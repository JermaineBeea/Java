import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class SimpleWebServer {
    private static final int PORT = 8080;
    private static final List<String> messages = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Starting web server...");
        try {
            // Create HTTP server on port 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            
            // Set up context handlers for different routes
            server.createContext("/", new HomeHandler());
            server.createContext("/send", new MessageHandler());
            server.createContext("/messages", new GetMessagesHandler());
            
            // Set thread pool for handling requests
            server.setExecutor(Executors.newFixedThreadPool(10));
            
            // Start the server
            server.start();
            
            System.out.println("Web server started on port " + PORT);
            System.out.println("Open http://localhost:" + PORT + " in your browser");
            
        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }

    // Handler for the home page - serves the HTML interface
    static class HomeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Simple Web Chat</title>\n" +
                    "    <style>\n" +
                    "        body { font-family: Arial, sans-serif; margin: 20px; }\n" +
                    "        #messageBox { width: 100%; height: 300px; margin-bottom: 10px; overflow-y: auto; border: 1px solid #ccc; padding: 10px; }\n" +
                    "        #messageInput { width: 80%; padding: 5px; }\n" +
                    "        #sendButton { padding: 5px 10px; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <h1>Simple Web Chat</h1>\n" +
                    "    <div id=\"messageBox\"></div>\n" +
                    "    <input type=\"text\" id=\"messageInput\" placeholder=\"Type a message...\">\n" +
                    "    <button id=\"sendButton\">Send</button>\n" +
                    "\n" +
                    "    <script>\n" +
                    "        const messageBox = document.getElementById('messageBox');\n" +
                    "        const messageInput = document.getElementById('messageInput');\n" +
                    "        const sendButton = document.getElementById('sendButton');\n" +
                    "\n" +
                    "        // Function to send a message to the server\n" +
                    "        function sendMessage() {\n" +
                    "            const message = messageInput.value.trim();\n" +
                    "            if (message) {\n" +
                    "                fetch('/send', {\n" +
                    "                    method: 'POST',\n" +
                    "                    body: message\n" +
                    "                })\n" +
                    "                .then(response => {\n" +
                    "                    if (response.ok) {\n" +
                    "                        messageInput.value = '';\n" +
                    "                        updateMessages();\n" +
                    "                    }\n" +
                    "                });\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        // Function to get messages from the server\n" +
                    "        function updateMessages() {\n" +
                    "            fetch('/messages')\n" +
                    "            .then(response => response.json())\n" +
                    "            .then(messages => {\n" +
                    "                messageBox.innerHTML = '';\n" +
                    "                messages.forEach(msg => {\n" +
                    "                    const msgElement = document.createElement('p');\n" +
                    "                    msgElement.textContent = `Client: ${msg}`;\n" +
                    "                    messageBox.appendChild(msgElement);\n" +
                    "                });\n" +
                    "                // Scroll to bottom of message box\n" +
                    "                messageBox.scrollTop = messageBox.scrollHeight;\n" +
                    "            });\n" +
                    "        }\n" +
                    "\n" +
                    "        // Set up event listeners\n" +
                    "        sendButton.addEventListener('click', sendMessage);\n" +
                    "        messageInput.addEventListener('keypress', function(e) {\n" +
                    "            if (e.key === 'Enter') {\n" +
                    "                sendMessage();\n" +
                    "            }\n" +
                    "        });\n" +
                    "\n" +
                    "        // Update messages when the page loads\n" +
                    "        updateMessages();\n" +
                    "        \n" +
                    "        // Periodically check for new messages\n" +
                    "        setInterval(updateMessages, 2000);\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>";

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, html.length());
            
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(html.getBytes());
            }
        }
    }

    // Handler for receiving messages
    static class MessageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                // Read the message from the request body
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
                BufferedReader br = new BufferedReader(isr);
                StringBuilder messageBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    messageBuilder.append(line);
                }
                
                String message = messageBuilder.toString();
                messages.add(message);
                
                System.out.println("\nClient: " + message);
                
                // Send a response indicating success
                String response = "Message received";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } else {
                // Method not allowed
                exchange.sendResponseHeaders(405, 0);
                exchange.getResponseBody().close();
            }
        }
    }

    // Handler for retrieving messages
    static class GetMessagesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                // Convert messages to JSON
                StringBuilder jsonBuilder = new StringBuilder("[");
                for (int i = 0; i < messages.size(); i++) {
                    jsonBuilder.append("\"").append(messages.get(i).replace("\"", "\\\"")).append("\"");
                    if (i < messages.size() - 1) {
                        jsonBuilder.append(",");
                    }
                }
                jsonBuilder.append("]");
                
                String json = jsonBuilder.toString();
                
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, json.length());
                
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(json.getBytes());
                }
            } else {
                // Method not allowed
                exchange.sendResponseHeaders(405, 0);
                exchange.getResponseBody().close();
            }
        }
    }
}