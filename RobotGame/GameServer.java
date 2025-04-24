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
public class GameServer {
    public static final int PORT = 9000;
    private static final String EXIT_COMMAND = "exit";
    private static final Game game = new Game();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))) {
            System.out.println("Server started on port " + PORT);
            System.out.println("Waiting for client connection...");

            // Accept a single client connection
            try (
                Socket client = server.accept();
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream toClient = new PrintStream(client.getOutputStream(), true)
            ) {
                System.out.println("Client connected from: " + client.getInetAddress().getHostAddress());
                toClient.println("Welcome to Robot Game!");
                
                // Begin game
                System.out.println("Awaiting client input...");
                
                String direction;
                String steps;
                String clientInput;
                
                while (true) {
                    try {
                        clientInput = fromClient.readLine();
                        
                        if (clientInput == null || EXIT_COMMAND.equals(clientInput)) {
                            System.out.println("Player has exited game");
                            break;
                        }
                        
                        // Receive input from Client
                        direction = clientInput;
                        steps = fromClient.readLine();
                        
                        try {
                            // Send input to game
                            game.playGame(direction, steps);
                            
                            // Send game output to Client
                            toClient.println(game.getStatus());
                        } catch (IllegalArgumentException e) {
                            toClient.println("Error: " + e.getMessage());
                        } catch (Exception e) {
                            toClient.println("Unexpected error occurred");
                            System.err.println("Error processing client request: " + e.getMessage());
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading from client: " + e.getMessage());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}