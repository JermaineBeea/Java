package Game.ServerPackage.ServerSideModules;

import java.net.Socket;
import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

import Game.ServerPackage.RobotModules.*;

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
                ObjectOutputStream objectToClient = new ObjectOutputStream(clientsocket.getOutputStream());
            ){
                // Create robot instance for client
                Robot clientRobot = new Robot();
                ServerCommands serverCommand = new ServerCommands(clientRobot);
                
                // Send default ste up values to client.
                dataToClient.writeDouble(serverCommand.getXpos());
                dataToClient.writeDouble(serverCommand.getYpos());
                dataToClient.writeDouble(serverCommand.getFuel());
                objectToClient.writeObject(serverCommand.getDirection());

                // Begin the game.
                while(isRunning.get() && clientsocket.isConnected()){
                    // Recieve robot state from client.
                    double xPos = dataFromClient.readDouble(); // xPos
                    double yPos = dataFromClient.readDouble(); // yPos
                    double fuel = dataFromClient.readDouble(); // fuel amount
                    Direction direction = (Direction) objectFromClient.readObject(); // Direction
                    // Update robot position and fuel amount.
                    serverCommand.updatePos(xPos, yPos, direction);
                    serverCommand.updateFuel(fuel);
                    serverCommand.viewPosition();
                }

            }catch(IOException ex){
                System.err.println("Server Error: " + ex.getMessage());
                System.err.println("Closing client " + clientId + " connection, and stopping thread");
                this.stopThread();
            }
        }catch(Exception e){
            System.err.println("Error running client thread: " + e.getMessage());
            System.err.println("Closing client " + clientId + " connection, and stopping thread");
            this.stopThread();
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

    public void stopThread(){
        try{
            clientsocket.close();
            if(thread != null){
                isRunning.set(false);
                thread.interrupt();
            }
        }catch(IOException e){
            System.out.println("Error clsoing client onnection: " + e.getMessage());
        }
    }

}
