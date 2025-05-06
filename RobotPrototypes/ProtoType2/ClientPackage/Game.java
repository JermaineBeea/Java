package ClientPackage;

import java.net.Socket;
import java.util.Scanner;

public class Game {
    
    Boolean isRunning;
    private Socket serverConnection;
    private final String EXIT_FLAG = "exit";
    private ParseInput inputParser;

    Game(Socket connection){
        this.serverConnection = connection;
        this.isRunning = false;
    }

    public void run(){
        System.out.println("\nWelcome to the game, lets begin!");
        boolean isRunning = true;
        try(Scanner consoleIn = new Scanner(System.in)){
            while(isRunning){
                try{
                    System.out.println("Enter a command seperated by a space, e.g 'Right 4'");
                    System.out.println("\nType 'exit' to exit game");
                    String userInput = consoleIn.nextLine();
                    if(userInput.trim().toLowerCase().equalsIgnoreCase(EXIT_FLAG)){
                        isRunning = false;
                    }
                    inputParser = new ParseInput(userInput);
                    
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
