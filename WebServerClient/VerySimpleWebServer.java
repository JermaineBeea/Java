package WebServerClient;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@SuppressWarnings("unused")
public class VerySimpleWebServer {
    private static final int PORT = 1300;

    public static void main(String[] args) {
        System.out.println("Starting web server...");
        try {
            // Create HTTP server on port 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            
            // Set up a single context handler for all requests
            server.createContext("/", new SimpleHandler());
            
            // Start the server
            server.start();
            
            System.out.println("Web server started on port " + PORT);
            System.out.println("Open http://localhost:" + PORT + " in your browser");
            
        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }

    static class SimpleHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Get request method and path
            String requestMethod = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            
            // Handle POST requests (messages from client)
            if ("POST".equals(requestMethod)) {
                // Read the message from the request body
                try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
                    StringBuilder messageBuilder = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        messageBuilder.append(line);
                    }
                    
                    String message = messageBuilder.toString();
                    System.out.println("\nClient: " + message);
                    
                    // Send a simple response
                    String response = "Message received";
                    sendResponse(exchange, response);
                }
            }
            // Handle GET requests (homepage)
            else if ("GET".equals(requestMethod)) {
                // Read HTML content from file
                String html;
                try {
                    html = readHtmlFile("WebServerClient\\index.html");
                } catch (IOException e) {
                    System.err.println("Error reading index.html: " + e.getMessage());
                    String errorMsg = "Error loading HTML file. Make sure index.html exists in the same directory.";
                    sendResponse(exchange, errorMsg, 500);
                    return;
                }

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                sendResponse(exchange, html);
            }
            // Handle other methods
            else {
                String response = "Method not supported";
                sendResponse(exchange, response, 405);
            }
        }
        
        // Helper method to send HTTP responses
        private void sendResponse(HttpExchange exchange, String response) throws IOException {
            sendResponse(exchange, response, 200);
        }
        
        private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
            exchange.sendResponseHeaders(statusCode, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
        
        // Helper method to read HTML file
        private String readHtmlFile(String filename) throws IOException {
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filename), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
            return content.toString();
        }
    }
}