package ClientServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server {
    private static final String IP = getServerIp();
    private static final int PORT = 9000;

    public static void main(String[] args) {
        System.out.println("Attempting connection to " + IP + ":" + PORT + "...");
        ServerSocket server = null;

        try {
            server = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"));
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
        } finally {
            if (server != null && !server.isClosed()) {
                try {
                    server.close();
                } catch(IOException e) {
                    System.out.println("Could not close server: " + e.getMessage());
                }
            }
        }
    }

    private static void handleClient(Socket client, int clientId) {   
        String name = "Unknown";
        String clientMessage;
        BufferedReader fromClient = null;
        PrintWriter toClient = null;
        
        try {
            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toClient = new PrintWriter(client.getOutputStream(), true);

            // Retrieve name from client.
            name = fromClient.readLine();
            System.out.println(name + " [client " + clientId + "], is connected.");
            System.out.println("Waiting to receive messages from " + name + "...");
            
            while(!client.isClosed()) {
                // Receive Messages from Client.
                clientMessage = fromClient.readLine();
                if (clientMessage == null) {
                    // Client disconnected
                    break;
                }
                System.out.println("\n" + name + " [client " + clientId + "]: " + clientMessage);
            }

        } catch(IOException e) {
            System.out.println("Error handling client " + clientId + ": " + e.getMessage());
        } finally {
            System.out.println(name + " [client " + clientId + "] disconnected.");
            
            try {
                if (fromClient != null) fromClient.close();
                if (toClient != null) toClient.close();
                if (client != null && !client.isClosed()) client.close();
            } catch(IOException e) {
                System.out.println("Error closing client resources: " + e.getMessage());
            }
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