package ClientPackage;

import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

import static UtilityModules.PrintMethods.delayPrint;
import static UtilityModules.PrintMethods.delaySlowPrint;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class ClientSide {

    private static final int SERVERPORT = 1100;
    private static final String SERVERIP = "localhost";

    public static void main(String[] args) {

        System.out.println("Establishing connection to Server at" + SERVERIP + ":" + SERVERPORT);

        try (Socket serversocket = new Socket(SERVERIP, SERVERPORT)) {
            // Set up input-ouuput resources
            DataInputStream fromServer = new DataInputStream(serversocket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(serversocket.getOutputStream());
            Scanner consoleIn = new Scanner(System.in);

            delayPrint(2000,"Connected Successfully!");
            delaySlowPrint(2000,100, "Welcome to the Server");
            // Prompt client for name
            delaySlowPrint(2000, 100, "\nEnter your name: ");
            String clientname = consoleIn.nextLine();
            delaySlowPrint(2000, 100, "Hi " + clientname);

            // Send client Name to Server.
            toServer.writeUTF(clientname);

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
