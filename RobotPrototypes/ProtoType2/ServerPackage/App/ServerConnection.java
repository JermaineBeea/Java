package ServerPackage.App;

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
            System.out.println("Server socket created. Waiting for client connection...");
            clientSocket = serverSocket.accept();
            System.out.println("Client connected from: " + clientSocket.getInetAddress());
            
            // Create streams in the proper order
            dataFromClient = new DataInputStream(clientSocket.getInputStream());
            dataToClient = new DataOutputStream(clientSocket.getOutputStream());
            
            // Important: auto-flush is true for PrintWriter
            strFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            strToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            
            isConnected = true;
            newGame = new ServerGame(this);
            System.out.println("Server ready, starting game...");
        }catch(IOException e){
            System.out.println("Error establishing connection: " + e.getMessage());
            closeConnection();
        }
    }

    public void runGame(){
        try{
            newGame.run();
        }catch(Exception e){
            System.out.println("\nError during game execution: " + e.getMessage());
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }

    public void closeConnection(){
        try{
            // Close streams in proper order
            if(strFromClient != null) strFromClient.close();
            if(strToClient != null) strToClient.close();
            if(dataFromClient != null) dataFromClient.close();
            if(dataToClient != null) dataToClient.close();
            if(clientSocket != null) clientSocket.close();
            if(serverSocket != null) serverSocket.close();
        }catch(IOException e){
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }

    public boolean handshake() {
        try {
            // Attempts to read from client - will throw IOException if client disconnected
            int clientResponse = dataFromClient.readInt();
            System.out.println("Handshake request received: " + clientResponse);
            
            // If we get here, client is still connected
            dataToClient.writeInt(ServerStatus.HANDSHAKE_RESPONSE.code);
            dataToClient.flush(); // Ensure the int is sent immediately
            System.out.println("Handshake sent: " + ServerStatus.HANDSHAKE_RESPONSE.code);
            
            return true;
        } catch (IOException e) {
            System.err.println("Handshake Response Error: Client likely disconnected: " + e.getMessage());
            return false;
        }
    }
}