package ServerPackage;

import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;

import static ServerPackage.ClientHandler.*;
import static UtilityModules.PrintMethods.*;

public class ServerSide {

    private static final int PORT = 1100;

    public static void main(String[] args) {

        delayPrint("Establishing connection to Server...");

        try(ServerSocket serversocket = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))){

            delayPrint("\nSuccessfully connected to Port: " + PORT);
            delayPrintWipe("Waiting for clients to connect...");

            // Connect client with Server.
            int clientcount = 1;
            while (true) {
                Socket clientsocket = serversocket.accept();
                delayPrintWipe("Client " + clientcount + ", is connected from: " + clientsocket.getRemoteSocketAddress());

                // Pass clientsocket, and client Id to clientHandler.
                final int clientId = clientcount;
                new Thread(()-> clientHandler(clientId, clientsocket)).start();
                clientcount++;
            }

        }catch(IOException e){
            delayPrint("\nServerSide Error: " + e.getMessage());
        }
    }
}
