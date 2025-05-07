package ServerPackage;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServerConnection {
    private static final int PORT = 1700;
    private ServerGame newGame;
    

    public boolean isConnected = false;
    public ServerSocket serverSocket;
    public Socket clientSocket;
    public DataOutputStream dataToClient;
    public DataInputStream dataFromClient;
    public PrintWriter strToClient;
    public BufferedReader strFromClient;


    public void runConnection(){
        System.out.println("\nEstablishing connection to "+ PORT + "...");
        try{
        serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"));
        clientSocket = serverSocket.accept();
        
        dataFromClient = new DataInputStream(clientSocket.getInputStream());
        dataToClient = new DataOutputStream(clientSocket.getOutputStream());
        strFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        strToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        
        isConnected = true;
        newGame = new ServerGame(this);
        this.runGame();
        }catch(IOException e){
            System.out.println();
        }
    }

    public void runGame(){
        try{
            newGame.run();
        }catch(Exception e){
            System.out.println("nError during game execution: " + e.getMessage());
        }finally{
            closeConnection();
        }
    }

    public void closeConnection(){
        try{
            if(serverSocket != null) serverSocket.close();
            if(clientSocket != null) clientSocket.close();
            if(strFromClient != null) strFromClient.close();
            if(strToClient != null) strToClient.close();
            if(dataFromClient != null) dataFromClient.close();
            if(dataToClient != null) dataToClient.close();
            if(clientSocket != null) clientSocket.close();
        }catch(IOException e){
            System.out.println("Error closing resources: ");
        } finally{}
    }

    public boolean handshake() {
        try {
            // Attempts to read from server - will throw IOException if client disconnected
            dataFromClient.readInt();
            
            // If we get here, server is still connected
            dataToClient.writeInt(ServerStatus.HANDSHAKE_RESPONSE.code);
            dataToClient.flush();
            
            return true;
        } catch (IOException e) {
            System.err.println("Handshake Response Error: Client likely disconnected: " + e.getMessage());
            return false;
        }
    }
}