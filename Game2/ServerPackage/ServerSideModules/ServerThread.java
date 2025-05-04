package Game2.ServerPackage.ServerSideModules;

import java.util.concurrent.atomic.AtomicBoolean;
import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Game2.ServerPackage.RobotModules.*;

/**
 * Manages communication with a single client in a separate thread.
 * Handles robot state synchronization between client and server.
 */
public class ServerThread {
    
    private Socket clientsocket;
    private int clientId;

    private Thread thread;
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
     * Initializes a new server thread for a client connection.
     * @param clientId Unique identifier for the client
     * @param clientSocket Socket connection to the client
     */
    public ServerThread(int clientId, Socket clientSocket){
        this.clientId = clientId;
        this.clientsocket = clientSocket;
    }

    /**
     * Runnable task for the client thread.
     * Handles data exchange with the client and maintains robot state.
     */
    Runnable wrapperFunction = () -> {
        try{
            try(
                DataInputStream dataFromClient = new DataInputStream(clientsocket.getInputStream());
                DataOutputStream dataToClient = new DataOutputStream(clientsocket.getOutputStream());
                ObjectInputStream objectFromClient = new ObjectInputStream(clientsocket.getInputStream());
                ObjectOutputStream objectToClient = new ObjectOutputStream(clientsocket.getOutputStream());
            ){
                // Create robot instance for client
                ServerRobot serverRobot = new ServerRobot();
                ServerCommands serverCommand = new ServerCommands(serverRobot);

                // Send default values of robot properties to client.
                dataToClient.writeDouble(serverCommand.getXpos()); // send x initial posiiton
                dataToClient.writeDouble(serverCommand.getYpos()); // send y initial posiiton

                // Send Direction object as a serialised String.
                objectToClient.writeObject(serverCommand.getDirection().name());

                // Flush output stream
                dataToClient.flush();
                objectToClient.flush();

                // Begin the game.
                boolean clientConnected;
                while(isRunning.get() && clientsocket.isConnected()){
                    
                    // Test connection to client
                    clientConnected = ServerUtility.recieveHandshake(clientsocket);
                    if(!clientConnected){
                        System.err.println("Client Connection Error");
                        System.err.println("Closing client " + clientId + " connection, and stopping thread");
                        clientsocket.close();
                        this.stopThread();
                        break;
                    }
                    
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
                    System.out.print("\nclient "+ clientId + " " + serverCommand.getPosition());
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

    /**
     * Starts the client thread if it's not already running.
     */
    public void startThread(){
        if(thread != null && thread.isAlive()){
            return;
        }

        isRunning.set(true);

        thread = new Thread(wrapperFunction);
        thread.start();
    }

    /**
     * Stops the client thread and closes the socket connection.
     */
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