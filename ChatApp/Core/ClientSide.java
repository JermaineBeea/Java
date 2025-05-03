import java.util.Scanner;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Handles client-side operations for the chat application.
 * Manages connecting to the server and client operations.
 */
public class ClientSide {
    private final String serverIp;
    private final int serverPort;
    private final int connectionAttempts;
    private Socket serverSocket;
    
    /**
     * Creates a new client with the specified server IP and port.
     *
     * @param serverIp The IP address of the server
     * @param serverPort The port of the server
     */
    public ClientSide(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.connectionAttempts = 5; // Default connection attempts
    }
    
    /**
     * Creates a new client with the specified server IP, port, and connection attempts.
     *
     * @param serverIp The IP address of the server
     * @param serverPort The port of the server
     * @param connectionAttempts Number of connection attempts before giving up
     */
    public ClientSide(String serverIp, int serverPort, int connectionAttempts) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.connectionAttempts = connectionAttempts;
    }

    /**
     * Logs a message to the console if logging is enabled.
     *
     * @param printLog Whether to print the log message
     * @param message The message to log
     */
    private void printLog(boolean printLog, String message) {
        if (printLog) {
            System.out.println(message);
        }
    }

    /**
     * Closes the connection to the server.
     */
    public void closeConnection() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Connection to server closed");
            }
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    /**
     * Starts the client and connects to the server.
     */
    public void startClient() {
        String ipPort = "[" + serverIp + ":" + serverPort + "]";
        System.out.println("Establishing connection to server at " + ipPort + "...");

        // Try to connect to the server
        for (int n = 0; n < connectionAttempts; n++) {
            try {
                serverSocket = new Socket(serverIp, serverPort);
                if (serverSocket.isConnected()) {
                    break;
                }
            } catch (IOException e) {
                System.err.println("Attempt " + (n + 1) + ": Error connecting to server: " + e.getMessage());
                
                if (n + 1 < connectionAttempts) {
                    try {
                        Thread.sleep(1000); // Wait a second before retrying
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            if (n + 1 == connectionAttempts) {
                System.err.println("Could not connect to " + ipPort + " after " + connectionAttempts + " attempts");
                return;
            }
        }

        // Handle client communication
        try (
            Scanner consoleIn = new Scanner(System.in);
            PrintWriter toServer = new PrintWriter(serverSocket.getOutputStream(), true);
            DataInputStream fromServer = new DataInputStream(serverSocket.getInputStream())
        ) {
            System.out.println("Successfully connected to server at " + ipPort);
            
            // Send client name to server
            System.out.print("Enter your name: ");
            String clientName = consoleIn.nextLine();
            toServer.println(clientName);

            final int serverStatus = fromServer.readInt();
            printLog(true, "Server status received code: " + serverStatus);

            if (serverStatus == StatusCode.ONBOARDING_COMPLETE.code) {
                System.out.println("Hello " + clientName + ", welcome to the chat!");
                
                // Handle chat session
                handleChatSession(consoleIn, toServer, fromServer);
            } else {
                System.out.println("Connection failed with status code: " + serverStatus);
            }
        } catch (IOException e) {
            System.err.println("Error during client session: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }
    
    /**
     * Handles the client's chat session after successful connection.
     *
     * @param consoleIn Scanner for reading console input
     * @param toServer PrintWriter for sending data to server
     * @param fromServer DataInputStream for receiving data from server
     */
    private void handleChatSession(Scanner consoleIn, PrintWriter toServer, DataInputStream fromServer) {
        // Create a thread to listen for messages from the server
        Thread messageListener = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted() && serverSocket.isConnected()) {
                    if (fromServer.available() > 0) {
                        String message = fromServer.readUTF();
                        System.out.println(message);
                    }
                    Thread.sleep(100);
                }
            } catch (IOException | InterruptedException e) {
                if (!serverSocket.isClosed()) {
                    System.err.println("Error receiving messages: " + e.getMessage());
                }
            }
        });
        messageListener.setDaemon(true);
        messageListener.start();
        
        // Handle user input
        try {
            System.out.println("Type your messages (type 'exit' to quit):");
            String message;
            while (!(message = consoleIn.nextLine()).equalsIgnoreCase("exit")) {
                toServer.println(message);
            }
            System.out.println("Exiting chat...");
        } catch (Exception e) {
            System.err.println("Error sending messages: " + e.getMessage());
        }
        
        // Cleanup
        messageListener.interrupt();
    }
    

}