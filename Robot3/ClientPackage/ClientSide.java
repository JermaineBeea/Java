package ClientPackage;

import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

import static UtilityModules.PrintMethods.delayPrint;
import static UtilityModules.PrintMethods.delaySlowPrint;
import static UtilityModules.PrintMethods.iteratingMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class ClientSide {

    private static final int SERVERPORT = 1100;
    private static final String SERVERIP = "localhost";

    public static void main(String[] args) {

        System.out.println("Establishing connection to Server at " + SERVERIP + ":" + SERVERPORT);

        try (Socket serversocket = new Socket(SERVERIP, SERVERPORT)) {
            // Set up input-ouuput resources
            DataInputStream fromServer = new DataInputStream(serversocket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(serversocket.getOutputStream());
            Scanner consoleIn = new Scanner(System.in);

            delayPrint(2000,"Connected Successfully!");
            delaySlowPrint(2000,100, "\nWelcome to the Server");
            // Prompt client for name
            delaySlowPrint(2000, 100, "\nEnter your name: ");
            String clientname = consoleIn.nextLine();
            
            delaySlowPrint(1000, 100, "\nHi " + clientname + "!");
            delaySlowPrint(1000, 100, "\nDo you want to start the game?");
            delaySlowPrint(500, 100, "\nType 'yes' to start game: ");

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
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
