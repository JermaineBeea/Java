package ClientServer;

import java.net.*;
import java.io.*;

@SuppressWarnings("unused")
public class Server {

    private static Socket client;
    private static int clientcount = 0;
    private static BufferedReader fromClient;
    private static PrintStream toClient;
    public static void main(String[] args) throws InterruptedException{

        System.out.println("Connected to Port");
        System.out.println("Waiting for clients\n");

        try (ServerSocket server = new ServerSocket(3000)) {
            new Thread(() -> 
            {
            int clientCount = 0; // Local variable for thread safety
            while (true) 
            {
                try 
                (
                Socket client = server.accept();
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream toClient = new PrintStream(client.getOutputStream())
                ){

                clientCount++;
                toClient.println("Welcome client " + clientCount);

                }catch (IOException e) {
                    e.printStackTrace();
                    break; // Exit the loop if an exception occurs
                }
            }
            }).start();

        } catch (IOException e) {
        e.printStackTrace();
        }

    }
}