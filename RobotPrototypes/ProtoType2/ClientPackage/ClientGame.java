package ClientPackage;

import java.util.Scanner;
import java.io.IOException;

public class ClientGame {
    
    private boolean isRunning;
    private boolean isConnected;
    private final String EXIT_FLAG = "exit";
    private final int SERVER_OK = 200;
    private final int SERVER_ERROR = 300;
    private ParseInput inputParser;
    private ClientConnection connection;

    ClientGame(ClientConnection instance){
        this.connection = instance;
        this.isRunning = false;
        this.isConnected = instance.isConnected;
    }

    public void run(){
        System.out.println("\nWelcome to the robot control game, let's begin!");
        isRunning = true;
        
        try(Scanner consoleIn = new Scanner(System.in)){
            while(isRunning && isConnected){
                System.out.println("\nPerforming handshake with server...");
                isRunning = connection.handshake();
                
                if (!isRunning) {
                    System.out.println("Handshake failed. Exiting game.");
                    break;
                }
                
                try{
                    System.out.println("\nEnter a command separated by a space, e.g 'right 4'");
                    System.out.println("Valid commands: rotateleft, rotateright, forward, backward, left, right");
                    System.out.println("Type 'exit' to exit game");
                    
                    String userInput = consoleIn.nextLine().trim();
                    if(userInput.toLowerCase().equals(EXIT_FLAG)){
                        System.out.println("Sending exit status to server...");
                        // Send status to server.
                        connection.dataToServer.writeInt(ClientStatus.STATUS_EXIT.code);
                        connection.dataToServer.flush();
                        System.out.println("Exiting game...");
                        isRunning = false;
                        isConnected = false;
                        continue;
                    }

                    // Parse input before sending anything to server
                    try {
                        inputParser = new ParseInput(userInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input: " + e.getMessage());
                        continue; // Skip the rest of this iteration and get new input
                    }
                    
                    String command = inputParser.getCommand();
                    double quantity = inputParser.getQuantity();
                    System.out.println("Parsed command: " + command + ", quantity: " + quantity);

                    // Send status to server
                    System.out.println("Sending OK status to server...");
                    connection.dataToServer.writeInt(ClientStatus.STATUS_OK.code);
                    connection.dataToServer.flush();
                    System.out.println("SENT CLIENT STATUS: " + ClientStatus.STATUS_OK.code);
                    
                    // Send data to server
                    System.out.println("Sending command string to server: " + command);
                    connection.strToServer.println(command);
                    // No need to flush here because PrintWriter was created with autoFlush=true
                    System.out.println("STRING SENT");
                    
                    System.out.println("Sending quantity to server: " + quantity);
                    connection.dataToServer.writeDouble(quantity);
                    connection.dataToServer.flush();
                    System.out.println("DOUBLE SENT");

                    // Wait for server response
                    System.out.println("Waiting for server status response...");
                    int serverStatus = connection.dataFromServer.readInt();
                    System.out.println("Received server status: " + serverStatus);

                    if (serverStatus == SERVER_OK) {
                        // Receive robot state from server.
                        System.out.println("Reading robot position data...");
                        double xPos = connection.dataFromServer.readDouble();
                        double yPos = connection.dataFromServer.readDouble();
                        int directionIndex = connection.dataFromServer.readInt();
                        double fuel = connection.dataFromServer.readDouble();

                        System.out.printf("\nRobot at position (%.2f, %.2f), facing %s, fuel: %.2f%n", 
                            xPos, yPos, Direction.getDirectionName(directionIndex), fuel);                    
                    
                    } else if(serverStatus == SERVER_ERROR){
                        String exceptionMessage = connection.strFromServer.readLine();
                        System.out.println("\nError from server: " + exceptionMessage);
                    } else {
                        System.out.println("SERVER ERROR: Unknown server status code: " + serverStatus);
                        isRunning = false;
                        connection.closeConnection();
                    }

                } catch (IOException e) {
                    System.out.println("\nConnection error: " + e.getMessage());
                    isRunning = false;
                    isConnected = false;
                } catch (Exception e) {
                    System.out.println("\nUnexpected error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Game ended.");
    }
}