package za.co.wethinkcode.robots.Client;

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

import za.co.wethinkcode.robots.Utility.LogConfiguration;

/**
 * Manages the client-side communication session with the server.
 * Handles the onboarding process and main session functionality.
 */
public class ClientSession 
{
    /** Logger configuration for this class */
    private static final LogConfiguration logConfig = new LogConfiguration(ClientSession.class.getName());
    
    /** Logger instance for this class */
    private static final Logger logger = logConfig.getLogger();
    
    /** Socket connection to the server */
    private final Socket serverSocket;

    // Output streams - initialized first to avoid potential deadlocks
    /** Stream for sending objects to the server */
    ObjectOutputStream objectToServer;
    
    /** Stream for sending primitive data to the server */
    DataOutputStream dataToServer;

    // Input streams - initialized after output streams
    /** Stream for receiving objects from the server */
    ObjectInputStream objectFromServer;
    
    /** Stream for receiving primitive data from the server */
    DataInputStream dataFromServer;

    /**
     * Creates a new client session with the specified server socket.
     * 
     * @param serverSocketArg The socket connection to the server
     */
    public ClientSession(Socket serverSocketArg) 
    {
        this.serverSocket = serverSocketArg;
        runSession();
    }

    /**
     * Initializes the session by setting up I/O streams and managing the client lifecycle.
     * First handles onboarding, then moves to the main session if onboarding is successful.
     */
    private void runSession() 
    {
        // Initialize all streams outside the try-with-resources to handle specific stream exceptions
        try 
        {
            // Initialize output streams first to avoid potential deadlocks
            this.objectToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            this.dataToServer = new DataOutputStream(serverSocket.getOutputStream());
            
            // Then initialize input streams
            this.objectFromServer = new ObjectInputStream(serverSocket.getInputStream());
            this.dataFromServer = new DataInputStream(serverSocket.getInputStream());
            
            try (Scanner consoleIn = new Scanner(System.in)) 
            {
                if (clientOnboarding(consoleIn)) 
                {
                    // Continue with the main session logic after successful onboarding
                    handleMainSession(consoleIn);
                }
            }
            System.out.println();
        } 
        catch (IOException e) 
        {
            logger.log(Level.SEVERE, "Error establishing streams with server", e);
            logConfig.printStack(e);
        } 
        finally 
        {
            closeConnection();
        }
    }
    
    /**
     * Handles the client onboarding process, including user name collection and server verification.
     * Continues prompting for a valid name until successful or the server rejects the connection.
     * 
     * @param consoleIn Scanner for reading user input
     * @return true if onboarding was successful, false otherwise
     */
    private boolean clientOnboarding(Scanner consoleIn) 
    {
        try 
        {
            System.out.print("Please enter your name: ");
            
            while (true) 
            {
                // Get client name from console input
                String clientName = consoleIn.nextLine().trim();
                
                // Validate name is not empty
                if (clientName.isEmpty()) 
                {
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
                
                // Process server response based on status code
                switch (statusCode) 
                {
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
        } 
        catch (IOException e) 
        {
            logger.log(Level.SEVERE, "Communication error during onboarding", e);
            logConfig.printStack(e);
            return false;
        }
    }
    
    /**
     * Closes the connection to the server and associated resources.
     */
    private void closeConnection() 
    {
        try 
        {
            if (serverSocket != null && !serverSocket.isClosed()) 
            {
                serverSocket.close();
                logger.info("Connection to server closed");
            }
        } 
        catch (IOException e) 
        {
            logger.log(Level.WARNING, "Error closing server socket", e);
        }
    }

    /**
     * Handles the main session after successful onboarding.
     * Displays robot types and allows the user to select one.
     * 
     * @param consoleIn Scanner for reading user input
     */
    private void handleMainSession(Scanner consoleIn) 
    {
        System.out.println();
        logger.info("Client successfully onboarded, beginning main session");
        
        try 
        {
            // Establish connection established message
            System.out.println();
            System.out.println("Connection established with server. Type 'exit' to disconnect.");
    
            // Read JSON as a string from the server
            String robotTypesJsonString = dataFromServer.readUTF();
            
            // Parse the JSON string into a JSONObject
            JSONObject robotTypes = new JSONObject(robotTypesJsonString);
            
            // Display available robot types with formatted JSON output
            System.out.println("\nPlease select the robot type by typing its name:");
            System.out.println(robotTypes.toString(4));
            
            // Handle robot type selection
            while (true) 
            {
                System.out.print("Enter robot type: ");
                String selectedType = consoleIn.nextLine().trim();
                
                // Check for exit command
                if (selectedType.equalsIgnoreCase("exit")) 
                {
                    break;
                }
                
                // Validate selection against available types
                if (robotTypes.has(selectedType)) 
                {
                    System.out.println("Selected robot details:");
                    System.out.println(robotTypes.getJSONObject(selectedType).toString(4));
                    // Additional logic for robot selection would go here
                    break;
                } 
                else 
                {
                    System.out.println("Invalid robot type. Please try again.");
                }
            }
        } 
        catch (Exception e) 
        {
            logger.log(Level.SEVERE, "Error in main session", e);
            logConfig.printStack(e);
        }
    }    
}