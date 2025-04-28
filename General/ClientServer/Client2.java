package ClientServer;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

@SuppressWarnings("unused")
public class Client2 {
    private static final int SERVERPORT = 9000;

    public static void main(String[] args) {
        System.out.println("Attempting connection to Server at port " + SERVERPORT + "...");
    
        try (
            Socket server = new Socket("localhost", SERVERPORT);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter toServer = new PrintWriter(server.getOutputStream(), true);
            Scanner consoleIn = new Scanner(System.in)
        ) {
            System.out.println("Connected successfully to: " + server.getInetAddress().getHostAddress());
            
            // Send name to Server.
            System.out.print("Enter name: ");
            String name = consoleIn.nextLine();
            toServer.println(name);
    
            // Send messages to Server
            System.out.println("Start typing messages (type 'exit' to quit):");
            String message;
            while (true) {
                message = consoleIn.nextLine();
                if (message.equalsIgnoreCase("exit"))
                    break;
                toServer.println(message);
            }
        } catch(IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}