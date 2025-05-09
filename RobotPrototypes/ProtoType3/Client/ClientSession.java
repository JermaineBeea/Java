package Client;

import java.util.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Utility.LogModule;

public class ClientSession {
    private LogModule logMod = new LogModule(ClientSession.class).launchLog(false, false);
    private Logger logger = logMod.getLogger();
    
    private static Socket serverSocket;

    public ClientSession(Socket serverSocketArg){
        this.serverSocket = serverSocketArg;
        runSession();
    }

    private void runSession(){
        // Begin onborading by recieving name from client.
        try(
            DataOutputStream toServer = new DataOutputStream(serverSocket.getOutputStream());
            DataInputStream fromServer = new DataInputStream(serverSocket.getInputStream());
        ){

        }catch(IOException e){

        }
    }
}
