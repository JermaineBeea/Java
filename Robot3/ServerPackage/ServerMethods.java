package ServerPackage;

import java.io.*;
import java.net.*;
import java.util.*;

@SuppressWarnings("unused")
/**
 * The methods used in the ServerSide class.
 */
public class ServerMethods {

    /**
     * Handles a new client connection, creating a new client instance and starting message handling.
     * @param clientId The client Id, which is the order of the client being accepted.
     * @param clientSocket The client's socket connection.
     */
    public static void clientHandler(int clientId, Socket clientSocket) {

        System.out.println("Waiting to recieve name from client " + clientId + "...");

        try(
            // Set up input-output resources.
            DataInputStream fromClient = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream toClient = new DataOutputStream(clientSocket.getOutputStream());
        ){
            // Retrieve name from client.
            String clientname = fromClient.readUTF();
            System.out.println("Name recieved from client!");

        }catch(IOException e){
            System.out.println("ClientHandler Error: " + e.getMessage());
        }
    }
    
}