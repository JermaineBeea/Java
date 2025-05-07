package ServerPackage.App;

import java.io.IOException;

import ServerPackage.RobotModules.*;

public class ServerGame {
    
    private boolean isRunning;
    private boolean isConnected;
    private final int CLIENT_EXIT = 500;
    private final int CLIENT_OK = 200;
    private ServerConnection connection;

    public ServerGame(ServerConnection instance){
        this.connection = instance;
        this.isRunning = false;
        this.isConnected = instance.isConnected;
    }

    public void run(){
        System.out.println("Robot game has begun...");
        isRunning = true;

        // Create robot for client.
        Robot robot = new Robot();
        Position robotPosition = robot.getPosition();
        CommandProcessor commandProcessor = new CommandProcessor(robot);


        while(isConnected && isRunning){
            /* TODO Refactor
            System.out.println("\nPerforming handshake with client...");
            isRunning = connection.handshake();
            
            if (!isRunning) {
                System.out.println("Handshake failed. Exiting game.");
                break;
            }
            */
            
            
            try{
                // Receive status code from client.
                System.out.println("\nWaiting for client status...");
                int clientStatus = connection.dataFromClient.readInt();
                System.out.println("RECEIVED CLIENT STATUS: " + clientStatus);

                if(clientStatus == CLIENT_OK){   

                    // Recieve command value
                    System.out.println("\nWaiting for quantity double...");
                    double quantity = connection.dataFromClient.readDouble(); 
                    System.out.println("DOUBLE RECEIVED: " + quantity);   
                    
                    // Receive command from client.
                    System.out.println("\nWaiting for command string...");
                    String command = connection.strFromClient.readLine();
                    System.out.println("STRING RECEIVED: " + command);
                    
                    
                    // Execute command.
                    System.out.println("Executing command: " + command + " " + quantity);
                    commandProcessor.executeCommand(command, quantity);

                    double xPos = robotPosition.getX();
                    double yPos = robotPosition.getY();
                    Direction direction = robotPosition.getDirection();
                    double fuel = robot.getFuelAmount();

                    System.out.printf("\nRobot at position (%.2f, %.2f), facing %s, fuel: %.2f%n", 
                        xPos, yPos, direction, fuel);                    

                    // Send server status message.
                    System.out.println("Sending OK status to client...");
                    connection.dataToClient.writeInt(ServerStatus.STATUS_OK.code);
                    connection.dataToClient.flush();

                    // Send robot status to client.
                    System.out.println("Sending robot data to client...");
                    connection.dataToClient.writeDouble(xPos);
                    connection.dataToClient.writeDouble(yPos);
                    connection.dataToClient.writeInt(direction.getIndex());
                    connection.dataToClient.writeDouble(fuel);
                    connection.dataToClient.flush();
                    System.out.println("Robot data sent successfully");
                    
                } else if(clientStatus == CLIENT_EXIT){
                    System.out.println("\nClient has exited the game!\nClosing game...");
                    isRunning = false;
                    connection.closeConnection();
                    break;
                } else {
                    System.out.println("\nSERVER ERROR: Unknown client status: " + clientStatus);
                    isRunning = false;
                    connection.closeConnection();
                }
            } catch(IOException e){
                System.out.println("\nConnection Error: " + e.getMessage());
                isRunning = false;
                connection.closeConnection();
            } catch(Exception ex){
                try{
                    System.out.println("\nClient Input Exception Error: " + ex.getMessage());
                    ex.printStackTrace();
                    
                    // Send exception to client
                    System.out.println("Sending error status to client...");
                    connection.dataToClient.writeInt(ServerStatus.STATUS_ERROR.code);
                    connection.dataToClient.flush();
                    
                    connection.strToClient.println(ex.getMessage());
                    connection.strToClient.flush();
                    System.out.println("Error message sent to client");
                } catch(IOException m){
                    System.out.println("Connection Error: " + m.getMessage());
                    isRunning = false;
                    connection.closeConnection();
                }
            }
        }
        System.out.println("Game ended.");
    }
}