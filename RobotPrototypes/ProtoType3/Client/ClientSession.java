package Client;

import java.net.Socket;
import java.util.logging.Logger;

import Utility.LogModule;

public class ClientSession {
    private LogModule logMod = new LogModule(ClientSession.class).launchLog(false, false);
    private Logger logger = logMod.getLogger();
    
    public ClientSession(Socket serverSocket){

    }
}
