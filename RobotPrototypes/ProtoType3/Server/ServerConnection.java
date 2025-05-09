package Server;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utility.HandShake;
import Utility.LogModule;

public class ServerConnection {
    private final LogModule logMod = new LogModule(ServerConnection.class);
    private final Logger logger = logMod.getLogger();

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final int PORT;
    private final int BACKLOG;

    {
        logMod.enableLogging(true);
        logMod.enablePrintStack(true);
    }

    /**
     * Initialise server connection.
     * @param serverPort
     * @param backlog
     */
    public ServerConnection(int serverPort, int backlog){
       this.PORT = serverPort;
       this.BACKLOG = backlog;
    }

    /**
     * Run the server, and connect clients.
     */
    public void runConnection(){
        System.out.println();
        logger.info("Establishing server connection...");
        try{
            this.serverSocket = new ServerSocket(PORT, BACKLOG, InetAddress.getByName("0.0.0.0"));
            connectClients(); // Connect clients to server.
        }catch(IOException e){
            closeConnection();
            logger.log(Level.SEVERE, "Connection Error: " + e);
            logMod.printStackTrace(e);
        }
    }

    /**
     * Method to handle connecting clients to server.
     */
    private void connectClients() throws IOException{
        int clientCount = 0;
        while (serverSocket.isBound() && !serverSocket.isClosed()){
            
            // Accept client connection.
            clientSocket = serverSocket.accept();
            logger.info("Client" + clientCount + "connected from: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                        
            //Establish connection test to client before running thread.
            logger.info("Connection Test: Send and recieve handshake from client...\n");
            HandShake handShake = new HandShake(clientSocket);
            handShake.sendHandshake();
            logger.info("Connection Test: Hanshake recieved!\nCreating thread for client.");

            // Run thread.
            clientCount++;
            final int clientId = clientCount;
            ClientThread clientThread = new ClientThread(clientId, clientSocket, serverSocket);
            clientThread.startThread();
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
            logMod.printStackTrace(e);
        }
    }
}
