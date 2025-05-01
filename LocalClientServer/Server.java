package LocalClientServer;
import java.net.*;
import java.io.*;

public class Server {
    
    public static void main(String[] args) {
        System.out.println("Starting Server...");
        try (ServerSocket serversocket = new ServerSocket(1100)) {
            System.out.println("Server connected on port 1100!");
            int clientcount = 1;
            
            while (true) {
                System.out.println("Waiting for client " + clientcount + " to connect...");
                Socket clientsocket = serversocket.accept();
                System.out.println("Client " + clientcount + " connected from: " + 
                                  clientsocket.getInetAddress().getHostAddress());
                
                ClientThread clientThread = new ClientThread(clientsocket, clientcount);
                clientThread.startThread();
                
                clientcount++;
            }
        } catch(IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}