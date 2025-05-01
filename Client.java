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
        ){
            System.out.println("Connected Successfully!");
            String outMessage;
            while (serversocket.isConnected()) {
                System.out.print("You: ");
                outMessage = consoleIn.nextLine();
                toServer.writeUTF(outMessage);
            }
        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
