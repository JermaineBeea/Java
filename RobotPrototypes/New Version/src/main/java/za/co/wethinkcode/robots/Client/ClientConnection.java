package za.co.wethinkcode.robots.Client;

import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.wethinkcode.robots.Utility.LogConfiguration;

import java.net.Socket;
import java.io.IOException;

public class ClientConnection {
    private static LogConfiguration logConfig = new LogConfiguration(ClientConnection.class.getName());
    private static Logger logger = logConfig.getLogger();

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
        logger.info("Establishing connection to server at " + SERVER_IP + ":" + SERVER_PORT);
        try{
            this.serverSocket = new Socket(SERVER_IP, SERVER_PORT);
            establishConnection();
        }catch(Exception e){
            closeConnection();
            logger.log(Level.SEVERE, "Connection error", e);
            logConfig.printStack(e);
        }
    }

    private void establishConnection() throws Exception{
        System.out.println();
        logger.info("Successfully connected from: " + serverSocket.getInetAddress() + ":" + serverSocket.getPort());
        logger.info("Validation Test: Recieve and send handshake from server...");
        
        //Establish connection test to server.
        ClientSideHandshake handShake = new ClientSideHandshake(serverSocket);
        handShake.performHandshake();
        logger.info("Connection Test: Decrypted code sent!\n");

        // Begin onboarding process of client.
        new ClientSession(serverSocket);
    }

       /**
     * Closes input and output resources.
     */
    private void closeConnection(){
        try{
            if(serverSocket != null) serverSocket.close();
        }catch(IOException e){
            logger.log(Level.SEVERE,"Error closing connections", e);
            logConfig.printStack(e);
        }
    }
}
