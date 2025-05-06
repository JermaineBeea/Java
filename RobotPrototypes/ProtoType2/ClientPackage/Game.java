package ClientPackage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Game {
    
    Boolean isRunning;
    private Socket serverConnection;
    private final String EXIT_FLAG = "exit";
    private ParseInput inputParser;

    Game(ClientConnection connection){
        this.isRunning = false;
    }

    public void run(){
        System.out.println("\nWelcome to the game, lets begin!");
        boolean isRunning = true;
        try(
            Scanner consoleIn = new Scanner(System.in);
            DataOutputStream dataToServer = new DataOutputStream(this.serverConnection.getOutputStream());
            PrintWriter strToServer = new PrintWriter(serverConnection.getOutputStream(), true)
            ){
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
                    strToServer.println(command);
                    dataToServer.writeDouble(quantity);

                    // Wait to

                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }catch(IOException e){
            System.out.println("Erro runnning game: " + e.getMessage());
        }
    }
}
