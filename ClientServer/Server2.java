package ClientServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server2 {
    private static final String IP = getServerIp();
    private static final int PORT = 9000;

    public static void main(String[] args) {
        System.out.println("Attempting connection to " + IP + ":" + PORT + "...");
    
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))) {
            System.out.println("Connected successfully to Port " + PORT + "\nWaiting for Clients to connect...");
    
            // Main thread to accepts client connections.
            int clientcount = 1;
            while(true) {
                Socket client = server.accept();
                System.out.println("Client " + clientcount + ", is connected from: " + client.getRemoteSocketAddress());
                
                // Thread to handle each Client separately.
                final int currentClientId = clientcount;
                new Thread(() -> handleClient(client, currentClientId)).start();
                
                clientcount++;
            }
        } catch(IOException e) {
            System.out.println("Server Error: Unable to connect: " + e.getMessage());
        }
    }
    
    private static void handleClient(Socket client, int clientId) {   
        String name = "Unknown";
        
        try (
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);
            Socket clientSocket = client // This will ensure the client socket is closed when done
        ) {
            // Retrieve name from client.
            name = fromClient.readLine();
            System.out.println(name + " [client " + clientId + "], is connected.");
            System.out.println("Waiting to receive messages from " + name + "...");
            
            String clientMessage;
            while((clientMessage = fromClient.readLine()) != null) {
                System.out.println("\n" + name + " [client " + clientId + "]: " + clientMessage);
            }
        } catch(IOException e) {
            System.out.println("Error handling client " + clientId + ": " + e.getMessage());
        } finally {
            System.out.println(name + " [client " + clientId + "] disconnected.");
        }
    }


    private static String getServerIp() {
        Socket tempSocket = null;
        
        try {
            // Find non-local IP address.
            tempSocket = new Socket("8.8.8.8", 53);
            return tempSocket.getLocalAddress().getHostAddress();
        } catch(IOException e) {
            try {
                // Find local IP address.
                return InetAddress.getLocalHost().getHostAddress();
            } catch(UnknownHostException ex) {
                System.out.println("Cannot find IP Address: " + ex.getMessage());
                return "localhost";
            }
        } finally {
            if (tempSocket != null) {
                try {
                    tempSocket.close();
                } catch(IOException e) {
                    System.out.println("Error closing temp socket: " + e.getMessage());
                }
            }
        }
    }
}