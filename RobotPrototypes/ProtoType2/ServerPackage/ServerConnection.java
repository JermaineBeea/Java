package ServerPackage;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServerConnection {
    private static final int PORT = 1700;

    public void runConnection(){
        try(ServerSocket serverconnection = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))){
            System.out.println("Connection to Port " + PORT );
            System.out.println("Waiting for clients...");
            
            // Using try-with-resources for proper resource management
            try (Socket clientconnection = serverconnection.accept();
                 DataInputStream dataFromClient = new DataInputStream(clientconnection.getInputStream());
                 DataOutputStream dataToClient = new DataOutputStream(clientconnection.getOutputStream());
                 BufferedReader strFromClient = new BufferedReader(new InputStreamReader(clientconnection.getInputStream()));
                 PrintWriter strToClient = new PrintWriter(clientconnection.getOutputStream(), true)) {
                
                System.out.println("\nClient connected, starting game...");
                
                // Create robot for client.
                Robot robot = new Robot();
                Position robotPosition = robot.getPosition();
                CommandProcessor commandProcessor = new CommandProcessor(robot);

                while (true) {
                    try{
                        // Receive data from client
                        String command = strFromClient.readLine();
                        double quantity = dataFromClient.readDouble();

                        commandProcessor.executeCommand(command, quantity);

                        // Send status code to client.
                        dataToClient.writeInt(ServerStatus.STATUS_OK.code);

                        // Send robot status to client.
                        dataToClient.writeDouble(robotPosition.getX());
                        dataToClient.writeDouble(robotPosition.getY());
                        dataToClient.writeInt(robotPosition.getDirection().getIndex());
                        dataToClient.writeDouble(robot.getFuelAmount());

                        // Display robot status.
                        System.out.println("\nRobot is at (" + robotPosition.getX() + "," + robotPosition.getY() + ")");
                        System.out.println("Robot is facing " + robotPosition.getDirection());
                        System.out.println("Fuel amount is " + robot.getFuelAmount());
                    } catch(Exception e){
                        System.out.println("Client input error: " + e.getMessage());
                        dataToClient.writeInt(ServerStatus.STATUS_ERROR.code);
                        strToClient.println(e.getMessage());
                    }
                }
            }
        } catch(IOException e){
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }
}