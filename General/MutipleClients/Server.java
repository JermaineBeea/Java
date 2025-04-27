package MutipleClients;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 5000;
    private static HashSet<PrintWriter> writers = new HashSet<>();
    
    public static void main(String[] args) {
        System.out.println("Chat Server started on port " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Error in the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            try {
                // Set up input and output streams
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // Request a name from the client
                out.println("SUBMIT_NAME");
                name = in.readLine();
                
                // Add client to the chat room and notify others
                synchronized (writers) {
                    writers.add(out);
                }
                
                System.out.println(name + " has joined the chat.");
                broadcast(name + " has joined the chat!");
                
                // Process messages from this client
                String message;
                while ((message = in.readLine()) != null) {
                    if (!message.isEmpty()) {
                        broadcast(name + ": " + message);
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } finally {
                // Client is leaving
                if (name != null) {
                    System.out.println(name + " has left the chat.");
                    broadcast(name + " has left the chat.");
                }
                
                // Remove client from the writers set
                if (out != null) {
                    synchronized (writers) {
                        writers.remove(out);
                    }
                }
                
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Broadcast a message to all connected clients
        private void broadcast(String message) {
            synchronized (writers) {
                for (PrintWriter writer : writers) {
                    writer.println(message);
                }
            }
        }
    }
}