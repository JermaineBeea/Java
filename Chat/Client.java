package Chat;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Client application for a simple chat program.
 */
public class Client {
    private static final int SERVER_PORT = 2300;
    private static final AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        String serverIp = (args.length > 0) ? args[0] : "localhost";
        Socket serverSocket = null;
        BufferedReader fromServer = null;
        PrintWriter toServer = null;
        Scanner keyInput = null;

        try {
            System.out.println("Connecting to server at " + serverIp + ":" + SERVER_PORT + "...");
            serverSocket = new Socket(serverIp, SERVER_PORT);
            System.out.println("Connected to server!");

            // Set up input-output streams to Server
            fromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            toServer = new PrintWriter(serverSocket.getOutputStream(), true);

            // Set up input stream from console
            keyInput = new Scanner(System.in);

            // Receive connected message from Server
            String serverMessage = fromServer.readLine();
            System.out.println("Server says: " + serverMessage);

            // Send client Name to Server
            System.out.print("Enter your name: ");
            String username = keyInput.nextLine();
            toServer.println(username);
            System.out.println("Welcome to the chat, " + username + "!");
            System.out.println("Type your messages below. Type 'exit' to quit.");

            // Create thread to receive messages from Server
            Thread receiveThread = createReceiveThread(fromServer);
            receiveThread.start();

            // Send keyInputs to Server in main thread
            String clientMessage;
            while (running.get() && (clientMessage = keyInput.nextLine()) != null) {
                if ("exit".equalsIgnoreCase(clientMessage.trim())) {
                    running.set(false);
                    toServer.println("<<CLIENT_DISCONNECTING>>");
                    break;
                }
                toServer.println(clientMessage);
            }
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } finally {
            // Clean up resources
            try {
                if (keyInput != null) keyInput.close();
                if (toServer != null) toServer.close();
                if (fromServer != null) fromServer.close();
                if (serverSocket != null) serverSocket.close();
                System.out.println("Disconnected from server.");
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    private static Thread createReceiveThread(final BufferedReader fromServer) {
        return new Thread(() -> {
            try {
                String serverMessage;
                while (running.get() && (serverMessage = fromServer.readLine()) != null) {
                    if ("<<SERVER_DISCONNECTING>>".equals(serverMessage)) {
                        System.out.println("Server has closed the connection.");
                        running.set(false);
                        break;
                    }
                    System.out.println("Server: " + serverMessage);
                }
            } catch (IOException e) {
                if (running.get()) {
                    System.err.println("Connection to server lost: " + e.getMessage());
                }
            } finally {
                running.set(false);
            }
        });
    }
}