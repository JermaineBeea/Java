package RobotGame0;

import java.net.Socket;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Client for the Robot Game that connects to a server.
 */
public class GameClient 
{
    private static final int seconds = 2;
    private static final String EXIT_COMMAND = "exit";

    /**
     * This static method is intended to delay the output messages.
     * @param seconds Time in seconds that programs sleeps for.
     */
    private static final void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) 
    {
        try (
            Socket server = new Socket("localhost", GameServer.PORT);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintStream toServer = new PrintStream(server.getOutputStream(), true);
            Scanner consoleIn = new Scanner(System.in)
        ) 
        {
            // Read welcome message from server if any
            String serverMessage1 = fromServer.readLine();
            String serverMessage2 = fromServer.readLine();
            System.out.println(serverMessage1);
            sleep(2);
            System.out.println(serverMessage2);
            
            // Begin game
            sleep(seconds);
            System.out.println("Let's begin the game!");

            // Main game loop
            String userInput;
            String direction;
            String steps;
            
            while (true) 
            {   
                sleep(seconds);
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
                
                try 
                {
                    String[] split = userInput.split(" ");
                    if (split.length != seconds) 
                    {
                        System.out.println("Incorrect input!");
                        continue;
                    }
                    
                    direction = split[0];
                    steps = split[1];
                    
                    // Send direction and steps to Server
                    toServer.println(direction);
                    toServer.println(steps);
                    
                    // Receive game status from Server
                    System.out.println(fromServer.readLine());
                } 
                catch (Exception e) 
                {
                    System.out.println("Incorrect input type! " + e.getMessage());
                }
            }

        } 
        catch (IOException e) 
        {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}