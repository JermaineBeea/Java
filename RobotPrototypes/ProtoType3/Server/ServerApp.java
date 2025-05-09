package Server;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerApp {
    private static Logger logger = Logger.getLogger(ServerApp.class.getName());
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
