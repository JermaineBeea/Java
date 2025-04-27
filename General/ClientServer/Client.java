package ClientServer;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final int SERVERPORT = 9000;
    private static final Scanner consoleIn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Attempting connection to Server at port " + SERVERPORT + "...");
        Socket server = null;
        BufferedReader fromServer = null;
        PrintWriter toServer = null;

        try {
            server = new Socket("localhost", SERVERPORT);
            System.out.println("Connected successfully to: " + server.getInetAddress().getHostAddress());

            // Set up input-output stream resources.
            fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            toServer = new PrintWriter(server.getOutputStream(), true);
            
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
        } finally {
            try {
                if (fromServer != null) fromServer.close();
                if (toServer != null) toServer.close();
                if (server != null && !server.isClosed()) server.close();
                consoleIn.close();
            } catch(IOException e) {
                System.out.println("Error occurred while closing resources: " + e.getMessage());
            }
        }
    }
}