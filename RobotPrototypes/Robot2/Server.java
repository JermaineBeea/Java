package RobotPrototypes.Robot2;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.IOException;


public class Server extends UtilityMethods{
    private static final int SERVERPORT = 9000;
    private static final int SECONDS = 2;                   // Delay for messages in seconds

    public static void main(String[] args) {
        System.out.println("Attempting to connect to " + SERVERPORT + "...");
        try
        (
            ServerSocket server = new ServerSocket(SERVERPORT, 50, InetAddress.getByName("0.0.0.0"));
        )
        {   
            delayPrint(SECONDS, "Connected Successfuly!");
            delayPrint(SECONDS, "Waiting for Clients to connect...");
            int clientcount = 1;
            while(true)
            {
                Socket client = server.accept();
                delayPrint(SECONDS, "\nClient " + clientcount + ", is connected from: " + client.getRemoteSocketAddress());
                
                final int clientcountID = clientcount;
                new Thread(()-> new ClientHandler(client, clientcountID).runHandler()).start();

                clientcount++;
            }

        }catch(IOException e){
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
