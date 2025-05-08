import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Handler;

public class Logging{

    private Logger logger;
    private boolean logEnabled;
    private Logger rootLogger;

    public Logging(Class<?> classObject){
        logEnabled = true;
        logger = Logger.getLogger(classObject.getName());
        rootLogger = Logger.getLogger("");
        setLevel(Level.ALL);
    }

    public Logger getLogger(){
        return logger;
    }

    public void enableLogging(boolean enableArg){
        if(enableArg && logEnabled){
            System.out.println("Logging has already been enabled");
        }else if (enableArg){
            setLevel(Level.ALL);
            logEnabled = true;
        }else if(!enableArg){
            setLevel(Level.OFF);
            logEnabled = false;
        }
    }

    public void setLevel(Level logLevel){
        logEnabled = true;
        logger.setLevel(logLevel);
        for(Handler handler : rootLogger.getHandlers()){
            handler.setLevel(logLevel);
        }
    }
}