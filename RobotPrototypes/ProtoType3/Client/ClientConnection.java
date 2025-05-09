package Client;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.io.IOException;

import Utility.HandShake;
import Utility.LogModule;

public class ClientConnection {
    private LogModule logMod = new LogModule(ClientConnection.class).launchLog(false, false);
    private Logger logger = logMod.getLogger();

    private Socket serverSocket;
    private final String SERVER_IP;
    private final int SERVER_PORT;

    /**
     * Initialise client connection to server.
     * @param serverIP
     * @param serverPort
     */
    public ClientConnection(String serverIP, int serverPort){
        this.SERVER_IP = serverIP;
        this.SERVER_PORT = serverPort;
    }

    /**
     * Run connection to server.
     */
    public void runConnection(){
        System.out.println();
        logger.info("Establishing connection to server at " + SERVER_IP + ":" + SERVER_PORT + "\n");
        try{
            this.serverSocket = new Socket(SERVER_IP, SERVER_PORT);
            establishConnection();
        }catch(IOException e){
            closeConnection();
            logger.log(Level.SEVERE, "Connection error", e);
            logMod.printStackTrace(e);
        }
    }

    private void establishConnection() throws IOException{
        logger.info("Successfully connected from: " + 
                    serverSocket.getInetAddress() + ":" + serverSocket.getPort() + "\n");
        logger.info("Connection Test: Recieve and send handshake from server...\n");
        
        //Establish connection test to server.
        HandShake handShake = new HandShake(serverSocket);
        handShake.recieveHandshake();
        logger.info("Connection Test: Hanshake sent!\n");

        // Begin onboarding process of client.
        new ClientHandler(serverSocket);
    }

       /**
     * Closes input and output resources.
     */
    private void closeConnection(){
        try{
            if(serverSocket != null) serverSocket.close();
        }catch(IOException e){
            logger.log(Level.SEVERE,"Error closing connections", e);
            logMod.printStackTrace(e);
        }
    }
}
