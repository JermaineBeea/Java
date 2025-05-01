import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@SuppressWarnings("unused")
public class VerySimpleWebServer {
    private static final int PORT = 8080;

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
                String html = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Very Simple Web Chat</title>\n" +
                        "    <style>\n" +
                        "        body { font-family: Arial, sans-serif; margin: 20px; }\n" +
                        "        #messageInput { width: 300px; padding: 5px; }\n" +
                        "        #sendButton { padding: 5px 10px; }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h1>Very Simple Web Chat</h1>\n" +
                        "    <input type=\"text\" id=\"messageInput\" placeholder=\"Type a message...\">\n" +
                        "    <button id=\"sendButton\">Send</button>\n" +
                        "    <p id=\"status\"></p>\n" +
                        "\n" +
                        "    <script>\n" +
                        "        const messageInput = document.getElementById('messageInput');\n" +
                        "        const sendButton = document.getElementById('sendButton');\n" +
                        "        const status = document.getElementById('status');\n" +
                        "\n" +
                        "        // Function to send a message to the server\n" +
                        "        function sendMessage() {\n" +
                        "            const message = messageInput.value.trim();\n" +
                        "            if (message) {\n" +
                        "                status.textContent = 'Sending...';\n" +
                        "                fetch('/', {\n" +
                        "                    method: 'POST',\n" +
                        "                    body: message\n" +
                        "                })\n" +
                        "                .then(response => {\n" +
                        "                    if (response.ok) {\n" +
                        "                        status.textContent = 'Message sent!';\n" +
                        "                        messageInput.value = '';\n" +
                        "                    }\n" +
                        "                })\n" +
                        "                .catch(error => {\n" +
                        "                    status.textContent = 'Error sending message.';\n" +
                        "                });\n" +
                        "            }\n" +
                        "        }\n" +
                        "\n" +
                        "        // Set up event listeners\n" +
                        "        sendButton.addEventListener('click', sendMessage);\n" +
                        "        messageInput.addEventListener('keypress', function(e) {\n" +
                        "            if (e.key === 'Enter') {\n" +
                        "                sendMessage();\n" +
                        "            }\n" +
                        "        });\n" +
                        "    </script>\n" +
                        "</body>\n" +
                        "</html>";

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
    }
}