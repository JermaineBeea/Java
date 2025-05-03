import java.util.concurrent.atomic.AtomicBoolean;


import java.net.Socket;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Handles communications with a single client in a separate thread.
 * Manages client onboarding and message handling.
 */
public class ClientThread {
    private int clientId;
    private Socket clientSocket;
    private Thread thread;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private Client client;
    
    /**
     * Creates a new client thread for the specified client ID and socket.
     *
     * @param clientId The unique identifier for the client
     * @param clientSocket The socket connection to the client
     */
    public ClientThread(int clientId, Socket clientSocket) {
        this.clientId = clientId;
        this.clientSocket = clientSocket;
    }

    /**
     * The main client handling logic.
     * Handles client onboarding and message processing.
     */
    private final Runnable clientHandler = () -> {
        if (!isRunning.get()) {
            isRunning.set(true);
        }
        
        String clientIdentifier = "client " + clientId;
        try (
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream toClient = new DataOutputStream(clientSocket.getOutputStream());
            PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            System.out.println("Beginning onboarding of " + clientIdentifier);
            System.out.println("Waiting for " + clientIdentifier + " to provide name...");

            // Get client name from client
            String clientName = fromClient.readLine();
            System.out.println("Name received from " + clientIdentifier + " (" + clientName + ")");

            // Create and register client
            client = new Client(clientId, clientName, clientSocket);
            ClientManager.addClient(client);
            System.out.println(clientName + " has been successfully onboarded!");

            // Send onboarding complete status to client
            toClient.writeInt(StatusCode.ONBOARDING_COMPLETE.code);
            
            // Broadcast join message to all clients
            broadcastMessage(clientName + " has joined the chat!");
            
            // Handle client messages
            String message;
            while (isRunning.get() && (message = fromClient.readLine()) != null) {
                System.out.println(clientName + ": " + message);
                broadcastMessage(clientName + ": " + message);
            }
            
        } catch (IOException e) {
            System.out.println("Error handling " + clientIdentifier + ": " + e.getMessage());
        } finally {
            cleanup();
        }
    };
    
    /**
     * Broadcasts a message to all connected clients.
     *
     * @param message The message to broadcast
     */
    private void broadcastMessage(String message) {
        ClientManager.getAllClients().forEach(c -> {
            try {
                if (c.getId() != clientId && !c.getSocket().isClosed()) {
                    PrintWriter writer = new PrintWriter(c.getSocket().getOutputStream(), true);
                    writer.println(message);
                }
            } catch (IOException e) {
                System.err.println("Error broadcasting to client " + c.getId() + ": " + e.getMessage());
            }
        });
    }
    
    /**
     * Cleans up resources when the client disconnects.
     */
    private void cleanup() {
        if (client != null) {
            System.out.println("Client " + client.getName() + " disconnected");
            broadcastMessage(client.getName() + " has left the chat.");
            ClientManager.removeClient(client);
            
            try {
                if (!clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
        
        isRunning.set(false);
    }

    /**
     * Starts the client thread.
     */
    public void startThread() {
        if (thread != null && thread.isAlive()) {
            return;
        }

        isRunning.set(true);
        thread = new Thread(clientHandler);
        thread.start();
    }
    
    /**
     * Stops the client thread.
     */
    public void stopThread() {
        isRunning.set(false);
        if (thread != null) {
            thread.interrupt();
        }
        cleanup();
    }
}