package ServerPackage;


import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SeverConnection {
    private static final int PORT = 1700;

    public void runConnection(){
        try(ServerSocket serverconnection = new ServerSocket(PORT, 50, InetAddress.getByName("0.0.0.0"))){
            System.out.println("\nEstablishing connection to Port " + PORT + "...");
            Socket clientconnection = serverconnection.accept();

            // Set up input-output resources
            DataInputStream dataFromServer = new DataInputStream(clientconnection.getInputStream());
            DataOutputStream dataToServer = new DataOutputStream(clientconnection.getOutputStream());
            BufferedReader strFromServer  = new BufferedReader(new InputStreamReader(clientconnection.getInputStream()));
            PrintWriter strToServer = new PrintWriter(clientconnection.getOutputStream(), true);

            // Recieve data from server
            String command = strFromServer.readLine();
            double quantity = dataFromServer.readDouble();

            
        }catch(IOException e){
            System.out.println("Error establlishing connection: " + e.getMessage());
        }
    }
    
}
