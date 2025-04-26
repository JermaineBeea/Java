package ClientServer;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class Client{
    private static final int SERVERPORT = 9000;
    private static BufferedReader fromServer;
    private static PrintWriter toServer;
    private static Scanner consoleIn = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("Attempting connection to Server at " + SERVERPORT + "...");

        try
        {
            Socket server = new Socket("localhost", SERVERPORT);
            System.out.println("Connected succesfully to: " + server.getInetAddress().getHostAddress());

            // Set up input-output stream resources.
            fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            toServer = new PrintWriter(server.getOutputStream(), true);
            
            // Send name to Server.
            System.out.print("Enter name: ");
            String name = consoleIn.nextLine();
            toServer.println(name);

            // Send messages to Server
            String message;
            while(((message == null) ? "": message.trim().toLowerCase()).equalsIgnoreCase("exit"))
            {
                toServer.println(message);
            }

        }catch(IOException e){
            System.out.println("Cleint Error: " + e.getMessage());
        }finally{
            try{
                serve.close()
            }catch(Exception e){
                System.out.println("Error occured while closing resources: " + e.getMessage());
            }
        }
    }
}