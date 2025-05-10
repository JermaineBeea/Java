package Server;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Utility.LogConfiguration;

public class ServerSession {
    private static final LogConfiguration logConfig = new LogConfiguration(ServerSession.class.getName());
    private static final Logger logger = logConfig.getLogger();
    
    private final int clientId;
    private final Socket clientSocket;
    private final Thread serverThread;

    ObjectOutputStream objectToClient;
    DataOutputStream dataToClient;
    
    // Then initialize input streams
    ObjectInputStream objectFromClient;
    DataInputStream dataFromClient;

    
    public ServerSession(int clientIdArg, Socket clientSocketArg, Thread serverThreadArg) {
        this.clientId = clientIdArg;
        this.clientSocket = clientSocketArg;
        this.serverThread = serverThreadArg;
        
        runSession();
    }

    private void runSession() {
        // Initialize streams outside try-with-resources to handle specific initialization errors
        try {
            // Initialize output streams first to avoid potential deadlocks
            this.objectToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            this.dataToClient = new DataOutputStream(clientSocket.getOutputStream());
            
            // Then initialize input streams
            this.objectFromClient = new ObjectInputStream(clientSocket.getInputStream());
            this.dataFromClient = new DataInputStream(clientSocket.getInputStream());
            
            if (handleClientRegistration()) {
                // Continue with main session logic after successful registration
                handleMainSession();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error establishing streams with client " + clientId, e);
            logConfig.printStack(e);
        } finally {
            closeClientConnection();
        }
    }
    
    private boolean handleClientRegistration() {
        logger.info("Handling registration for client ID: " + clientId);
        
        for (int attempt = 0; attempt < ServerConstants.EXECUTION_ATTEMPTS.num; attempt++) {
            try {
                logger.info("Waiting for client " + clientId + " to send name (attempt " + (attempt + 1) + ")");
                
                String clientName = dataFromClient.readUTF().trim();
                logger.info("Received name from client " + clientId + ": " + clientName);
                
                if (clientName.isEmpty()) {
                    logger.warning("Client " + clientId + " sent empty name");
                    dataToClient.writeInt(ServerCodes.STATUS_EXCEPTION.code);
                    continue;
                }
                
                // Create client instance and add to client registry
                Client client = new Client(clientName, clientId, clientSocket, serverThread);
                ClientSet.addClient(clientId, client);
                
                // Verify client was properly registered
                boolean clientRegistered = ClientSet.confirmClientDetails(clientId, clientName);
                
                if (clientRegistered) {
                    logger.info("Client " + clientId + " successfully registered as: " + clientName);
                    dataToClient.writeInt(ServerCodes.STATUS_OK.code);
                    return true;
                } else {
                    logger.warning("Failed to confirm registration for client " + clientId);
                    
                    // If this is the last attempt, send ERROR, otherwise send EXCEPTION
                    if (attempt + 1 == ServerConstants.EXECUTION_ATTEMPTS.num) {
                        dataToClient.writeInt(ServerCodes.STATUS_ERROR.code);
                        return false;
                    } else {
                        dataToClient.writeInt(ServerCodes.STATUS_EXCEPTION.code);
                    }
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "IO error during registration of client " + clientId, e);
                logConfig.printStack(e);
                return false;
            }
        }
        
        logger.severe("All registration attempts failed for client " + clientId);
        return false;
    }
    
    private void handleMainSession() {
        // Main session logic would go here
        // This is where you would handle the primary client-server interaction
        logger.info("Starting main session for client " + clientId);
        
        try {
            // Example of main session logic
            
            // Main communication loop would go here
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in main session for client " + clientId, e);
            logConfig.printStack(e);
        }
    }
    
    private void closeClientConnection() {
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
                logger.info("Connection closed for client " + clientId);
            }
            
            // Clean up client from registry if needed
            ClientSet.removeClient(clientId);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error closing connection for client " + clientId, e);
        }
    }
}