package Server;

import java.net.ServerSocket;
import java.net.Socket;
import Utility.LogModule;
import java.util.logging.Logger;

public class ServerHandler {
    private LogModule logMod = new LogModule(ServerHandler.class).launchLog(false, false);
    private Logger logger = logMod.getLogger();
    
    public ServerHandler(int clientId, Socket clientSocket, ServerSocket serverSocket){
    }
}
