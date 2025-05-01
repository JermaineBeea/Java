import java.net.*;
import java.io.*;

public class Server {
    
    public static void main(String[] args) {
        System.out.println("Connecting Server...");
        try(ServerSocket serversocket = new ServerSocket(1100)){
            System.out.println("Server connected!");
            int clientcount = 1;
            while (true) {
                System.out.println("Waiting for client " + clientcount + ", to connect...");
                Socket clientsocket = serversocket.accept();
                new ClientThread(clientsocket, clientcount).startThread();;
                System.out.println("Client " + clientcount + ", is connected!");
                clientcount++;
            }
        }catch(IOException e){
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
