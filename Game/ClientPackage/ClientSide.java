package Game.ClientPackage;

import java.util.*;
import java.net.*;
import java.io.*;

public class ClientSide {
    
    public static void main(String[] args) {
        System.out.println("Connecting to Server.....");
        try (
            Socket serverSocket = new Socket("localhost", 3000);
            DataInputStream fromServer = new DataInputStream(serverSocket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(serverSocket.getOutputStream());
            Scanner consoleIn = new Scanner(System.in);
        ) {
            System.out.println("Connected to server!");

            Position pos = new Position();
            // Set initial position and direction
            pos.setPos(0, 0);
            pos.setDirection(Direction.NORTH);
            
            CommandProcessor commandProcessor = new CommandProcessor(pos);
            
            while (serverSocket.isConnected()) {
                System.out.println("\nEnter command separated by a space. E.g 'forward 3'");
                System.out.print("Enter command: ");
                String clientInput = consoleIn.nextLine();
                
                Object[] result = Utility.parseInput(clientInput);
                if (result != null) {
                    String command = (String) result[0];
                    double quantity = (double) result[1];
                    
                    commandProcessor.executeCommand(command, quantity);
                    
                    System.out.println("New position: (" + pos.getXpos() + ", " + pos.getYpos() + ")");
                    
                    // Return state to server
                    toServer.writeDouble(pos.getXpos());
                    toServer.writeDouble(pos.getYpos());
                    toServer.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}