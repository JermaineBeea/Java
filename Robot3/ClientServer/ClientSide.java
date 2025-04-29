package ClientServer;

import static ClientServer.PrintMethods.delayPrint;

import java.io.*;
import java.net.*;

public class ClientSide {
    private static final int SERVERPORT = 1100;
    private static final String SERVERIP = "localhost";
    public static void main(String[] args) {
        System.out.println("Establishing connection to Server at" + SERVERIP + ":" + SERVERPORT);

        try (Socket serversocket = new Socket(SERVERIP, SERVERPORT)) {
            // Set up input-ouuput resources
            DataInputStream fromServer = new DataInputStream(serversocket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(serversocket.getOutputStream());

            System.out.println("Connected Successfully");
            delayPrint(true, 2, "Welcome to the Server!");


        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
