package MutipleClients;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to chat server at " + SERVER_ADDRESS + ":" + SERVER_PORT);
            
            // Set up input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            // Get user's name
            Scanner scanner = new Scanner(System.in);
            String name = "";
            
            // Process server's first message (which should be SUBMIT_NAME)
            String serverMsg = in.readLine();
            if (serverMsg.equals("SUBMIT_NAME")) {
                System.out.print("Enter your name: ");
                name = scanner.nextLine();
                out.println(name);
            }
            
            // Start a thread to handle server messages
            Thread serverListener = new Thread(new ServerListener(in));
            serverListener.start();
            
            // Main thread handles sending messages
            System.out.println("Start typing messages. To exit, type 'exit'.");
            String message;
            while (true) {
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println(message);
            }
            
            // Clean up
            socket.close();
            scanner.close();
            System.exit(0);
            
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Thread class to handle incoming messages from the server
    private static class ServerListener implements Runnable {
        private BufferedReader in;
        
        public ServerListener(BufferedReader in) {
            this.in = in;
        }
        
        @Override
        public void run() {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    System.out.println(serverMessage);
                }
            } catch (IOException e) {
                System.err.println("Connection to server closed.");
            }
        }
    }
}
