package za.co.wethinkcode.robots.Server;

import java.util.logging.Level;
import java.util.logging.Logger;

// import za.co.wethinkcode.flow.Recorder;
import za.co.wethinkcode.robots.Utility.LogConfiguration;


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
            logger.log(Level.SEVERE, "Error running server connection", e);
        }

        throw new UnsupportedOperationException( "TODO" );
    }

    // The following initialisation is REQUIRED for `flow` monitoring.
    // DO NOT REMOVE OR MODIFY THIS CODE.
    static {
        // new Recorder().logRun();
    }
}
