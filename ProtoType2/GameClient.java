package ProtoType2;

import java.net.Socket;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Client for the Robot Game that connects to a server.
 * This class handles user input and communicates with the game server.
 * It sends move commands and displays the results to the user.
 */
public class GameClient extends UtilityMethods
{
    private static final String SERVER_HOST = "localhost";  // Server hostname
    private static final int SERVER_PORT = 9000;            // Server port 
    private static final int seconds = 2;                   // Delay for messages in seconds
    private static final String EXIT_COMMAND = "exit";      // Command to end the game

    /**
     * Main method that starts the client, connects to the server, and handles user input.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) 
    {
        System.out.println("Connecting to server at " + SERVER_HOST + ":" + SERVER_PORT + "...");
        try (
            // Establish connection with the game server
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

            // Main game loop - process user input until exit
            String userInput;
            while (true) 
            {   
                delay(1);
                System.out.println("---------------------");
                System.out.println("\nEnter the direction and steps separated by space\nExample -> 'Right 3': ");
                System.out.print("Entry: ");
                userInput = consoleIn.nextLine();

                // Check if user wants to exit
                if (userInput != null && userInput.trim().equalsIgnoreCase(EXIT_COMMAND)) 
                {
                    System.out.println("Player has exited game");
                    toServer.println(EXIT_COMMAND);
                    break;
                }              
                // Send user input to server
                toServer.println(userInput);  
                
                // Receive and display robot status or error message from server
                System.out.println(fromServer.readLine());
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}