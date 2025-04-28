package ChatApp;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;

import static  ChatApp.ServerUtility.getServerIp;
import static ChatApp.ServerUtility.delayPrint;

public class ServerSide {

    private static final int serverPort = 1100;
    private static final int backLog = 50;
    private static final String serverIp = getServerIp();

    public static void main(String[] args) {
        System.out.println("Connecting to "+ serverIp + ":"+ serverPort+"...");

        try(ServerSocket server = new ServerSocket(serverPort, backLog, InetAddress.getByName("0.0.0.0")))
        {
            System.out.println("Connected Succesfully!\nWaiting for clients to connect...");
            
            int clientcount = 1;
            while(true)
            {
                // Create instance of client.
                Socket clientsocket = server.accept();
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