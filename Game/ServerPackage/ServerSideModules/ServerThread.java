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
                Commands command = new Commands(clientRobot);
                
                // Send default ste up values to client.
                dataToClient.writeDouble(command.getXpos());
                dataToClient.writeDouble(command.getYpos());
                dataToClient.writeDouble(command.getFuel());
                objectToClient.writeObject(command.getDirection());

                // Begin the game.
                while(isRunning.get() && clientsocket.isConnected()){
                // Recieve robot state from client and update locally.
                    dataFromClient.readDouble(); // xPos
                    dataFromClient.readDouble(); // yPos
                    dataFromClient.readDouble(); // fuel amount
                    objectFromClient.readObject(); // Direction
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
