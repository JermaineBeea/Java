package Game2.ServerPackage.ServerSideModules;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;



/**
 * Main server application that listens for client connections.
 * Creates a new thread for each connected client.
 */
public class ServerSide {

    private static final int PORT = 1900;
    /**
     * Main entry point for the server application.
     * Establishes a server socket and handles incoming client connections.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args){

        System.out.println("Establishing server connection...");

        try(ServerSocket serversocket = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))){

            System.out.println("Connection established to " + ServerUtility.getServerIP() + ":" + PORT);

            int clientcount = 0;
            while(true){
                System.out.println("\nWaiting for client" + (clientcount + 1) + "...");
                // Accept new client connection
                Socket clientsocket = serversocket.accept();

                clientcount++;
                final int clientId = clientcount;

                // Create and start a new thread for the client
                ServerThread clientThread = new ServerThread(clientId, clientsocket);
                clientThread.startThread();

                System.out.println("Client " + clientcount + " is connected.");
            }

        }catch(IOException e){
            System.err.println("Server Error: " + e.getMessage());
        }
    }
}