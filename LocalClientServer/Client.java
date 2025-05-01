package LocalClientServer;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {
        System.out.println("Connecting to Server...");
        try (
            Socket serversocket = new Socket("localhost", 1100);
            DataOutputStream toServer = new DataOutputStream(serversocket.getOutputStream());
            Scanner consoleIn = new Scanner(System.in);
        ) {
            System.out.println("Connected Successfully!");
            String outMessage;
            
            while (serversocket.isConnected()) {
                System.out.print("You: ");
                outMessage = consoleIn.nextLine();
                toServer.writeUTF(outMessage);
                
                // Add a way to exit the client
                if (outMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnecting from server...");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
        System.out.println("Client disconnected");
    }
}