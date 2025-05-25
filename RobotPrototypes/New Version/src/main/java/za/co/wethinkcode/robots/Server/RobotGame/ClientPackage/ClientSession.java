package za.co.wethinkcode.robots.server.RobotGame.ClientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.wethinkcode.robots.server.RobotGame.Utility.LogConfiguration;

public class ClientSession {
    private static final LogConfiguration logConfig = new LogConfiguration(ClientSession.class.getName());
    private static final Logger logger = logConfig.getLogger();

    private final String serverAddress;
    private final int serverPort;

    private Socket socket;
    private DataOutputStream dataToServer;
    private DataInputStream dataFromServer;

    private String clientName;

    public ClientSession(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 5000;

        System.out.println("Starting Robot Client...");
        ClientSession session = new ClientSession(serverAddress, serverPort);
        session.runSession();
        System.out.println("Client session ended. Goodbye!");
    }

    public void runSession() {
        boolean keepTrying = true;

        while (keepTrying) {
            try {
                boolean connected = connectToServer();

                if (!connected) {
                    System.out.println("Could not connect to the server.");
                    System.out.print("Would you like to try connecting again? (yes/no): ");
                    String answer = new Scanner(System.in).nextLine().trim().toLowerCase();
                    if (answer.equals("yes") || answer.equals("y")) continue;
                    else break;
                }

                ClientOnboarding onboarding = new ClientOnboarding(socket, dataToServer, dataFromServer);
                boolean registered = onboarding.executeRegistration();

                if (registered) {
                    clientName = onboarding.getClientName();
                    System.out.println("Welcome, " + clientName + "! Registration successful.");
                    startGameSession();
                    keepTrying = false;
                } else {
                    System.out.println("Registration failed.");
                    System.out.print("Try again? (yes/no): ");
                    String answer = new Scanner(System.in).nextLine().trim().toLowerCase();
                    if (!(answer.equals("yes") || answer.equals("y"))) {
                        keepTrying = false;
                    }
                    closeConnection();
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                logger.log(Level.SEVERE, "Unexpected error", e);
                closeConnection();
                System.out.print("Would you like to try again? (yes/no): ");
                String answer = new Scanner(System.in).nextLine().trim().toLowerCase();
                if (!(answer.equals("yes") || answer.equals("y"))) keepTrying = false;
            }
        }
        closeConnection();
    }

    private boolean connectToServer() {
        try {
            System.out.println("Connecting to " + serverAddress + ":" + serverPort + "...");
            socket = new Socket(serverAddress, serverPort);
            dataToServer = new DataOutputStream(socket.getOutputStream());
            dataFromServer = new DataInputStream(socket.getInputStream());

            System.out.println("Connected!");
            logger.info("Connected to server");
            return true;
        } catch (IOException e) {
            System.out.println("Connection failed: " + e.getMessage());
            logger.log(Level.SEVERE, "Connection error", e);
            return false;
        }
    }

    private void startGameSession() {
        try {
            System.out.println("-------------------------------------");
            System.out.println("Welcome to Robot Wars, " + clientName + "!");
            System.out.println("Game session is starting...");
            System.out.println("-------------------------------------");

            logger.info("Game session started for client: " + clientName);
            System.out.println("Game session started.");

        } catch (Exception e) {
            System.out.println("Game error: " + e.getMessage());
            logger.log(Level.SEVERE, "Game error", e);
        }
    }

    private void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("Connection closed.");
                logger.info("Connection closed");
            }
        } catch (IOException e) {
            System.out.println("Error closing connection: " + e.getMessage());
            logger.log(Level.WARNING, "Closing error", e);
        }
    }
}


