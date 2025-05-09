package Server;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utility.LogModule;

public class ServerApp {
    private static LogModule logMod = new LogModule(ServerApp.class).launchLog(false, false);
    private static Logger logger = logMod.getLogger();
    
    private static final int PORT = 9000;
    private static final int BACKLOG = 50;
    static ServerConnection connection = new ServerConnection(PORT, BACKLOG);

    public static void main(String[] args) {
        try{
            connection.runConnection();
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error runnig server connection", e);
        }
        
    }
}
