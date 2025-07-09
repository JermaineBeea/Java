package com.application;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    Logger logger = Logger.getLogger(Client.class.getName());
    {
        logger.setLevel(Level.ALL);
    }

    private String IP;
    private int PORT;
    private volatile boolean running = true;

    public Client(int portArg, String IpArg){
        this.PORT = portArg;
        this.IP = IpArg;
    }

    public void runClient() {
        try (
            Socket socket = new Socket(IP, PORT);
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner consoleIn = new Scanner(System.in);
        ){
            System.out.println("Connected to server. Type 'quit' or 'exit' to disconnect.");
            
            // Thread for reading messages from server
            Thread readerThread = new Thread(() -> {
                try {
                    String inMessage;
                    while (running && (inMessage = fromServer.readLine()) != null) {
                        System.out.println("Server: " + inMessage);
                    }
                } catch (IOException e) {
                    if (running) {
                        System.out.println("Connection lost: " + e.getMessage());
                        logger.warning("Connection error: " + e.getMessage());
                    }
                } finally {
                    if (running) {
                        System.out.println("Server disconnected.");
                    }
                    running = false;
                }
            });
            
            readerThread.start();

            // Main thread for sending messages
            String outMessage;
            while (running) {
                System.out.print("You (client): ");
                if (consoleIn.hasNextLine()) {
                    outMessage = consoleIn.nextLine();
                    
                    // Allow graceful exit
                    if ("quit".equalsIgnoreCase(outMessage) || "exit".equalsIgnoreCase(outMessage)) {
                        System.out.println("Disconnecting from server...");
                        running = false;
                        break;
                    }
                    
                    // Check if still connected before sending
                    if (running) {
                        toServer.println(outMessage);
                    }
                } else {
                    // Console input closed
                    running = false;
                    break;
                }
            }
            
            // Wait for reader thread to finish
            try {
                readerThread.join(1000); // Wait up to 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warning("Interrupted while waiting for reader thread: " + e.getMessage());
            }
            
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
            logger.severe("Client error: " + e.getMessage());
        }
    }
}