package ServerPackage;

import java.io.IOException;

public class ServerGame {
    
    private boolean isRunning;
    private boolean isConnected;
    private final int EXIT_CODE = 500;
    private final int CLIENT_OK = 200;
    private ServerConnection connection;

    public ServerGame(ServerConnection instance){
        this.connection = instance;
        this.isRunning = false;
        this.isConnected = instance.isConnected;
    }

    public void run(){
        System.out.println("Client has begun robot game...");
        isRunning = true;

        while(isConnected && isRunning){
            isRunning = connection.handshake();
            try{

                // Recieve status code from client.
                int clientStatus = connection.dataFromClient.readInt();

                if(clientStatus == EXIT_CODE){
                    isRunning = false;
                    isConnected = false;
                    connection.closeConnection();
                }
            }catch(IOException e){
                System.out.println("Game Error: " + e.getMessage());
            }
        }
    }
}
