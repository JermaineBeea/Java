package Client;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utility.LogModule;


public class ClientApp {
    private static LogModule logMod = new LogModule(ClientApp.class).launchLog(false, false);
    private static Logger logger = logMod.getLogger();
    
    private static String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9000;
    static ClientConnection connection = new ClientConnection(SERVER_IP, SERVER_PORT);

    public static void main(String[] args) {
        try{
            connection.runConnection();
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error runnig client connection", e);
        }
        
    }
}
