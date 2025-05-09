package Server;

import java.util.logging.Logger;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import Utility.LogModule;

public class ServerSession {
    private LogModule logMod = new LogModule(ServerSession.class).launchLog(false, false);
    private Logger logger = logMod.getLogger();

    private final int clientId;
    private final Socket clientSocket;
    private final Thread serveThread;
    
    public ServerSession(int clientIdArg, Socket clientSocketArg, Thread serverThreadArg){
        this.clientId = clientIdArg;
        this.clientSocket = clientSocketArg;
        this.serveThread = serverThreadArg;
        runSession();
    }

    private void runSession(){
        // Begin onborading by recieving name from client.
        try(
            DataInputStream fromClient = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream toClient = new DataOutputStream(clientSocket.getOutputStream());
        ){

        }catch(IOException e){
            
        }

    }
}
