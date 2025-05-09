package Server;

import java.util.logging.Logger;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        try(
            DataInputStream datafromClient = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream datatoClient = new DataOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ObjectFromClient = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream ObjectToClient = new ObjectOutputStream(clientSocket.getOutputStream());
        ){
        // Begin onborading by recieving name from client.
        
    }catch(IOException e){
            
        }

    }
}
