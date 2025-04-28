package ChatApp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Set;

/**
 * The methods used in the ServerSide class.
 */
public class ServerMethods {

    /**
     * Handles a new client connection, creating a new client instance and starting message handling.
     * @param clientId The client Id, which is the order of the client being accepted.
     * @param clientSocket The client's socket connection.
     */
    public static void clientHandler(int clientId, Socket clientSocket) {
        try {
            // Set up input stream to receive client name
            DataInputStream fromClient = new DataInputStream(clientSocket.getInputStream());
            
            // Receive name from client
            String clientName = fromClient.readUTF();
            System.out.println("Client " + clientId + " identified as: " + clientName);
            
            // Create new Client instance
            Client client = new Client(clientId, clientName, clientSocket);
            
            // Add client to set of clients
            AllClients.addClient(client);
            
            // Notify everyone of new client
            broadcastMessage("SERVER: " + clientName + " has joined the chat!", client);
            
            // Send current client count to all clients
            String countMessage = "SERVER: Currently " + AllClients.getCountClients() + " user(s) online.";
            broadcastMessage(countMessage, null);
            
            // Start message handling thread
            new Thread(() -> handleClientMessages(client)).start();
            
        } catch (IOException e) {
            System.err.println("Client Handler Error: " + e.getMessage());
        }
    }
    
    /**
     * Continuously listens for messages from a client and broadcasts them.
     * @param client The client whose messages to handle.
     */
    private static void handleClientMessages(Client client) {
        DataInputStream inputStream = client.getInputStream();
        
        try {
            // Keep reading messages until connection is closed
            while (client.isActive()) {
                String message = inputStream.readUTF();
                
                // Check for exit command
                if (message.equalsIgnoreCase("/exit")) {
                    disconnectClient(client);
                    break;
                }
                
                // Format and broadcast the message
                String formattedMessage = client.getClientName() + ": " + message;
                broadcastMessage(formattedMessage, client);
            }
        } catch (IOException e) {
            // Handle client disconnection
            System.err.println("Client disconnected: " + e.getMessage());
            disconnectClient(client);
        }
    }
    
    /**
     * Safely disconnects a client.
     * @param client The client to disconnect.
     */
    private static void disconnectClient(Client client) {
        if (client != null) {
            // Remove from active clients
            AllClients.removeClient(client);
            
            // Notify others
            broadcastMessage("SERVER: " + client.getClientName() + " has left the chat.", null);
            
            // Close resources
            client.close();
        }
    }
    
    /**
     * Broadcasts a message to all connected clients.
     * @param message The message to broadcast.
     * @param exclude Client to exclude from broadcast (usually the sender), can be null to send to all.
     */
    public static void broadcastMessage(String message, Client exclude) {
        Set<Client> clients = AllClients.getClients();
        
        for (Client client : clients) {
            // Skip the sender if specified
            if (exclude != null && client.getClientID() == exclude.getClientID()) {
                continue;
            }
            
            // Send message to this client
            if (!client.sendMessage(message)) {
                // If sending failed, client might be disconnected
                disconnectClient(client);
            }
        }
    }
}