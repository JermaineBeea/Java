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
import org.json.JSONObject;

import Utility.LogConfiguration;

public class ClientConection {
    private static final LogConfiguration logConfig = new LogConfiguration(ClientConection.class.getName());
    private static final Logger logger = logConfig.getLogger();
    private final Socket serverSocket;

    // Initialize output streams first to avoid potential deadlocks
    ObjectOutputStream objectToServer;
    DataOutputStream dataToServer;

    // Then initialize input streams
    ObjectInputStream objectFromServer;
    DataInputStream dataFromServer;

    
    public ClientConection(Socket serverSocketArg) {
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
            }System.out.println();
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
                System.out.println();
                logger.info("Sent name to server: " + clientName);
                
                // Receive status code from server
                int serverCode = dataFromServer.readInt();
                System.out.println();
                logger.info("Received server code: " + serverCode);
                
                // Convert int code to enum value for cleaner switch
                ClientCodes statusCode = ClientCodes.fromCode(serverCode);
                
                // Process server response
                switch (statusCode) {
                    case STATUS_OK:
                        System.out.println();
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

    private void handleMainSession(Scanner consoleIn) {
        System.out.println();
        logger.info("Client successfully onboarded, beginning main session");
        
        try {
            // Establish connection established message
            System.out.println();
            System.out.println("Connection established with server. Type 'exit' to disconnect.");
    
            // Read JSON as a string from the server
            String robotTypesJsonString = dataFromServer.readUTF();
            
            // Parse the JSON string into a JSONObject
            JSONObject robotTypes = new JSONObject(robotTypesJsonString);
            
            // Display available robot types
            System.out.println("\nPlease select the robot type by typing its name:");
            System.out.println(robotTypes.toString(4));
            
            // Optional: Add logic for robot type selection
            while (true) {
                System.out.print("Enter robot type: ");
                String selectedType = consoleIn.nextLine().trim();
                
                if (selectedType.equalsIgnoreCase("exit")) {
                    break;
                }
                
                if (robotTypes.has(selectedType)) {
                    System.out.println("View robot details below");
                    System.out.println(robotTypes.getJSONObject(selectedType).toString(4));
                    
                    System.out.println();
                    System.out.println("Do you want to select this robot type? (yes/no)");
                    String confirmation = consoleIn.nextLine().trim();
                    if (confirmation.equalsIgnoreCase("yes")) {
                        // Send the selected robot type to the server
                        dataToServer.writeUTF(selectedType);
                        dataToServer.flush();
                        
                        System.out.println();
                        System.out.println("Robot type " + selectedType + " selected.");
                        break;
                    } else if (confirmation.equalsIgnoreCase("no")) {
                        System.out.println("Please select another robot type.");
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                } else if (selectedType.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the program.");
                    break;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in main session", e);
            logConfig.printStack(e);
        }
    }    
}