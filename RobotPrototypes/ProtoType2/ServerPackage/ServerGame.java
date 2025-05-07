package ServerPackage;

import java.io.IOException;

import ClientPackage.ClientStatus;

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
                    Robot clientRobot = new Robot();
                    Position robotPosition = clientRobot.getPosition();
                    CommandProcessor commandProcessor = new CommandProcessor(clientRobot);

                    // Recieve command from client.
                    String command = connection.strFromClient.readLine();
                    double quantity = connection.dataFromClient.readInt();    
                    
                    // Execute command.
                    commandProcessor.executeCommand(command, quantity);

                    // Send server status message.
                    connection.dataToClient.writeInt(ServerStatus.STATUS_OK.code);

                    // Send robot status to client.
                    connection.dataToClient.writeDouble(robotPosition.getX());
                    connection.dataToClient.writeDouble(robotPosition.getY());
                    connection.dataToClient.writeInt(robotPosition.getDirection().getIndex());
                    connection.dataToClient.writeDouble(clientRobot.getFuelAmount());
                    
                }else if(clientStatus == CLIENT_EXIT){
                    System.out.println("Client has exited the game!\nClosing game...");
                    isRunning = false;
                    connection.closeConnection();
                }else{
                    System.out.println("SERVER ERROR: Unknown server status, closing Game");
                    isRunning = false;
                    connection.closeConnection();
                }
            }catch(IOException e){
                System.out.println("Connection Error: " + e.getMessage());
            }catch(Exception ex){
                try{
                System.out.println("Client Input Exception Error" + ex.getMessage());
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
