package ChatApp;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;

import static ChatApp.PrintMethods.*;
import static ChatApp.ServerMethods.*;

/**
 * Server side of the chat application.
 * Accepts client connections and manages communication.
 */
public class ServerSide {
    private static final int SERVERPORT = 1700;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        System.out.println("Starting chat server on port " + SERVERPORT + "...");

        try (
            ServerSocket server = new ServerSocket(SERVERPORT, 50, InetAddress.getByName("0.0.0.0"))
        ) {
            System.out.println("Server listening at " + server.getInetAddress().getHostAddress() + ":" + SERVERPORT);
            delayPrint(true, 1, "Waiting for clients to connect...");

            // Register shutdown hook to properly close server
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\nShutting down server...");
                isRunning = false;
            }));

            // Accept client connections, and create new client thread
            int clientCount = 1;
            while (isRunning) {
                try {
                    // Accept new client connection (blocks until a connection is made)
                    Socket clientSocket = server.accept();
                    System.out.println("Client " + clientCount + " connected from: " + clientSocket.getRemoteSocketAddress());
                    delayPrint(true, 1, "Waiting to receive name from client " + clientCount + "...");

                    // Send client-socket and clientID to clientHandler in a new thread
                    final int clientId = clientCount++;
                    new Thread(() -> clientHandler(clientId, clientSocket)).start();
                } catch (IOException e) {
                    if (isRunning) {
                        System.err.println("Error accepting client connection: " + e.getMessage());
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        } finally {
            System.out.println("Server shutdown complete.");
        }
    }
}