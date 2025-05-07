package ServerPackage;

import java.io.IOException;

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
        System.out.println("robot game has begun...");
        isRunning = true;

        while(isConnected && isRunning){
            isRunning = connection.handshake();
            try{
                // Recieve status code from client.
                int clientStatus = connection.dataFromClient.readInt();

                if(clientStatus == CLIENT_OK){   
                    // Create robot for client.
                    Robot robot = new Robot();
                    Position robotPosition = robot.getPosition();
                    CommandProcessor commandProcessor = new CommandProcessor(robot);

                    // Recieve command from client.
                    String command = connection.strFromClient.readLine();
                    double quantity = connection.dataFromClient.readInt();    
                    
                    // Execute command.
                    commandProcessor.executeCommand(command, quantity);

                    double xPos = robotPosition.getX();
                    double yPos = robotPosition.getY();
                    Direction direction = robotPosition.getDirection();
                    double fuel = robot.getFuelAmount();

                    System.out.printf("\nRobot at position (%.2f, %.2f), facing %s, fuel: %.2f%n", xPos, yPos, direction, fuel);                    

                    // Send server status message.
                    connection.dataToClient.writeInt(ServerStatus.STATUS_OK.code);

                    // Send robot status to client.
                    connection.dataToClient.writeDouble(xPos); // Send X position.
                    connection.dataToClient.writeDouble(yPos); // send Y position.
                    connection.dataToClient.writeInt(direction.getIndex()); // Send direction index.
                    connection.dataToClient.writeDouble(fuel); // Send fuel amount.
                    
                }else if(clientStatus == CLIENT_EXIT){
                    System.out.println("\nClient has exited the game!\nClosing game...");
                    isRunning = false;
                    connection.closeConnection();
                }else{
                    System.out.println("\nSERVER ERROR: Unknown server status, closing Game");
                    isRunning = false;
                    connection.closeConnection();
                }
            }catch(IOException e){
                System.out.println("\nConnection Error: " + e.getMessage());
            }catch(Exception ex){
                try{
                System.out.println("\nClient Input Exception Error" + ex.getMessage());
                // Send exception to client
                connection.dataToClient.writeInt(ServerStatus.STATUS_ERROR.code);
                connection.strToClient.write(ex.getMessage());
                }catch(IOException m){
                    System.out.println("Connection Error: " + m.getMessage());
                }
            }
        }
    }
}
