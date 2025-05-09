package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import Utility.LogModule;

public class ServerSession {
    private LogModule logMod = new LogModule(ServerSession.class).launchLog(false, false);
    private Logger logger = logMod.getLogger();
    
    public ServerSession(int clientId, Socket clientSocket, ServerSocket serverSocket){
    }
}
