import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server {
    

    public static void main(String[] args) throws IOException{
        
        try(ServerSocket serverconnection = new ServerSocket(3100, 50, InetAddress.getByName("0.0.0.0")))
        {   
            System.out.println("Connected, waiting for client...");
            Socket client = serverconnection.accept();

            //Set up input-output stram
            // Input and Output Stream
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);

            //Console input 
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            // Send message that client is connected
            System.out.println("Client connected from: " + client.getInetAddress().getHostAddress());
            toClient.println("Connected to Server!");

            // Send message to Client;
            String outMessage;
            while(!"exit".equalsIgnoreCase(
                (outMessage = consoleIn.readLine()) == null ? "": outMessage.trim()
            ))
            {
                toClient.println(outMessage);
                String inMessage = fromClient.readLine();
                System.out.println("Client: " + inMessage);
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
