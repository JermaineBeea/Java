import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Client {
    

    public static void main(String[] args) throws IOException{

        try(Socket server = new Socket("localhost", 3100))
        {
            // Input and Output Stream
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter toServer = new PrintWriter(server.getOutputStream(), true);

            //Console input 
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            // reciever welcome message from Server.
            System.out.println(fromServer.readLine());

            // Reciev messages from Server.
            String inMessage;
            while(!"exit".equalsIgnoreCase(
                (inMessage = fromServer.readLine()) == null ? "": inMessage.trim()
            ))
            {                   
                System.out.println("Server: " + inMessage);
                String outMessage = consoleIn.readLine();
                toServer.println(outMessage);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }

}
