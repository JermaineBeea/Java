package Client;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Utility.LogConfiguration;

public class ClientSession {
    private static final LogConfiguration logConfig = new LogConfiguration(ClientSession.class.getName());
    private static final Logger logger = logConfig.getLogger();
    private final Socket serverSocket;

    ObjectOutputStream objectToServer;
    ObjectInputStream objectFromServer;
    DataOutputStream dataToServer;
    DataInputStream dataFromServer;
    
    public ClientSession(Socket serverSocketArg) {
        this.serverSocket = serverSocketArg;
        
        runSession();
    }

    private void runSession() {
        // Initialize all streams outside the try-with-resources to handle specific stream exceptions
        try {
            // Initialize output streams first to avoid potential deadlocks
            this.objectToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            this.dataToServer = new DataOutputStream(serverSocket.getOutputStream());
            
            // Then initialize input streams
            this.objectFromServer = new ObjectInputStream(serverSocket.getInputStream());
            this.dataFromServer = new DataInputStream(serverSocket.getInputStream());
            
            try (Scanner consoleIn = new Scanner(System.in)) {
                if (handleClientOnboarding(consoleIn)) {
                    // Continue with the main session logic after successful onboarding
                    handleMainSession(consoleIn);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error establishing streams with server", e);
            logConfig.printStack(e);
        } finally {
            closeConnection();
        }
    }
    
    private boolean handleClientOnboarding(Scanner consoleIn) {
        try {
            System.out.print("Please enter your name: ");
            
            while (true) {
                String clientName = consoleIn.nextLine().trim();
                
                if (clientName.isEmpty()) {
                    System.out.print("Name cannot be empty. Please enter your name: ");
                    continue;
                }
                
                // Send name to server
                dataToServer.writeUTF(clientName);
                logger.info("Sent name to server: " + clientName);
                
                // Receive status code from server
                int serverCode = dataFromServer.readInt();
                logger.info("Received server code: " + serverCode);
                
                // Convert int code to enum value for cleaner switch
                ClientCodes statusCode = ClientCodes.fromCode(serverCode);
                
                // Process server response
                switch (statusCode) {
                    case STATUS_OK:
                        System.out.println("Connected successfully as: " + clientName);
                        return true;
                    case STATUS_EXCEPTION:
                        System.out.println("Server couldn't process your name. Please try again.");
                        System.out.print("Please re-enter your name: ");
                        break;
                    case STATUS_ERROR:
                        System.err.println("Server error occurred. Closing connection.");
                        logger.log(Level.SEVERE, "Server reported error during onboarding");
                        return false;
                    default:
                        System.err.println("Unknown server response: " + serverCode);
                        logger.log(Level.WARNING, "Received unknown status code: " + serverCode);
                        return false;
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Communication error during onboarding", e);
            logConfig.printStack(e);
            return false;
        }
    }
    
    private void handleMainSession(Scanner consoleIn) {
        // Main session logic would go here
        // This is where you would handle the primary client-server interaction
        logger.info("Client successfully onboarded, beginning main session");
        
        try {
            // Example of main session logic
            System.out.println("Connection established with server. Type 'exit' to disconnect.");
            
            // Main communication loop would go here
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in main session", e);
            logConfig.printStack(e);
        }
    }
    
    private void closeConnection() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                logger.info("Connection to server closed");
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error closing server socket", e);
        }
    }
}