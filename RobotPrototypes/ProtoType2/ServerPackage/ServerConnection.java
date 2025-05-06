package ServerPackage;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// import java.io.PrintWriter;

public class ServerConnection {
    private static final int PORT = 1700;

    public void runConnection(){
        try(ServerSocket serverconnection = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))){
            System.out.println("Connection to Port " + PORT );
            System.out.println("Waiting for clients...");
            Socket clientconnection = serverconnection.accept();
            System.out.println("\nClient connected, starting game...");
            
            // Set up input-output resources
            DataInputStream dataFromClient = new DataInputStream(clientconnection.getInputStream());
            DataOutputStream dataToClient = new DataOutputStream(clientconnection.getOutputStream());
            BufferedReader strFromClient  = new BufferedReader(new InputStreamReader(clientconnection.getInputStream()));
            // PrintWriter strToClient = new PrintWriter(clientconnection.getOutputStream(), true);

            // Create robot for client.
            Robot robot = new Robot();
            Position robotPosition = robot.getPosition();
            CommandProcessor commandProcessor = new CommandProcessor(robot);

            while (true) {
            // Recieve data from server
            String command = strFromClient.readLine();
            double quantity = dataFromClient.readDouble();

            commandProcessor.executeCommand(command, quantity);

            // Send status code to client.
            dataToClient.writeInt(200);

            // Display robot status.
            System.out.println("robot is at (" + robotPosition.getX() + "," + robotPosition.getY() + ")");
            System.out.println("Robot is facing " + robotPosition.getDirection());
            System.out.println("Fuel amount is " + robot.getFuelAmount());
        
            }
            
        }catch(IOException e){
            System.out.println("Error establlishing connection: " + e.getMessage());
        }
    }
    
}
