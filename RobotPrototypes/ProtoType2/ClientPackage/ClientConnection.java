package ClientPackage;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class ClientConnection {

    private static Game newGame;
    private static final int SERVER_PORT = 1700;
    private static final String SERVER_IP = "localhost";

    public Socket serverconnection;
    public DataOutputStream dataToServer;
    public DataInputStream dataFromServer;
    public PrintWriter strToServer;
    public BufferedReader strFromServer;

    public void runConnection(){  
        System.out.println("\nEstablishing connection to " + SERVER_IP + ":" + SERVER_PORT + "...");
        try{
            serverconnection = new Socket(SERVER_IP, SERVER_PORT);
            dataToServer = new DataOutputStream(serverconnection.getOutputStream());
            dataFromServer = new DataInputStream(serverconnection.getInputStream());
            strToServer = new PrintWriter(serverconnection.getOutputStream(), true);
            strFromServer = new BufferedReader(new InputStreamReader(serverconnection.getInputStream()));
            System.out.println("Connected successfully"); 
            newGame = new Game(this);    
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
    
    private void closeConnection(){
        try {
            if(strFromServer != null) strFromServer.close();
            if(strToServer != null) strToServer.close();
            if(dataFromServer != null) dataFromServer.close();
            if(dataToServer != null) dataToServer.close();
            if(serverconnection != null) serverconnection.close();
        } catch (IOException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
}