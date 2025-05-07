package ClientPackage;

import java.util.Scanner;

public class Game {
    
    private boolean isRunning;
    private final String EXIT_FLAG = "exit";
    private ParseInput inputParser;
    private ClientConnection connection;

    Game(ClientConnection instance){
        this.connection = instance;
        this.isRunning = false;
    }

    public void run(){
        System.out.println("\nWelcome to the robot control game, let's begin!");
        isRunning = true;
        
        try(Scanner consoleIn = new Scanner(System.in)){
            while(isRunning){
                try{
                    System.out.println("\nEnter a command separated by a space, e.g 'right 4'");
                    System.out.println("Valid commands: rotateleft, rotateright, forward, backward, left, right");
                    System.out.println("Type 'exit' to exit game");
                    
                    String userInput = consoleIn.nextLine().trim();
                    if(userInput.toLowerCase().equals(EXIT_FLAG)){
                        System.out.println("Exiting game...");
                        isRunning = false;
                        continue;
                    }
                    
                    inputParser = new ParseInput(userInput);
                    String command = inputParser.getCommand();
                    Double quantity = inputParser.getQuantity();

                    // Send data to server
                    connection.strToServer.println(command);
                    connection.dataToServer.writeDouble(quantity);
                    connection.strToServer.flush();
                    connection.dataToServer.flush();

                    // Wait for server status message code
                    int serverStatus = connection.dataFromServer.readInt();
                    if (serverStatus == 200){
                        System.out.println("Command executed successfully");
                    } else if(serverStatus == 500){
                        String exceptionMessage = connection.strFromServer.readLine();
                        System.out.println("Error: " + exceptionMessage);
                    }

                } catch(Exception e){
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}