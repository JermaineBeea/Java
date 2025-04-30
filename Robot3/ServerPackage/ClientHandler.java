package ServerPackage;

import static UtilityModules.PrintMethods.*;

import java.io.*;
import java.net.*;

import ClientPackage.*;
import RobotPackage.*;

/**
 * The methods used in the ServerSide class.
 */
public class ClientHandler {

    /**
     * Handles a new client connection, creating a new client instance and starting message handling.
     * @param clientId The client Id, which is the order of the client being accepted.
     * @param clientSocket The client's socket connection.
     */
    public static void clientHandler(int clientId, Socket clientSocket) {

        delayPrintWipe("Waiting to recieve name from client " + clientId + "...");

        try(
            // Set up input-output resources.
            DataInputStream fromClient = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream toClient = new DataOutputStream(clientSocket.getOutputStream());
        ){
            // Retrieve name from client.
            String clientname = fromClient.readUTF();
            System.out.println(" ");
            delayPrint("Name recieved from client " + clientId);
            delayPrintWipe("Waiting for client response to start game...");

            // Recieve client answer if they wish to play game.
            String clientResponse = fromClient.readUTF();
            if(clientSocket.isConnected() && clientResponse.trim().toLowerCase().equals("yes")){
                // Create insance of Robot Class
            }else{
                delayPrintWipe("Client" + clientId + ", has been disconnected!");
                delayPrintWipe("");
                iteratingMessage(3, ".", "Closing client connecting");
                delayPrintWipe("Waiting for other clients...");
                if(clientSocket.isConnected()){
                    clientSocket.close();
                }
            }

            //=====================================
            // GAME SECTION
            //=====================================

            // Create an instance of the "Client" class, and Robot class.
            // Pass client instance to AllClients and to Game Handler.
            Client client = new Client(clientId, clientname, clientSocket);
            AllClients.addClient(client);
            new GameHandler(client).runGame();

        }catch(IOException e){
            delayPrint("\nClientHandler Error: " + e.getMessage());
        }
    }
    
}