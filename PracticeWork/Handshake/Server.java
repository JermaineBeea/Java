import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.zip.DataFormatException;

public class Server {

    private final int backLog;
    private final int Port;

    public Server(int backLog, int Port){
        this.backLog = backLog;
        this.Port = Port;
    }
    
    public void runServer(){
        System.out.println("Establishing connnection...");
        try(ServerSocket server = new ServerSocket(Port, backLog)){
            Socket client = server.accept();
            System.out.println("Client Connected!");
            DataInputStream fromCleint = new DataInputStream(client.getInputStream());
            DataOutputStream toClient = new DataOutputStream(client.getOutputStream());
            
        }catch(IOException e){
            System.err.println("Server Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
