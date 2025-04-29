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

        delayPrint("Establishing connection to Server at (" + SERVERIP + ":" + SERVERPORT + ")");

        try (Socket serversocket = new Socket(SERVERIP, SERVERPORT)) {
            // Set up input-ouuput resources
            DataInputStream fromServer = new DataInputStream(serversocket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(serversocket.getOutputStream());
            Scanner consoleIn = new Scanner(System.in);

            delayPrint("\nConnected Successfully!");
            delayPrint(1000,100, "\n\nWelcome to the Server");

            // Prompt client for name.
            delayPrintWipe("\nEnter your name: ");
            String clientname = consoleIn.nextLine();

            // Send name to Server.
            toServer.writeUTF(clientname);
            
            delayPrintWipe("\nHi " + clientname + "!");
            delayPrint("\nDo you want to start the game?");
            delayPrint("\nType 'yes' to start game: ");

            String clientInput = consoleIn.nextLine();
            toServer.writeUTF(clientInput);

            if(!(clientInput.trim().toLowerCase().equals("yes"))){
                System.out.println();
                iteratingMessage(4, ".", "Exiting game");
            }else{
                //TODO: COMPLETE GAME IMPLEMENTATION
                System.out.println();
                while(true){
                    iteratingMessage(3, ".", "Starting game");
                }
            }
            // Send client Name to Server.
            toServer.writeUTF(clientname);

        } catch (IOException e) {
            delayPrint("\nClient Error: " + e.getMessage());
        }
    }
}
