package Server;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utility.HandShake;
import Utility.LogConfiguration;

public class ServerConnection {
    private static LogConfiguration logConfig = new LogConfiguration(ServerConnection.class.getName());
    private static Logger logger = logConfig.getLogger();
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final int PORT;
    private final int BACKLOG;

    // {logger.setLevel(Level.ALL);}

    /**
     * Initialise server connection.
     * @param serverPort
     * @param backlog
     */
    public ServerConnection(int serverPort, int backlog){
       this.PORT = serverPort;
       this.BACKLOG = backlog;
       runConnection();
    }

    /**
     * Run the server, and connect clients.
     */
    public void runConnection(){
        System.out.println();
        logger.info("Establishing server connection...");
        try{
            this.serverSocket = new ServerSocket(PORT, BACKLOG, InetAddress.getByName("0.0.0.0"));
            System.out.println();
            logger.info("Connected succesfuly to port " + PORT + "!");
                connectClients(); // Connect clients to server.
        }catch(Exception  e){
            closeConnection();
            logger.log(Level.SEVERE, "Connection Error: " + e);
            logConfig.printStack(e);
        }
    }

    /**
     * Method to handle connecting clients to server.
     */
    private void connectClients() throws Exception{
        int clientCount = 1;
        while (serverSocket.isBound() && !serverSocket.isClosed()){
            
            // Accept client connection.
            clientSocket = serverSocket.accept();
            System.out.println();
            logger.info("Client " + clientCount + "connected from: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                        
            //Establish connection test to client before running thread.
            HandShake handShake = new HandShake(clientSocket);
            System.out.println();
            logger.info("Connection Test: Send and recieve handshake from client...");
            handShake.sendHandshake();
            System.out.println();
            logger.info("Connection Test: Hanshake recieved!\nCreating thread for client.");

            // Run thread.
            clientCount++;
            final int clientId = clientCount;
            ServerThread serverThread = new ServerThread(clientId, clientSocket, serverSocket);
            serverThread.startThread();
        }
    }
    
    /**
     * Closes input and output resources.
     */
    public void closeConnection(){
        try{
            if(serverSocket != null) serverSocket.close();
            if(clientSocket != null) clientSocket.close();
        }catch(IOException e){
            logger.log(Level.SEVERE,"Error closing connections\n", e);
            logConfig.printStack(e);
        }
    }
}
