package ServerPackage;

import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;

import static ClientPackage.ClientHandler.*;
import static UtilityModules.PrintMethods.*;

public class ServerSide {

    private static final int PORT = 1100;

    public static void main(String[] args) {

        System.out.println("Establishing connection to Server...");

        try(ServerSocket serversocket = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))){

            delayPrint(2000, "Successfully connected to Port: " + PORT);
            delayPrint(2000, "Waiting for clients to connect...");

            // Connect client with Server.
            int clientcount = 1;
            while (true) {
                Socket clientsocket = serversocket.accept();
                System.out.println("\nClient " + clientcount + ", is connected from: " + clientsocket.getRemoteSocketAddress());

                // Pass clientsocket, and client Id to clientHandler.
                final int clientId = clientcount;
                new Thread(()-> clientHandler(clientId, clientsocket)).start();
            }

        }catch(IOException e){
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
