package RobotGame;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Server for the Robot game that handles a single client connection.
 */
public class GameServer extends UtilityMethods
{
    private static final int seconds = 2;
    public static final int PORT = 9000;
    private static final String EXIT_COMMAND = "exit";
    private static final Game game = new Game();


    public static void main(String[] args) 
    {
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))) 
        {
            System.out.println("Server started on port " + PORT);
            delayPrint(seconds, "Waiting for client connection...");

            // Accept a single client connection
            try (
                Socket client = server.accept();
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream toClient = new PrintStream(client.getOutputStream(), true)
            ) 
            {
                // Begin game
                delayPrint(seconds, "Awaiting client input...");
                
                String direction;
                String steps;
                String clientInput;
                
                while (true) 
                {
                    try 
                    {
                        clientInput = fromClient.readLine();
                        
                        if (clientInput == null || EXIT_COMMAND.equals(clientInput)) 
                        {
                            System.out.println("Player has exited game");
                            break;
                        }

                        try
                        {
                            String[] split = clientInput.split(" ");
                            if(!(split.length == 2))
                            {
                                toClient.println("Invalid Input!: Use format 'Direction steps'");
                                System.out.println("\nClient has entered Invalid entry: " + clientInput);
                            }
                            else
                            {
                                // Get Direction and Steps.
                                direction = split[0];
                                steps = split[1];
                                
                                // Send input from client, then send back to client game output back to Client
                                toClient.println(game.playGame(direction, steps));
                                System.out.println("\nClient has entered: " + clientInput);
                            }
                        }
                        catch(Exception e)                
                        {
                            toClient.println("Invalid Input!: " + e.getMessage());
                            System.out.println("\nClient has entered Invalid entry: " + clientInput);
                        }   

                        
                    } 
                    catch (IOException e) 
                    {
                        System.err.println("Error processing client request: " + e.getMessage());
                        break;
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Server error: " + e.getMessage());
        }
    }

}

