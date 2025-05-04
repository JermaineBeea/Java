package Game.ClientPackage.ClientSideModules;

import java.util.*;

import Game.ClientPackage.RobotModules.ClientCommands;
import Game.ClientPackage.RobotModules.ClientRobot;
import Game.ClientPackage.RobotModules.Direction;

import java.net.*;
import java.io.*;

public class ClientSide {
    
    private static final int PORT = 1700;

    public static void main(String[] args) {
        System.out.println("Connecting to Server.....");
        try (
            Socket serverSocket = new Socket("localhost", PORT);
            DataInputStream dataFromServer = new DataInputStream(serverSocket.getInputStream());
            DataOutputStream dataToServer = new DataOutputStream(serverSocket.getOutputStream());
            ObjectInputStream ObjectFromServer = new ObjectInputStream(serverSocket.getInputStream());
            ObjectOutputStream ObjectToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            Scanner consoleIn = new Scanner(System.in);
        ) {
            System.out.println("Connected to server!");
            System.out.println("Lets begin game!, waiting for Server to set game up....");

            ClientRobot robot = new ClientRobot();
            ClientCommands clientCommand = new ClientCommands(robot);

            //Retrieve robot default values.
            double xInitial = dataFromServer.readDouble();
            double yInitial = dataFromServer.readDouble();
            double fuelInitial = dataFromServer.readDouble();
            Direction direction = (Direction) ObjectFromServer.readObject();

            // Set up robot.
            clientCommand.setPos(xInitial, yInitial);
            clientCommand.setFuel(fuelInitial);
            clientCommand.setDirection(direction);
            
            System.out.println("Lets begin the game...");
            while (serverSocket.isConnected()) {

                Object result[] = {};
                while(true){                
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
                clientCommand.viewPos();

                // Return state to server
                dataToServer.writeDouble(clientCommand.getXpos());
                dataToServer.writeDouble(clientCommand.getYpos());
                ObjectToServer.writeObject(clientCommand.getDirection());
                dataToServer.flush();
                ObjectToServer.flush();
            }
        } catch (IOException e) {
            System.err.println("Client Connection error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}