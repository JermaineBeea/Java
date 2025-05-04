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
            ObjectInputStream ObjectFromServer = new ObjectInputStream(serverSocket.getInputStream());
            ObjectOutputStream ObjectToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            Scanner consoleIn = new Scanner(System.in);
        ) {
            System.out.println("Connected to server!");
            System.out.println("Lets begin game!, waiting for Server to set game up....");

            ClientRobot robot = new ClientRobot();
            ClientCommands robotCommand = new ClientCommands(robot);

            //Retrieve robot default values.
            double xInitial = fromServer.readDouble();
            double yInitial = fromServer.readDouble();
            double fuelInitial = fromServer.readDouble();
            Direction direction = (Direction) ObjectFromServer.readObject();

            // Set up robot.
            robotCommand.setPos(xInitial, yInitial);
            robotCommand.setFuel(fuelInitial);
            robotCommand.setDirection(direction);
                        
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
                
                robotCommand.executeCommand(command, quantity);
                
                // Return state to server
                toServer.writeDouble(robotCommand.getXpos());
                toServer.writeDouble(robotCommand.getYpos());
                ObjectToServer.writeObject(robotCommand.getDirection());
                toServer.flush();
                ObjectToServer.flush();
            }
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}