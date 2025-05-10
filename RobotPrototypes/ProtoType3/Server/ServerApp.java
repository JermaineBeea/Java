package Server;
import java.util.logging.Level;
import java.util.logging.Logger;

import Client.ClientConnection;
import Utility.LogConfiguration;

public class ServerApp {
    private static LogConfiguration logConfig = new LogConfiguration(ServerApp.class.getName());
    private static Logger logger = logConfig.getLogger();
    
    private static final int PORT = 9000;
    private static final int BACKLOG = 50;
    private static ServerConnection connection = new ServerConnection(PORT, BACKLOG);


    public static void main(String[] args) {
        try{
            connection.runConnection();
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error runnig server connection", e);
        }
        
    }
}
