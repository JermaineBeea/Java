package Game2.ServerPackage.ServerSideModules;

import java.util.concurrent.atomic.AtomicBoolean;
import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Game2.ServerPackage.RobotModules.*;

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

                // Send default values of robot properties to client.
                dataToClient.writeDouble(serverCommand.getXpos()); // send x initial posiiton
                dataToClient.writeDouble(serverCommand.getYpos()); // send y initial posiiton

                // Send Direction object as a serialised String.
                objectToClient.writeObject(serverCommand.getDirection().name());

                // Flush output stream
                dataToClient.flush();
                objectToClient.flush();

                // Begin the game.
                while(isRunning.get() && clientsocket.isConnected()){
                    // Receive robot state from client.
                    double xPos = dataFromClient.readDouble(); // xPos
                    double yPos = dataFromClient.readDouble(); // yPos

                    // Receive direction as a string and convert back to server Direction enum
                    String directionName = (String) objectFromClient.readObject();
                    Direction direction = Direction.valueOf(directionName);
                    
                    // Update robot position and fuel amount.
                    serverCommand.updatePosition(xPos, yPos);
                    serverCommand.updateDirection(direction);
                    // View current Position of client robot.
                    serverCommand.viewPosition();
                }

            }catch(IOException ex){
                System.err.println("Server Thread Error: " + ex.getMessage());
                System.err.println("Closing client " + clientId + " connection, and stopping thread");
                ex.printStackTrace();
                this.stopThread();
            }
        }catch(Exception e){
            System.err.println("Error running client thread: " + e.getMessage());
            System.err.println("Closing client " + clientId + " connection, and stopping thread");
            e.printStackTrace();
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
            System.out.println("Error closing client connection: " + e.getMessage());
        }
    }
}