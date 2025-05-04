package Game.ServerPackage.ServerSideModules;

import java.net.Socket;
import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

import ServerPackage.RobotModules.Robot;

public class ServerThread {
    
    private Socket clientsocket;
    private int clientId;

    private Thread thread;
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    public ServerThread(int clientId, Socket clientSocket){
        this.clientId = clientId;
        this.clientsocket = clientSocket;
    }

    Runnable wrapperFunction = () -> {
        try{
            try(
                DataInputStream dataFromClient = new DataInputStream(clientsocket.getInputStream());
                DataOutputStream dataToClient = new DataOutputStream(clientsocket.getOutputStream());
                ObjectInputStream objectFromClient = new ObjectInputStream(clientsocket.getInputStream());
                ObjectOutputStream ObjectToClient = new ObjectOutputStream(clientsocket.getOutputStream());
            ){
                // Create robot instance for client
                Robot clientRobot = new Robot();
                Commands command = new Commands(clientRobot);
                
                // Send default values to client
                dataToClient.writeDouble(command.getXpos());
                dataToClient.writeDouble(command.getYpos());
                ObjectToClient.writeDouble(command.getDirection());

            }catch(IOException ex){
                System.err.println("Server Error: " + ex.getMessage());
            }
        }catch(Exception e){
            System.err.println("Error running client thread: " + e.getMessage());
        }
    };

    public void startThread(){
        if(thread != null && thread.isAlive()){
            return;
        }

        isRunning.set(true);

        thread = new Thread(wrapperFunction);
        thread.start();
    }
}
