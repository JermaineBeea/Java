package ClientServer;

import java.net.*;
import java.io.*;

import static ClientServer.PrintMethods.*;
import static ClientServer.ServerMethods.*;
public class ServerSide {

    private static final int PORT = 1100;

    public static void main(String[] args) {

        System.out.println("Attempting connection to Server...");

        try(ServerSocket serversocket = new ServerSocket(PORT)){

            System.out.println("Successfully connected to Port: " + PORT);
            delayPrint(true, 2, "Waiting for clients to connect...");

            // Connect client with Server.
            int clientcount = 1;
            while (true) {
                Socket clientsocket = serversocket.accept();
                System.out.println("Client " + clientcount + ", is connected from: " + clientsocket.getRemoteSocketAddress());

                // Pass clientsocket, and client Id to clientHandler.
                final int clientId = clientcount;
                clientHandler(clientId, clientsocket);
            }

        }catch(IOException e){
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
