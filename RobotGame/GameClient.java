package RobotGame;

import java.net.Socket;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Client for the Robot Game that connects to a server.
 */
public class GameClient extends UtilityMethods
{
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9000;
    private static final int seconds = 2;
    private static final String EXIT_COMMAND = "exit";

    
    public static void main(String[] args) 
    {
        System.out.println("Connecting to server at " + SERVER_HOST + ":" + SERVER_PORT + "...");
        try (
            Socket server = new Socket("localhost", GameServer.PORT);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintStream toServer = new PrintStream(server.getOutputStream(), true);
            Scanner consoleIn = new Scanner(System.in)
        ) 
        {
            // Inform clients that they are connected
            delayPrint(seconds, "Connected Successfully!");
            delayPrint(seconds, "Welcome to Robot Game");
                        
            // Begin game
            delayPrint(seconds, "Let's begin the game!");

            // Main game loop
            String userInput;
            while (true) 
            {   
                delay(1);
                System.out.println("---------------------");
                System.out.println("\nEnter the direction and steps separated by space\nExample -> 'Right 3': ");
                System.out.print("Entry: ");
                userInput = consoleIn.nextLine();

                if (userInput != null && userInput.trim().equalsIgnoreCase(EXIT_COMMAND)) 
                {
                    System.out.println("Player has exited game");
                    toServer.println(EXIT_COMMAND);
                    break;
                }              
                // Send to server
                toServer.println(userInput);  
                
                // Recieve Robot status or Error Message from Server
                System.out.println(fromServer.readLine());
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}