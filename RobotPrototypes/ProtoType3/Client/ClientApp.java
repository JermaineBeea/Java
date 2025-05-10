package Client;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utility.LogConfiguration;

public class ClientApp {
    private static LogConfiguration logConfig = new LogConfiguration(ClientApp.class.getName());
    private static Logger logger = logConfig.getLogger();

    private static final int SERVER_PORT = 9000;
    private static final String SERVER_IP = "localhost";
    static ClientConnection connection = new ClientConnection(SERVER_IP, SERVER_PORT);

    public static void main(String[] args) {
        try{
            connection.runConnection();
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error runnig client connection", e);
        } 
    }
}
