package co.za.Main;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.Executors;

public class WebServerConfiguration {
    
    private HttpServer server;
    private VariableDatabase database;
    
    // In-memory application table (separate from database)
    private Map<String, Long> applicationTable;
    
    public WebServerConfiguration() throws IOException {
        initializeApplicationTable();
        initializeDatabase();
        setupServer();
    }
    
    private void initializeApplicationTable() {
        applicationTable = new HashMap<>();
        applicationTable.put("a", 0L);
        applicationTable.put("b", 0L);
        applicationTable.put("c", 0L);
        System.out.println("Application table initialized with default values.");
    }
    
    private void initializeDatabase() {
        try {
            database = new VariableDatabase();
            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void setupServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Serve static HTML page
        server.createContext("/", new StaticFileHandler());
        
        // API endpoints
        server.createContext("/api/data", new DataHandler());
        server.createContext("/api/update", new UpdateHandler());
        server.createContext("/api/query", new QueryHandler());
        
        server.setExecutor(Executors.newFixedThreadPool(10));
        
        System.out.println("Server starting on http://localhost:8080");
        server.start();
    }
    
    // Serve the HTML page
    class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String html = getHtmlContent();
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, html.getBytes().length);
                
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(html.getBytes());
                }
            }
        }
    }
    
    // Handle getting current data from application table
    class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                try {
                    // Get query values from database (if available)
                    Map<String, Long> queryValues = new HashMap<>();
                    try {
                        queryValues.put("a", database.getValueFromColumn("a", "query"));
                        queryValues.put("b", database.getValueFromColumn("b", "query"));
                        queryValues.put("c", database.getValueFromColumn("c", "query"));
                    } catch (SQLException e) {
                        // If database query fails, use default values
                        queryValues.put("a", 0L);
                        queryValues.put("b", 0L);
                        queryValues.put("c", 0L);
                    }
                    
                    String json = String.format(
                        "{\"data\": [" +
                        "{\"variable\": \"a\", \"value\": %d, \"query\": %d}," +
                        "{\"variable\": \"b\", \"value\": %d, \"query\": %d}," +
                        "{\"variable\": \"c\", \"value\": %d, \"query\": %d}" +
                        "]}",
                        applicationTable.get("a"), queryValues.get("a"),
                        applicationTable.get("b"), queryValues.get("b"),
                        applicationTable.get("c"), queryValues.get("c")
                    );
                    
                    sendJsonResponse(exchange, json);
                    
                } catch (Exception e) {
                    sendErrorResponse(exchange, "Error fetching data: " + e.getMessage());
                }
            }
        }
    }
    
    // Handle updating values in application table
    class UpdateHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                try {
                    String requestBody = readRequestBody(exchange);
                    System.out.println("Update request: " + requestBody);
                    
                    // Parse JSON manually (simple approach)
                    String variable = extractJsonValue(requestBody, "variable");
                    Long value = Long.parseLong(extractJsonValue(requestBody, "value"));
                    
                    // Update application table
                    applicationTable.put(variable, value);
                    System.out.println("Updated application table: " + variable + " = " + value);
                    
                    sendJsonResponse(exchange, "{\"success\": true, \"message\": \"Value updated successfully\"}");
                    
                } catch (Exception e) {
                    sendErrorResponse(exchange, "Error updating value: " + e.getMessage());
                }
            }
        }
    }
    
    // Handle running query (process values through database)
    class QueryHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                try {
                    System.out.println("Running query with application table values:");
                    System.out.println("a=" + applicationTable.get("a") + 
                                     ", b=" + applicationTable.get("b") + 
                                     ", c=" + applicationTable.get("c"));
                    
                    // Step 1: Get values from application table
                    Long aValue = applicationTable.get("a");
                    Long bValue = applicationTable.get("b");
                    Long cValue = applicationTable.get("c");
                    
                    // Step 2: Create QueryImplementation and populate database
                    QueryImplementation queryImpl = new QueryImplementation(aValue, bValue, cValue);
                    queryImpl.populateTable();
                    
                    // Step 3: Fetch query results from database
                    Long queryA = database.getValueFromColumn("a", "query");
                    Long queryB = database.getValueFromColumn("b", "query");
                    Long queryC = database.getValueFromColumn("c", "query");
                    
                    System.out.println("Query results from database: a=" + queryA + 
                                     ", b=" + queryB + ", c=" + queryC);
                    
                    String json = String.format(
                        "{\"success\": true, \"message\": \"Query executed successfully\", " +
                        "\"results\": {\"a\": %d, \"b\": %d, \"c\": %d}}",
                        queryA, queryB, queryC
                    );
                    
                    sendJsonResponse(exchange, json);
                    
                } catch (Exception e) {
                    sendErrorResponse(exchange, "Error running query: " + e.getMessage());
                }
            }
        }
    }
    
    private void sendJsonResponse(HttpExchange exchange, String json) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, json.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json.getBytes());
        }
    }
    
    private void sendErrorResponse(HttpExchange exchange, String error) throws IOException {
        String json = "{\"success\": false, \"error\": \"" + error + "\"}";
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(500, json.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json.getBytes());
        }
    }
    
    private String readRequestBody(HttpExchange exchange) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            return body.toString();
        }
    }
    
    private String extractJsonValue(String json, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) return "";
        
        int colonIndex = json.indexOf(":", keyIndex);
        int startIndex = json.indexOf("\"", colonIndex) + 1;
        int endIndex = json.indexOf("\"", startIndex);
        
        if (startIndex == 0 || endIndex == -1) {
            // Handle numeric values (no quotes)
            startIndex = colonIndex + 1;
            while (startIndex < json.length() && (json.charAt(startIndex) == ' ' || json.charAt(startIndex) == '\t')) {
                startIndex++;
            }
            endIndex = startIndex;
            while (endIndex < json.length() && Character.isDigit(json.charAt(endIndex))) {
                endIndex++;
            }
        }
        
        return json.substring(startIndex, endIndex);
    }
    
    private String getHtmlContent() {
        try {
            // Try to read from the HTML file first
            File htmlFile = new File("index.html");
            if (htmlFile.exists()) {
                return readFileContent(htmlFile);
            }
            
            // If file doesn't exist, try reading from resources
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("index.html")) {
                if (is != null) {
                    return readInputStreamContent(is);
                }
            }
            
            // Fallback to inline HTML if file not found
            System.out.println("Warning: index.html not found, using fallback HTML content");
            return getFallbackHtmlContent();
            
        } catch (IOException e) {
            System.err.println("Error reading HTML file: " + e.getMessage());
            return getFallbackHtmlContent();
        }
    }
    
    private String readFileContent(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    
    private String readInputStreamContent(InputStream is) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    
    private String getFallbackHtmlContent() {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Variable Query Web Application</title>
            <style>
                body { font-family: Arial, sans-serif; max-width: 800px; margin: 0 auto; padding: 20px; background-color: #f5f5f5; }
                .container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
                h1 { color: #333; text-align: center; margin-bottom: 30px; }
                .error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; padding: 20px; border-radius: 4px; }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>Variable Query Web Application</h1>
                <div class="error">
                    <h3>Configuration Error</h3>
                    <p>The HTML template file 'index.html' was not found. Please ensure it exists in the project root directory or resources folder.</p>
                    <p>Expected location: <code>index.html</code> in the same directory as the JAR file, or in the resources folder.</p>
                </div>
            </div>
        </body>
        </html>
        """;
    }
    
    public void stop() {
        if (server != null) {
            server.stop(0);
        }
        if (database != null) {
            try {
                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}