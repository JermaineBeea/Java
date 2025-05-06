package ClientPackage;

import java.util.Scanner;

public class Game {
    
    private final String EXIT_FLAG = "exit";
    private ParseInput inputParser;
    private Scanner consoleIn = new Scanner(System.in);

    public void run(){
        System.out.println("\nWelcome to the game, lets begin!");
   
        System.out.println("\nEnter a command seperated by a space, e.g 'Right 4'");
        System.out.println("Type 'exit' to exit game");
        String userInput = consoleIn.nextLine();
        if(userInput.trim().toLowerCase().equalsIgnoreCase(EXIT_FLAG)){
            return;
        }
        inputParser = new ParseInput(userInput);
        String command = inputParser.getCommand();
        Double quantity = inputParser.getQuantity();
        }

}
