import java.net.*;
import java.io.*;

public class Client {
    
    private final String serverIP;
    private final int serverPort;

    public Client(String IP, int port){
        this.serverIP = IP;
        this.serverPort = port;
    }

    public void runClient(){
        System.out.println("Connecting to Server...!");
        try(
            Socket server = new Socket(serverIP, serverPort);
            DataInputStream fromServer = new DataInputStream(server.getInputStream());
            DataOutputStream toServer = new DataOutputStream(server.getOutputStream());
        ){
            System.out.println("Connected to Server.");
        }catch(IOException e){
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
