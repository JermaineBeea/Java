package ClientPackage;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    
    Boolean isRunning;
    private final String EXIT_FLAG = "exit";
    private ParseInput inputParser;
    private ClientConnection connection;

    Game(ClientConnection instance){
        this.connection = instance;
        this.isRunning = false;
    }

    public void run(){
        System.out.println("\nWelcome to the game, lets begin!");
        boolean isRunning = true;
        try(Scanner consoleIn = new Scanner(System.in)){
            while(isRunning){
                try{
                    System.out.println("\nEnter a command seperated by a space, e.g 'Right 4'");
                    System.out.println("Type 'exit' to exit game");
                    String userInput = consoleIn.nextLine();
                    if(userInput.trim().toLowerCase().equalsIgnoreCase(EXIT_FLAG)){
                        isRunning = false;
                    }
                    inputParser = new ParseInput(userInput);
                    String command = inputParser.getCommand();
                    Double quantity = inputParser.getQuantity();

                    // Send data to server.
                    connection.strToServer.println(command);
                    connection.dataToServer.writeDouble(quantity);

                    //Wait to for server status message code.
                    connection.dataFromServer.readInt();

                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

