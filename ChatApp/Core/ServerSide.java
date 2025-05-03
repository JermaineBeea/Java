
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Server-side implementation for the chat application.
 * Manages the server socket and client connections.
 */
public class ServerSide {
    private final int port;
    private final int backLog;
    private final int connectionAttempts;
    private String serverIp;
    private ServerSocket serverSocket;
    private boolean isRunning = false;
    private final List<ClientThread> clientThreads = new ArrayList<>();
    
    /**
     * Creates a new server with the specified port, backlog, and connection attempts.
     *
     * @param port The port to listen on
     * @param backLog The maximum queue length for incoming connections
     * @param connectionAttempts Number of connection attempts before giving up
     */
    public ServerSide(int port, int backLog, int connectionAttempts) {
        this.port = port;
        this.backLog = backLog;
        this.connectionAttempts = connectionAttempts;
    }

    /**
     * Creates a new server with the specified port and backlog.
     *
     * @param port The port to listen on
     * @param backLog The maximum queue length for incoming connections
     */
    public ServerSide(int port, int backLog) {
        this(port, backLog, 5);
    }

    /**
     * Creates a new server with the specified port.
     *
     * @param port The port to listen on
     */
    public ServerSide(int port) {
        this(port, 50, 5);
    }

    /**
     * Starts the server and begins accepting client connections.
     */
    public void startServer() {
        serverIp = getServerIp();
        String ipPort = "[" + serverIp + ":" + port + "]";
        System.out.println("Establishing server on " + ipPort);

        // Establish server socket
        for (int n = 0; n < connectionAttempts; n++) {
            try {
                serverSocket = new ServerSocket(port, backLog, InetAddress.getByName("0.0.0.0"));
                if (serverSocket.isBound()) {
                    break;
                }
            } catch (IOException e) {
                System.err.println("Attempt " + (n + 1) + ": Error establishing server connection: " + e.getMessage());
                
                if (n + 1 < connectionAttempts) {
                    try {
                        Thread.sleep(1000); // Wait a second before retrying
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            if (n + 1 == connectionAttempts) {
                System.err.println("Could not establish server on " + ipPort + " after " + connectionAttempts + " attempts");
                return;
            }
        }

        System.out.println("Server established on " + ipPort);
        System.out.println("Waiting for clients to connect to server...");
        
        isRunning = true;
        int clientCount = 1;
        
        // Accept and handle client connections
        while (isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client " + clientCount + " connected from " + clientSocket.getRemoteSocketAddress());

                // Start client thread
                final int clientId = clientCount;
                ClientThread clientThread = new ClientThread(clientId, clientSocket);
                clientThreads.add(clientThread);
                clientThread.startThread();
                clientCount++;
                
            } catch (IOException e) {
                if (isRunning) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Closes the server and all client connections.
     */
    public void closeServer() {
        isRunning = false;
        
        // Close all client threads
        for (ClientThread clientThread : clientThreads) {
            clientThread.stopThread();
        }
        clientThreads.clear();
        
        // Close server socket
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server has been closed");
            } else {
                System.out.println("Server is already closed!");
                System.out.println("Run startServer() to start server");
            }
        } catch (IOException e) {
            System.err.println("Error closing server: " + e.getMessage());
        }
    }

    /**
     * Gets the server's IP address.
     *
     * @return The server's IP address
     */
    private String getServerIp() {
        try {
            // Try to get IP by connecting to Google DNS
            try (Socket tempSocket = new Socket("8.8.8.8", 53)) {
                return tempSocket.getLocalAddress().getHostAddress();
            }
        } catch (IOException e) {
            try {
                // Fallback to local host address
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ex) {
                System.out.println("Could not establish IP, switched to 'localhost'");
                return "localhost";
            }
        }
    }
    

}