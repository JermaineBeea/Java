package Server;

import java.net.ServerSocket;
import java.net.Socket;
import Utility.LogModule;

import java.util.logging.Logger;

public class ServerHandler {
    private LogModule logMod = new LogModule(ServerHandler.class);
    private Logger logger = logMod.launchLog(false, false);

    public ServerHandler(int clientId, Socket clientSocket, ServerSocket serverSocket){

    }
}
