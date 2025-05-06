package ClientPackage;

import java.net.Socket;
import java.io.IOException;

public class ClientSide {

    private static final int SERVER_PORT = 1700;
    private static final String SERVER_IP = "localhost";

    public static void runConnection(){

        System.out.println("Establishing connection to " + SERVER_IP + ":" + SERVER_PORT);

        try(Socket serverConnection = new Socket(SERVER_IP, SERVER_PORT);){
            new Game(serverConnection).run();
        }catch(IOException e){
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }
    
}
