package com.application;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.net.*;

public class Server {

    Logger logger = Logger.getLogger(Server.class.getName());
    {
        logger.setLevel(Level.ALL);
    }

    private String IP;
    private int PORT;
    private volatile boolean running = true;

    public Server(int portArg, String IpArg){
        this.PORT = portArg;
        this.IP = IpArg;
    }
    
    public void runServer(){
        try(
            ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(IP));
        ){
            System.out.println("Server listening on " + IP + ":" + PORT);
            System.out.println("Waiting for client connection...");
            
            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from: " + clientSocket.getRemoteSocketAddress());
            System.out.println("Type 'quit' or 'exit' to shutdown server.");
            
            try(
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Scanner consoleIn = new Scanner(System.in);
            ){
                // Thread for reading messages from client
                Thread readerThread = new Thread(() -> {
                    try {
                        String inMessage;
                        while (running && (inMessage = fromClient.readLine()) != null) {
                            System.out.println("Client: " + inMessage);
                        }
                    } catch (IOException e) {
                        if (running) {
                            System.out.println("Client connection lost: " + e.getMessage());
                            logger.warning("Client connection error: " + e.getMessage());
                        }
                    } finally {
                        if (running) {
                            System.out.println("Client disconnected.");
                        }
                        running = false;
                    }
                });
                
                readerThread.start();

                // Main thread for sending messages to client
                String outMessage;
                while (running) {
                    System.out.print("You (Server): ");
                    if (consoleIn.hasNextLine()) {
                        outMessage = consoleIn.nextLine();
                        
                        // Allow graceful shutdown
                        if ("quit".equalsIgnoreCase(outMessage) || "exit".equalsIgnoreCase(outMessage)) {
                            System.out.println("Shutting down server...");
                            running = false;
                            break;
                        }
                        
                        // Check if still connected before sending
                        if (running) {
                            toClient.println(outMessage);
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
                System.out.println("Error with client connection: " + e.getMessage());
                logger.severe("Client connection error: " + e.getMessage());
            }
            
        } catch(IOException e){
            System.out.println("Error starting server: " + e.getMessage());
            logger.severe("Server startup error: " + e.getMessage());
        }
    }
}