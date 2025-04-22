import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server2 {
    public static void main(String[] args) {
        try (ServerSocket serverConnection = new ServerSocket(3100, 50, InetAddress.getByName("0.0.0.0"))) {
            System.out.println("Server started on port 3100, waiting for client...");
            Socket client = serverConnection.accept();
            System.out.println("Client connected: " + client.getInetAddress());

            // Set up input-output streams
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);

            // Console input 
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            // Send welcome message
            toClient.println("Connected to Server!");
            
            // Communication loop
            String clientMessage;
            while (true) {
                // Get message from client
                clientMessage = fromClient.readLine();
                if (clientMessage == null) {
                    System.out.println("Client disconnected unexpectedly.");
                    break;
                }
                
                System.out.println("Client: " + clientMessage);
                
                // Check if client wants to exit
                if ("exit".equalsIgnoreCase(clientMessage.trim())) {
                    System.out.println("Client has requested to exit.");
                    break;
                }
                
                // Get server's response
                System.out.print("You: ");
                String response = consoleIn.readLine();
                
                // Send response to client
                toClient.println(response);
                
                // Check if server wants to exit
                if ("exit".equalsIgnoreCase(response.trim())) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Server error:");
            e.printStackTrace();
        }
        
        System.out.println("Server shutting down.");
    }
}