package Robot1;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Server for the Robot game that handles a single client connection.
 * This class creates a socket server that listens for client commands to move the robot.
 * The server accepts commands in the format "Direction steps" (e.g., "Right 3").
 */
public class GameServer extends UtilityMethods
{
    private static final int seconds = 2;         // Delay time for messages in seconds
    public static final int PORT = 9000;          // Server port number
    private static final String EXIT_COMMAND = "exit";  // Command to terminate the connection
    private static final GameHandler game = new GameHandler();  // Game instance that manages the robot

    /**
     * Main method that starts the server and handles client connections.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) 
    {
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))) 
        {
            System.out.println("Server started on port " + PORT);
            delayPrint(seconds, "Waiting for client connection...");

            // Accept a single client connection
            try (
                Socket client = server.accept();  // Blocks until a client connects
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream toClient = new PrintStream(client.getOutputStream(), true)
            ) 
            {
                // Begin game
                delayPrint(seconds, "Awaiting client input...");
                
                String direction;  // Direction for robot movement
                String steps;      // Number of steps to move
                String clientInput;
                
                // Main server loop - processes client commands until exit
                while (true) 
                {
                    try 
                    {
                        clientInput = fromClient.readLine();
                        
                        // Check if client has disconnected or sent exit command
                        if (clientInput == null || EXIT_COMMAND.equals(clientInput)) 
                        {
                            System.out.println("Player has exited game");
                            break;
                        }

                        try
                        {
                            // Parse the client input - should be in format "Direction steps"
                            String[] split = clientInput.split(" ");
                            if(!(split.length == 2))
                            {
                                toClient.println("Invalid Input!: Use format 'Direction steps'");
                                System.out.println("\nClient has entered Invalid entry: " + clientInput);
                            }
                            else
                            {
                                // Get Direction and Steps
                                direction = split[0];
                                steps = split[1];
                                
                                // Process the game move and send result back to client
                                toClient.println(game.playGame(direction, steps));
                                System.out.println("\nClient has entered: " + clientInput);
                            }
                        }
                        catch(Exception e)                
                        {
                            // Handle any errors in processing client input
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