package Server;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ServerConnection {

    private boolean serverRunning = false;
    private static final int PORT = 9000;
    private DataInputStream dataFromClient;
    private DataOutputStream dataToClient;
    
    public void runServer(){
        try{
            serverRunning = true;
            while (serverRunning) {
                
            }
        }catch(IOException e){
            System.out.println("Connection Error: " + e.getMessage());
            serverRunning = false;
        }
    }

    public DataOutputStream toClient(){
        return dataToClient;
    }

    public DataInputStream fromClient(){
        return dataFromClient;
    }

    public void closeConnection(){

    }
}
