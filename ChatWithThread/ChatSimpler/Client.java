

import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client {

    private static final String EXITFLAG = "exit";
    private static final int serverPort = 3000;
    private static String serverIP;

    public static void main(String[] args) {
        serverIP = (args.length > 0) ? args[0] : "localhost";

        try {
            // Establish connection to the Server.
            Socket server = new Socket(serverIP, serverPort);
            System.out.println("You are connected to the Server: " + serverIP);
            System.out.println("You can now send messages to Server");

            // Set up input and output streams from the Server.
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter toServer = new PrintWriter(server.getOutputStream(), true);

            // Set up input stream from Console.
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            // Thread to receive messages from Server.
            Thread receiveThread = new Thread(() -> {
                try {
                    String inMessage;
                    while ((inMessage = fromServer.readLine()) != null) {
                        System.out.println("Server: " + inMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Error encountered when receiving messages from Server!");
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            // Main thread to send messages to Server.
            String outMessage;
            while (true) {
                outMessage = consoleIn.readLine();

                if (EXITFLAG.equalsIgnoreCase((outMessage == null) ? " " : outMessage.trim().toLowerCase())) {
                    System.out.println("CLIENT DISCONNECT: <<<DISCONNECTING FROM SERVER>>>");
                    break;
                } else {
                    // Send message to Server.
                    toServer.println(outMessage);
                }
            }

            // Close resources.
            server.close();
            fromServer.close();
            toServer.close();
            consoleIn.close();
            
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}