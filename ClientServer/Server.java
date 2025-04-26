package ClientServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server{
    private static final String IP = getServerIp();
    private static final int PORT = 9000;
    private static BufferedReader fromClient;
    private static PrintWriter toClient;

    public static void main(String[] args)
    {
        System.out.println("Attempting connection to " + IP + ":" + PORT + "...");

        try
        {
            ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"));
            System.out.println("Connected succesfully to Port " + PORT + "\nWaiting for Clients to connected...");

            // Main thread to accepts client connections.
            int clientcount = 1;
            while(true)
            {
                Socket client = server.accept();
                System.out.println("Client " + clientcount + ", is connected from: " + client.getPort());
                clientcount++;

                // Thread to handle each Client seperately.
                new Thread(()-> handleClient(client, clientcount)).start();
            }

        }catch(IOException e){
            System.out.println("Server Error: Unable to connect" + e.getMessage());
        }finally{
            try{
                server.close();
            }catch(IOException e){
                System.out.println("Could not close resources" + e.getMessage());
            }
        }
    }

    private static final void handleClient(Socket client, int clientcount)
    {   
        String name;
        String clientMessage;
        try
        {
            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toClient = new PrintWriter(client.getOutputStream(), true);

            // Retrieve name from client.
            name = fromClient.readLine();
            System.out.println(name + "[" + "client " + clientcount + "], is connected.");
            System.out.println("Waiting to recieve messages from " + name + "...");
            while(client.isConnected())
            {
                // Recive Messages from Client.
                clientMessage = fromClient.readLine();
                System.out.println("\n"+ name + "[" + "client " + clientcount + "]" + clientMessage);
            }

        }catch(IOException e){
            System.out.println("Error connecting client" + e.getMessage());
        }finally{
            try{ 
                fromClient.close();
                toClient.close();
            }catch(IOException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private static final String getServerIp()
    {
        try
        {
            // Find non-local Ip address.
            Socket serversocket = new Socket("8.8.8.8", 53);
            return serversocket.getLocalAddress().getHostAddress();
        }catch(IOException e){
           try{
            // Find local IP address.
                return InetAddress.getLocalHost().getHostAddress();
           }catch(UnknownHostException ex){
                System.out.println("Cannot find IP Address" + ex.getMessage());
           }
        }finally{
            try{
                serversocket.close();
                return "localhost";
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}