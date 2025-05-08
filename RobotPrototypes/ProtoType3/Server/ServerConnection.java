package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ServerConnection {

    private boolean serverRunning = false;
    private static int PORT;
    private static int BACKLOG;
    private DataInputStream dataFromClient;
    private DataOutputStream dataToClient;
    private ServerSocket serverSocket;

    public ServerConnection(int serverPort, int backlog){
        try{
            this.PORT = serverPort;
            this.BACKLOG = backlog;
            this.serverSocket = new ServerSocket(PORT, BACKLOG, InetAddress.getByName("0.0.0.0"));
        }catch(IOException e){
            System.out.println("Connection Error: " + e.getMessage());
        }
    }
    
    public void connectClients(){
        try{
            serverRunning = true;
            int clientCount = 0;
            while (serverRunning){
                Socket clientSocket = serverSocket.accept();
                clientCount++;
            }

        }catch(IOException e){
            System.out.println("Connection Error: " + e.getMessage());
            serverRunning = false;
        }
    }

    // public DataOutputStream toClient(){
    //     return dataToClient;
    // }

    // public DataInputStream fromClient(){
    //     return dataFromClient;
    // }

    public void closeConnection() throws IOException{
        if(serverSocket != null) serverSocket.close();
    }
}
