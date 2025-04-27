package ClientServer;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ComplexServer {
    private static final int serverPORT = 9000;
    private static final String serverIP = "localhost";
    private static final Set<Socket> activeClients = ConcurrentHashMap.newKeySet(); // Thread-safe set
    private static int clientCount = 0;

    public static void main(String[] args) {
        System.out.println("Attempting to connect at " + serverIP + ":" + serverPORT);
        
        // Thread to accept clients
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(serverPORT)) {
                System.out.println("Connected Successfully!\nWaiting for clients...");
                
                while (true) {
                    Socket client = server.accept();
                    synchronized (activeClients) {
                        activeClients.add(client);
                        clientCount++;
                        System.out.println("Client " + clientCount + " Connected!");
                        // Notify the main thread that a new client has connected
                        activeClients.notifyAll();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        
        // Main Thread to retrieve names from clients
        try {
            while (true) {
                Socket clientToProcess = null;
                
                // Wait until there's at least one client available
                synchronized (activeClients) {
                    while (activeClients.isEmpty()) {
                        try {
                            System.out.println("Waiting for clients to connect...");
                            activeClients.wait(); // Wait until notified by the client accept thread
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    
                    // Get the next client to process
                    clientToProcess = activeClients.iterator().next();
                    activeClients.remove(clientToProcess); // Remove it so we don't process it again
                }
                
                // Now process this client outside the synchronized block
                processClient(clientToProcess);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void processClient(Socket client) {
        try {
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);
            
            // Retrieve name from client
            String name = fromClient.readLine();
            System.out.println(name + " has connected");
            
            // Add client back to the set for future interactions
            synchronized (activeClients) {
                activeClients.add(client);
            }
        } catch (IOException e) {
            System.out.println("Error processing client: " + e.getMessage());
        }
    }
}