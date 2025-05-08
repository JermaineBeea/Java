import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Handler;

public class Logging {

    private final Logger logger;
    private boolean enable;

    public Logging(Class<?> classObject) {
        this.logger = Logger.getLogger(classObject.getName());
        this.enable = true;
        this.logger.setLevel(Level.ALL);
        // Fix: Using the correct logger name instead of "null"
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.ALL);
        }
    }

    public Logger getLogger(){
        return logger;
    }

    public void enableLog(boolean enableArg) {
        if (enableArg && enable) {
            System.out.println("Logging is already enabled!");
        } else if (!enableArg) { // Fix: Logic was inverted here
            setLevel(Level.OFF);
            this.enable = false;
        } else {
            // Fix: This should handle when logging is disabled and we want to enable it
            setLevel(Level.ALL);
            this.enable = true;
            System.out.println("Logging has been enabled!");
        }
    }

    public void setLevel(Level logLevel) {
        if (enable) {
            logger.setLevel(logLevel);
            Logger rootLogger = Logger.getLogger("");
            for (Handler handler : rootLogger.getHandlers()) {
                handler.setLevel(logLevel);
            }
        } else {
            System.out.println("Logging has been set to off!\nTurn on to set Level");
        }
    }
}