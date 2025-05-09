import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientApp {
    private static Logger logger = Logger.getLogger(ServerApp.class.getName());
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
