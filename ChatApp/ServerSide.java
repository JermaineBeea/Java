package ChatApp;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;

import static ChatApp.ServerUtility.delayPrint;
import static ChatApp.ServerUtility.getServerIP;

public class ServerSide {

    private static final int serverPort = 1100;
    private static final int backLog = 50;
    private static String serverIp;

    public static void main(String[] args) {
        System.out.println("Establishing Connection...");

        try(ServerSocket server = new ServerSocket(serverPort, backLog, InetAddress.getByName("0.0.0.0")))
        {   
            serverIp = getServerIP();
            System.out.println("Connected Succesfully to " + serverIp + ":"+ serverPort);
            delayPrint(1, "Waiting for clients to connect...");
            
            int clientcount = 1;
            while(true)
            {
                // Accept client.
                Socket clientsocket = server.accept();

                // Create instance of client.
                final int clientID = clientcount;
                new Client(clientID, clientsocket, "");
                
                System.out.println("Client " + clientcount + ", is connected!");
                delayPrint(1, "Waiting to recieve name from client " + clientcount + "...");
                clientcount++;
            }
            
        }catch(IOException e){
            System.out.println("Server Error: "+ e.getMessage());
        }
    }

}