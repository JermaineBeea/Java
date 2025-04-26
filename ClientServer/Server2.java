package ClientServer;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Server2 {
    private static final int serverPORT = 9000;
    private static final String serverIP = "localhost";
    private static int clientCount = 0;

    public static void main(String[] args) {
        System.out.println("Attempting to connect at " + serverIP + ":" + serverPORT);
        
        try (ServerSocket server = new ServerSocket(serverPORT)) {
            System.out.println("Connected Successfully!\nWaiting for clients...");
            
            while (true) {
                // Main thread accepts clients (blocks until a client connects)
                Socket client = server.accept();
                clientCount++;
                System.out.println("Client " + clientCount + " Connected!");
                
                // Create a new thread for each client
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void handleClient(Socket client) {
        try {
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);
            
            // Retrieve name from client
            String name = fromClient.readLine();
            System.out.println(name + " has connected");
            
            // Continue handling client communication
            while (client.isConnected()) {
                // Process additional client messages if needed
                String message = fromClient.readLine();
                if (message == null) {
                    break; // Client disconnected
                }
                System.out.println(name + ": " + message);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}