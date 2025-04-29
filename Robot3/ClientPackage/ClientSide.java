package ClientPackage;

import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

import static UtilityModules.PrintMethods.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class ClientSide {

    private static final int SERVERPORT = 1100;
    private static final String SERVERIP = "localhost";

    public static void main(String[] args) {

        delayPrintWipe("Establishing connection to Server at (" + SERVERIP + ":" + SERVERPORT + ")");

        try (
            Socket serversocket = new Socket(SERVERIP, SERVERPORT);
            Scanner consoleIn = new Scanner(System.in)
            ) {
            // Set up input-ouuput resources
            DataInputStream fromServer = new DataInputStream(serversocket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(serversocket.getOutputStream());

            delayPrint("\nConnected Successfully!");
            delayPrintWipe("Welcome to the Server!");

            // Prompt client for name.
            delayPrint("\nEnter your name: ");
            String clientname = consoleIn.nextLine();

            // Send name to Server.
            toServer.writeUTF(clientname);
            
            delayPrintWipe("\nHi " + clientname + "!");
            delayPrintWipe("Do you want to start the game?: ");
            // delayPrint("\nType 'yes' to start game: ");

            String clientInput = consoleIn.nextLine();
            toServer.writeUTF(clientInput);

            if(!(clientInput.trim().toLowerCase().equals("yes"))){
                System.out.println();
                iteratingMessage(4, ".", "Exiting game");
            }else{
                //TODO: COMPLETE GAME IMPLEMENTATION
                System.out.println();
                iteratingMessage(3, ".", "Starting game");
                
            }
            // Send client Name to Server.
            toServer.writeUTF(clientname);

        } catch (IOException e) {
            delayPrint("\nClientSide Error: " + e.getMessage());
        }
    }
}
