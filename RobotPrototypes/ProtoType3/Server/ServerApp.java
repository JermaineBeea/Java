package Server;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utility.LogConfig;

public class ServerApp {
    private static Logger logger = Logger.getLogger(ServerApp.class.getName());
    private static final int PORT = 9000;
    private static final int BACKLOG = 50;
    private static ServerConnection connection = new ServerConnection(PORT, BACKLOG);

    static {
        LogConfig.setUp(Level.OFF, false);
        logger.setLevel(Level.ALL);
    }

    public static void main(String[] args) {
        try{
            connection.runConnection();
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error runnig server connection", e);
        }
        
    }
}
