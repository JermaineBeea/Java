package Chat;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Server application for a simple chat program.
 */
public class Server {
    private static final int SERVER_PORT = 2300;
    private static final AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader fromClient = null;
        PrintWriter toClient = null;
        Scanner keyInput = null;

        try {
            // Bind to all network interfaces
            serverSocket = new ServerSocket(SERVER_PORT, 50, InetAddress.getByName("0.0.0.0"));
            System.out.println("Server running on port " + SERVER_PORT + "!");
            System.out.println("Waiting for client connection...");

            // Accept client connection
            clientSocket = serverSocket.accept();
            String clientIp = clientSocket.getInetAddress().getHostAddress();
            System.out.println("Client connected from: " + clientIp);

            // Set up input-output streams with client
            fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            toClient = new PrintWriter(clientSocket.getOutputStream(), true);

            // Set up input stream from console
            keyInput = new Scanner(System.in);

            // Send welcome message to client
            toClient.println("You are connected to the server!");

            // Receive username from client
            String username = fromClient.readLine();
            System.out.println("Client username: " + username);
            System.out.println("Type your messages and press Enter to send.");

            // Create thread to receive messages from client
            Thread receiveThread = createClientListenerThread(fromClient, username);
            receiveThread.start();

            // Send messages to client from main thread
            String serverMessage;
            while (running.get() && (serverMessage = keyInput.nextLine()) != null) {
                if ("exit".equalsIgnoreCase(serverMessage.trim())) {
                    running.set(false);
                    toClient.println("<<SERVER_DISCONNECTING>>");
                    break;
                }
                toClient.println(serverMessage);
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            // Clean up resources
            try {
                if (keyInput != null) keyInput.close();
                if (toClient != null) toClient.close();
                if (fromClient != null) fromClient.close();
                if (clientSocket != null) clientSocket.close();
                if (serverSocket != null) serverSocket.close();
                System.out.println("Server shutdown complete.");
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    private static Thread createClientListenerThread(final BufferedReader fromClient, final String username) {
        return new Thread(() -> {
            try {
                String clientMessage;
                while (running.get() && (clientMessage = fromClient.readLine()) != null) {
                    if ("<<CLIENT_DISCONNECTING>>".equals(clientMessage)) {
                        System.out.println(username + " has disconnected.");
                        running.set(false);
                        break;
                    }
                    System.out.println(username + ": " + clientMessage);
                }
            } catch (IOException e) {
                if (running.get()) {
                    System.err.println("Client connection lost: " + e.getMessage());
                }
            } finally {
                running.set(false);
            }
        });
    }
}