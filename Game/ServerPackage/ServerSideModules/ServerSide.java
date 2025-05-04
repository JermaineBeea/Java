package Game.ServerPackage.ServerSideModules;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServerSide {

    private static final int PORT = 1700;
    public static void main(String[] args){

        System.out.println("Establishing server connection...");

        try(ServerSocket serversocket = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))){
            System.out.println("Connection established!");
            System.out.println("Waiting for clients...");

            int clientcount = 0;
            while(true){
                Socket clientsocket = serversocket.accept();
                clientcount++;
                final int clientId = clientcount;
                ServerThread clientThread = new ServerThread(clientId, clientsocket);
                clientThread.startThread();
                System.out.println("Client " + clientcount + " is connected.");
            }

        }catch(IOException e){
            System.err.println("Server Error: " + e.getMessage());
        }
    }
}
