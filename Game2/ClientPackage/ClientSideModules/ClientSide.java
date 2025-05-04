package Game2.ClientPackage.ClientSideModules;

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Game2.ClientPackage.RobotModules.*;

/**
 * Main client application that connects to the server and handles user input.
 * Manages the client-server communication and robot control.
 */
public class ClientSide {
    
    private static final int PORT = 1900;
    private static final Path path = Paths.get("Game", "ClientPackage", "ClientSideModules", "Commands.txt");
    static String displayOfCommands = ClientUtility.displayTextVertically(path);
    
    /**
     * Main entry point for the client application.
     * Establishes server connection and handles user commands.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Connecting to Server...");
        try (
            Socket serverSocket = new Socket("localhost", PORT);
            DataOutputStream dataToServer = new DataOutputStream(serverSocket.getOutputStream());
            DataInputStream dataFromServer = new DataInputStream(serverSocket.getInputStream());
            ObjectOutputStream objectToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream objectFromServer = new ObjectInputStream(serverSocket.getInputStream());
            Scanner consoleIn = new Scanner(System.in);
        ) {
            System.out.println("Connected to server!");

            ClientRobot clientRobot = new ClientRobot();
            ClientCommands clientCommand = new ClientCommands(clientRobot);

            System.out.println("\nLets begin the game...");

            // Recieve default values of robot properties from server.
            double xInitial = dataFromServer.readDouble();// X initial position
            double yInitial = dataFromServer.readDouble();// Y initial position

            // Receive direction as a string and convert back to client Direction enum
            String directionName = (String) objectFromServer.readObject();
            Direction directionInitial = (Direction) Direction.valueOf(directionName);

            // Set robot inital properties.
            clientCommand.setPosition(xInitial, yInitial);
            clientCommand.setDirection(directionInitial);

            //Begin the game
            boolean running = true;
            boolean connecteToServer;
            while (running && serverSocket.isConnected()) {

                //Connection test
                    connecteToServer = ClientUtility.sendHandshake(serverSocket);
                    if(!connecteToServer){
                        System.err.println("Server Connection Error");
                        System.out.println("Shutting down Game...");
                        serverSocket.close();
                        break;
                    }

                    ClientUtility.delayRun(1300);
                    System.out.println("\n------------------------\n" + displayOfCommands);        
                    System.out.println("\nEnter command separated by a space. E.g 'forward 3'");
                    System.out.println("Type 'exit' to quit the game");
                    System.out.print("Enter command: ");
                    String clientInput = consoleIn.nextLine();
                    System.out.println("\n");
                
                try {
                    // Check for exit command
                    if (clientInput.trim().toLowerCase().equalsIgnoreCase("exit")) {
                        System.out.println("Exiting the game...");
                        running = false;
                        continue;
                    }
                    
                    Object[] result = ClientUtility.parseInput(clientInput);
                    String command = (String) result[0];
                    double quantity = (double) result[1];

                    // Execute the commands.
                    clientCommand.executeCommand(command, quantity);

                    // View current Position.
                    clientCommand.viewPosition();

                    // Return state to server
                    dataToServer.writeDouble(clientCommand.getXpos());
                    dataToServer.writeDouble(clientCommand.getYpos());
                    // Send direction as a string name
                    objectToServer.writeObject(clientCommand.getDirection().name());

                    // Flush output stream
                    dataToServer.flush();
                    objectToServer.flush();

                } catch (IllegalArgumentException e) {
                    // Handle specific input parsing errors
                    System.out.println("Input error: " + e.getMessage());
                } catch (Exception e) {
                    // If IO Exception break loop
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Client Connection error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}