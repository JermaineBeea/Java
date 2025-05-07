package ClientPackage;

import java.net.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class ClientConnection {

    private static final int SERVER_PORT = 1700;
    private static final String SERVER_IP = "localhost";
    private static ClientGame newGame;

    public boolean isConnected = false;
    public Socket serverSocket;
    public DataOutputStream dataToServer;
    public DataInputStream dataFromServer;
    public PrintWriter strToServer;
    public BufferedReader strFromServer;

    public void runConnection(){  
        System.out.println("\nEstablishing connection to " + SERVER_IP + ":" + SERVER_PORT + "...");
        try{
            serverSocket = new Socket(SERVER_IP, SERVER_PORT);
            
            dataToServer = new DataOutputStream(serverSocket.getOutputStream());
            dataFromServer = new DataInputStream(serverSocket.getInputStream());
            strToServer = new PrintWriter(serverSocket.getOutputStream(), true);
            strFromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            
            isConnected = true;
            newGame = new ClientGame(this);   
            System.out.println("Connected successfully"); 
        }catch(IOException e){
            System.out.println("\nError connecting to server: " + e.getMessage());
            closeConnection();
        }
    }
    
    public void runGame(){
        try{
            newGame.run();
        }catch(Exception e){
            System.out.println("\nError during game execution: " + e.getMessage());
        }finally{
            closeConnection();
        }
    }
    
    public void closeConnection(){
        try {
            if(serverSocket != null) serverSocket.close();
            if(strFromServer != null) strFromServer.close();
            if(strToServer != null) strToServer.close();
            if(dataFromServer != null) dataFromServer.close();
            if(dataToServer != null) dataToServer.close();
            if(serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }

    public boolean handshake() {
        try {
            // If we get here, client is still connected
            dataToServer.writeInt(ClientStatus.HANDSHAKE_RESPONSE.code);
            dataToServer.flush();

            // Attempts to read from client - will throw IOException if client disconnected
            dataFromServer.readInt();

            return true;
        } catch (IOException e) {
            System.err.println("Handshake Response Error: Server likely disconnected: " + e.getMessage());
            return false;
        }
    }
}