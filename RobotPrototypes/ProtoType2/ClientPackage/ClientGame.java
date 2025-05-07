package ClientPackage;

import java.util.Scanner;

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
                isRunning = connection.handshake();
                try{
                    System.out.println("\nEnter a command separated by a space, e.g 'right 4'");
                    System.out.println("Valid commands: rotateleft, rotateright, forward, backward, left, right");
                    System.out.println("Type 'exit' to exit game");
                    
                    String userInput = consoleIn.nextLine().trim();
                    if(userInput.toLowerCase().equals(EXIT_FLAG)){
                        // Send status to server.
                        connection.dataToServer.writeInt(ClientStatus.STATUS_EXIT.code);
                        System.out.println("Exiting game...");
                        isRunning = false;
                        isConnected = false;
                        continue;
                    }

                    // Send status to server
                    connection.dataToServer.writeInt(ClientStatus.STATUS_OK.code);
                    
                    inputParser = new ParseInput(userInput);
                    String command = inputParser.getCommand();
                    double quantity = inputParser.getQuantity();

                    // Send data to server
                    connection.strToServer.println(command);
                    connection.dataToServer.writeDouble(quantity);
                    connection.strToServer.flush();
                    connection.dataToServer.flush();

                    // recieve server status message code
                    int serverStatus = connection.dataFromServer.readInt();

                        if (serverStatus == SERVER_OK) {
                            // Recieve robot state from server.
                            double xPos = connection.dataFromServer.readDouble();
                            double yPos = connection.dataFromServer.readDouble();
                            int directionIndex = connection.dataFromServer.readInt();
                            double fuel = connection.dataFromServer.readDouble();

                            System.out.printf("\nRobot at position (%.2f, %.2f), facing %s, fuel: %.2f%n", xPos, yPos, Direction.getDirectionName(directionIndex), fuel);                    
                        
                        } else if(serverStatus == SERVER_ERROR){
                        String exceptionMessage = connection.strFromServer.readLine();
                        System.out.println("\nError: " + exceptionMessage);
                        }else{
                            System.out.println("SERVER ERROR: Unknown server status, closing Game");
                            isRunning = false;
                            connection.closeConnection();
                        }

                } catch(Exception e){
                    System.out.println("\nError: " + e.getMessage());
                }
            }
        }
    }
}

