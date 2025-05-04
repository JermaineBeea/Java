package Game.ClientPackage.ClientSideModules;

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Game.ClientPackage.RobotModules.*;

public class ClientSide {
    
    private static final int PORT = 1700;
    private static final Path path = Paths.get("Game", "ClientPackage", "ClientSideModules", "Commands.txt");
    static String displayOfCommands = Utility.displayTextVertically(path);
    
    public static void main(String[] args) {
        System.out.println("Connecting to Server.....");
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

            // Recieve robot types from server.
            String robotTypes = dataFromServer.readUTF();
            System.out.println("\n"+robotTypes);
            System.out.println("\nPick a robot type by selecting its number:");
            System.out.print("Enter number:");
            int robotIndex = Integer.parseInt(consoleIn.nextLine());

            // Send index of robot to Server
            dataToServer.writeInt(robotIndex);

            // Recieve default values of robot properties from server.
            double xInitial = dataFromServer.readDouble();// X initial position
            double yInitial = dataFromServer.readDouble();// Y initial position
            double rateInitial = dataFromServer.readDouble();// Rate of fuel usage
            double fuelInitial = dataFromServer.readDouble();// Fuel initilal amount

            // Receive direction as a string and convert back to client Direction enum
            String directionName = (String) objectFromServer.readObject();
            Direction directionInitial = (Direction) Direction.valueOf(directionName);

            // Set robot inital properties.
            clientCommand.setPosition(xInitial, yInitial);
            clientCommand.setDirection(directionInitial);
            clientCommand.setFuel(fuelInitial);
            clientCommand.setRateUsage(rateInitial);

            //Begin the game
            while (serverSocket.isConnected()) {

                Object result[] = {};
                while(serverSocket.isConnected()){  
                    System.out.println("\n" + displayOfCommands);        
                    System.out.println("\nEnter command separated by a space. E.g 'forward 3'");
                    System.out.print("Enter command: ");
                    String clientInput = consoleIn.nextLine();
                    result = Utility.parseInput(clientInput);
                    if(result != null){
                        break;
                    }
                }

                String command = (String) result[0];
                double quantity = (double) result[1];
                
                clientCommand.executeCommand(command, quantity);
                // View current Position.
                clientCommand.viewPosition();

                // Return state to server
                dataToServer.writeDouble(clientCommand.getXpos());
                dataToServer.writeDouble(clientCommand.getYpos());
                dataToServer.writeDouble(clientCommand.getFuel()); 

                // Send direction as a string name
                objectToServer.writeObject(clientCommand.getDirection().name());
                dataToServer.writeDouble(clientCommand.getFuel()); 

                // Flush output stream
                dataToServer.flush();
                objectToServer.flush();
            }
        } catch (IOException e) {
            System.err.println("Client Connection error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}