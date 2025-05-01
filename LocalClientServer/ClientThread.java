package LocalClientServer;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientThread {

    private Socket clientSocket;
    private Thread thread;
    private int clientcount;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    public ClientThread(Socket clientSocket, int clientcount){
        this.clientSocket = clientSocket;
        this.clientcount = clientcount;
    }   

    Runnable wrapperfunction = () -> {
        try {
            System.out.println("Thread of client " + clientcount + ", is running...");
            DataInputStream fromClient = new DataInputStream(clientSocket.getInputStream());
            String inMessage;
            while(clientSocket.isConnected() && isRunning.get()){
                inMessage = fromClient.readUTF();
                System.out.println("Client " + clientcount + ": " + inMessage);
            }
        } catch(Exception e) {
            System.out.println("Error running thread function: " + e.getMessage());
            this.stopThread();
        }
    };

    public void startThread(){
        if(clientSocket.isConnected()){
            if(thread != null && thread.isAlive()){
                System.out.println("Thread has already been started!");
                return;
            }
            
            // Run thread and execute thread runnable.
            isRunning.set(true);

            // Create new thread.
            thread = new Thread(wrapperfunction);
            thread.start();
        } else {
            try {
                System.out.println("Client is not connected to Server");
                System.out.println("Closing client connection...");
                clientSocket.close();
            } catch(IOException e) {
                System.err.println("Error closing client connection: " + e.getMessage());
            }
            
            this.stopThread();
        }
    }

    public void stopThread(){
        if(thread != null){
            try {
                System.out.println("Closing client " + clientcount + " thread...");
                isRunning.set(false);
                thread.interrupt();
            } catch (Exception e) {
                System.out.println("Error stopping Thread: " + e.getMessage());
            }
        }
    }
}