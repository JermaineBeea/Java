package ChatApp;

import java.io.IOException;
import java.net.Socket;

public class ClientSide {
    private static final int serverPort = 1100;

    public static void main(String[] args) {
        try(Socket serversocket = new Socket("localhost", serverPort))
        {

        }catch(IOException e){
            
        }
    }
}
