package Server;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utility.HandShake;
import Utility.LogConfiguration;

/**
 * Manages server-side connections to clients.
 * Handles server socket initialization, client acceptance, and connection handshakes.
 */
public class ServerConnection 
{
    /** Logger configuration for this class */
    private static LogConfiguration logConfig = new LogConfiguration(ServerConnection.class.getName());
    
    /** Logger instance for this class */
    private static Logger logger = logConfig.getLogger();
    
    /** Server socket for accepting client connections */
    private ServerSocket serverSocket;
    
    /** Socket for the current client connection */
    private Socket clientSocket;
    
    /** Server port number */
    private final int PORT;
    
    /** Connection request queue length */
    private final int BACKLOG;

    /**
     * Initializes a server connection with specified port and connection backlog.
     * Automatically starts running the connection after initialization.
     * 
     * @param serverPort Port number to bind the server to
     * @param backlog Maximum length of the queue of incoming connections
     */
    public ServerConnection(int serverPort, int backlog)
    {
       this.PORT = serverPort;
       this.BACKLOG = backlog;
       runConnection();
    }

    /**
     * Creates and runs the server, binding to the specified port and accepting client connections.
     * Binds to all available network interfaces (0.0.0.0).
     */
    public void runConnection()
    {
        System.out.println();
        logger.info("Establishing server connection...");
        try
        {
            // Bind to all network interfaces (0.0.0.0)
            this.serverSocket = new ServerSocket(PORT, BACKLOG, InetAddress.getByName("0.0.0.0"));
            System.out.println();
            logger.info("Connected successfully to port " + PORT + "!");
            
            // Begin accepting client connections
            connectClients();
        }
        catch(Exception e)
        {
            closeConnection();
            logger.log(Level.SEVERE, "Connection Error: " + e);
            logConfig.printStack(e);
        }
    }

    /**
     * Continuously accepts client connections and handles them.
     * For each accepted client, performs handshake and creates a new server thread.
     * 
     * @throws Exception If an error occurs during client connection handling
     */
    private void connectClients() throws Exception
    {
        int clientCount = 1;
        
        // Continue accepting clients as long as the server socket is open
        while (serverSocket.isBound() && !serverSocket.isClosed())
        {
            // Accept a new client connection
            clientSocket = serverSocket.accept();
            System.out.println();
            logger.info("Client " + clientCount + " connected from: " + 
                       clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                        
            // Establish connection test with client before starting thread
            HandShake handShake = new HandShake(clientSocket);
            System.out.println();
            logger.info("Connection Test: Send and receive handshake from client...");
            handShake.sendHandshake();
            System.out.println();
            logger.info("Connection Test: Handshake received!\nCreating thread for client.");

            // Create and start a new thread for this client
            final int clientId = clientCount;
            ServerThread serverThread = new ServerThread(clientId, clientSocket, serverSocket);
            serverThread.startThread();
            
            // Increment client counter for next connection
            clientCount++;
        }
    }
    
    /**
     * Closes server and client sockets to release resources.
     * Called when shutting down the server or when an error occurs.
     */
    public void closeConnection()
    {
        try
        {
            // Close server socket if it exists
            if(serverSocket != null) 
            {
                serverSocket.close();
            }
            
            // Close client socket if it exists
            if(clientSocket != null) 
            {
                clientSocket.close();
            }
        }
        catch(IOException e)
        {
            logger.log(Level.SEVERE, "Error closing connections\n", e);
            logConfig.printStack(e);
        }
    }
}