package Client;

import java.net.Socket;
import java.util.logging.Logger;

import Utility.LogModule;

public class ClientHandler {
    private LogModule logMod = new LogModule(ClientHandler.class).launchLog(false, false);
    private Logger logger = logMod.getLogger();
    
    public ClientHandler(Socket serverSocket){

    }
}
