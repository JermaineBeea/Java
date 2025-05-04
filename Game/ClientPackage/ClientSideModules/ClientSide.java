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
            DataOutputStream dataToServer = new DataOutputStream(serverSocket.getOutputStream());
            DataInputStream dataFromServer = new DataInputStream(serverSocket.getInputStream());
            ObjectOutputStream objectToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream objectFromServer = new ObjectInputStream(serverSocket.getInputStream());
            Scanner consoleIn = new Scanner(System.in);
        ) {
            System.out.println("Connected to server!");
            System.out.println("Waiting for Server to set game up....");

            ClientRobot robot = new ClientRobot();
            ClientCommands clientCommand = new ClientCommands(robot);

            System.out.println("Lets begin the game...");

            // Recieve robot types from server.
            String robotTypes = dataFromServer.readUTF();
            System.out.println(robotTypes);
            System.out.println("Pick a robot type by selecting its number:");
            System.out.println("Enter number");
            int robotIndex = Integer.parseInt(consoleIn.nextLine());
            // Send index of robot to Server
            dataToServer.writeInt(robotIndex);

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
                System.out.println(clientCommand.viewPos());

                // Return state to server
                dataToServer.writeDouble(clientCommand.getXpos());
                dataToServer.writeDouble(clientCommand.getYpos());
                // Send direction as a string name
                objectToServer.writeObject(clientCommand.getDirection().name());
                dataToServer.writeDouble(robot.fuelAmount); // Added missing fuel data
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