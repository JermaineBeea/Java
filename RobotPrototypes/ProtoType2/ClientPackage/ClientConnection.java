package ClientPackage;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientConnection {

    private static Game newGame;
    private static final int SERVER_PORT = 1700;
    private static final String SERVER_IP = "localhost";

    pub;
    {  
        try{
            Socket serverconnection;
            DataOutputStream dataToServer;
        
        }catch(IOException e){
            System.out.println("Error connectiong to server: " + e.getMessage());
        }
        newGame = new Game(this);
    }
    
    public static void runConnection(){
        System.out.println("Establishing connection to " + SERVER_IP + ":" + SERVER_PORT);

        try{
            newGame.run();
        }catch(Exception e){
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }
}
