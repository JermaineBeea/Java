package ChatApp;

import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import static ChatApp.PrintMethods.*;

/**
 * Client side of the chat application.
 * Handles connecting to server and sending/receiving messages.
 */
public class ClientSide {
    private static final int SERVERPORT = 1700;
    private static final String SERVERIP = "localhost";
    private static boolean isRunning = true;

    public static void main(String[] args) {
        System.out.println("Establishing connection to server...");

        try (
            // Set up input-output resources
            Socket serverSocket = new Socket(SERVERIP, SERVERPORT);
            DataInputStream fromServer = new DataInputStream(serverSocket.getInputStream());
            DataOutputStream toServer = new DataOutputStream(serverSocket.getOutputStream());
            Scanner consoleIn = new Scanner(System.in)
        ) {
            System.out.println("Connected successfully at: " + SERVERIP + ":" + SERVERPORT);

            // Prompt client for name, and send name to server
            delayPrint(false, 1, "Enter your name: ");
            String clientName = consoleIn.nextLine();
            toServer.writeUTF(clientName);
            System.out.println("Name sent to server!");
            
            // Start a thread to listen for messages from server
            Thread listenerThread = new Thread(() -> {
                try {
                    while (isRunning) {
                        String message = fromServer.readUTF();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    if (isRunning) {
                        System.out.println("Disconnected from server: " + e.getMessage());
                        isRunning = false;
                    }
                }
            });
            listenerThread.setDaemon(true);
            listenerThread.start();
            
            // Main thread handles user input
            System.out.println("Chat is ready! Type a message or '/exit' to quit.");
            while (isRunning) {
                String message = consoleIn.nextLine();
                
                // Check for exit command
                if (message.equalsIgnoreCase("/exit")) {
                    toServer.writeUTF("/exit");
                    isRunning = false;
                    System.out.println("Disconnecting from server...");
                    break;
                }
                
                // Send the message
                toServer.writeUTF(message);
            }

        } catch (IOException e) {
            System.err.println("Client Error: " + e.getMessage());
        } finally {
            System.out.println("Client shutdown complete.");
        }
    }
}