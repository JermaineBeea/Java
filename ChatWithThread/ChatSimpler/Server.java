

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server {

    private static final String EXITFLAG = "exit";
    private static final String serverIP = getServerIp();

    public static void main(String[] args) {
        
        try {
            // Establish Server connection.
            ServerSocket server = new ServerSocket(3000, 50, InetAddress.getByName("0.0.0.0"));
            System.out.println("Connected to server: " + serverIP);
            System.out.println("Waiting for client connection...");

            // Accept client connection to Server.
            Socket client = server.accept();
            System.out.println("Client is connected from: " + client.getInetAddress().getHostAddress());
            System.out.println("You can now send messages to client");

            // Set up input and output streams from the client.
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);

            // Set up input stream from Console.
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            // Thread to receive messages from Client.
            Thread receiveThread = new Thread(() -> {
                try {
                    String inMessage;
                    while ((inMessage = fromClient.readLine()) != null) {
                        System.out.println("Client: " + inMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Error encountered when receiving messages from Client!");
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            // Main Thread to send Messages to Client.
            String outMessage;
            while (true) {
                outMessage = consoleIn.readLine();

                if (EXITFLAG.equalsIgnoreCase((outMessage == null) ? "" : outMessage.trim().toLowerCase())) {
                    System.out.println("SERVER DISCONNECT: <<<DISCONNECTING FROM SERVER>>>");
                    break;
                } else {
                    // Send Message to Client.
                    toClient.println(outMessage);
                }
            }

            // Close resources.
            server.close();
            client.close();
            fromClient.close();
            toClient.close();
            consoleIn.close();

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    private static String getServerIp() {
        try {
            // Find non-local Ip address.
            Socket socket = new Socket("8.8.8.8", 53);
            String address = socket.getLocalAddress().getHostAddress();
            socket.close();
            return address;
        } catch (IOException e) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ex) {
                System.out.println("Unable to find Server Ip");
                ex.printStackTrace();
                return "localhost";
            }
        }
    }
}