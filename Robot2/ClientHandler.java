package Robot2;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Server for the Robot game that handles a single client connection.
 * This class creates a socket server that listens for client commands to move the robot.
 * The server accepts commands in the format "Direction steps" (e.g., "Right 3").
 */
public class ClientHandler extends UtilityMethods
{
    private Socket client;
    private String clientName;
    private static final int seconds = 2;         // Delay time for messages in seconds
    public static final int PORT = 9000;          // Server port number
    private static final String EXIT_COMMAND = "exit";  // Command to terminate the connection
    private static final GameHandler game = new GameHandler();  // Game instance that manages the robot

    public ClientHandler(Socket client, int clientID) {
        this.client = client;
        this.clientName = "Client " + String.valueOf(clientID);
    }

    /**
     * Main method that starts the server and handles client connections.
     * @param args Command line arguments (not used).
     */
    public void runHandler() 
    {
        try 
        {
            // Accept a single client connection
            try 
            (
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream toClient = new PrintStream(client.getOutputStream(), true)
            ) 
            {
                // Begin game
                delayPrint(seconds, "Waiting for " + clientName + " input...");
                
                String direction;  // Direction for robot movement
                String steps;      // Number of steps to move
                String clientInput;
                
                // Main server loop - processes client commands until exit
                while (client.isConnected()) 
                {
                    try 
                    {
                        clientInput = fromClient.readLine();
                        
                        // Check if client has disconnected or sent exit command
                        if (clientInput == null || EXIT_COMMAND.equals(clientInput)) 
                        {
                            System.out.println(clientName + " has exited game");
                            break;
                        }

                        try
                        {
                            // Parse the client input - should be in format "Direction steps"
                            String[] split = clientInput.split(" ");
                            if(!(split.length == 2))
                            {
                                toClient.println("Invalid Input!: Use format 'Direction steps'");
                                System.out.println("\n" + clientName + " has entered Invalid entry: " + clientInput);
                            }
                            else
                            {
                                // Get Direction and Steps
                                direction = split[0];
                                steps = split[1];
                                
                                // Process the game move and send result back to client
                                toClient.println(game.playGame(direction, steps));
                                System.out.println("\n" + clientName + " has entered: " + clientInput);
                            }
                        }
                        catch(Exception e)                
                        {
                            // Handle any errors in processing client input
                            toClient.println("Invalid Input!: " + e.getMessage());
                            System.out.println("\n" + clientName + " has entered Invalid entry: " + clientInput);
                        }   
                    } 
                    catch (IOException e) 
                    {
                        System.err.println("Error processing " + clientName + " request: " + e.getMessage());
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