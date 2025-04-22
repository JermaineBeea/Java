import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client2 {
    public static void main(String[] args) {
        try (Socket server = new Socket("localhost", 3100)) {
            System.out.println("Connected to server at " + server.getInetAddress());
            
            // Input and Output Stream - note the true for auto-flush
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter toServer = new PrintWriter(server.getOutputStream(), true);

            // Console input 
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            // Receive welcome message from Server
            System.out.println("Server: " + fromServer.readLine());
            
            // Start communication loop
            String inputLine;
            while (true) {
                // Get input from user
                System.out.print("You: ");
                inputLine = consoleIn.readLine();
                
                // Send to server
                toServer.println(inputLine);
                
                // Check if we're done
                if ("exit".equalsIgnoreCase(inputLine.trim())) {
                    break;
                }
                
                // Get response from server
                String response = fromServer.readLine();
                System.out.println("Server: " + response);
                
                // Check if server wants to exit
                if ("exit".equalsIgnoreCase(response.trim())) {
                    System.out.println("Server has closed the connection.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error connecting to server: ");
            e.printStackTrace();
        }
        
        System.out.println("Client disconnected.");
    }
}