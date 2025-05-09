import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Handler;

public class LogModule{

    private Logger logger;
    private Logger rootLogger;
    private Level logLevel;
    private boolean printStack;

    public LogModule(Class<?> classObject){
        this.printStack = false;
        this.logLevel = Level.OFF;
        this.logger = Logger.getLogger(classObject.getName());
        this.rootLogger = Logger.getLogger("");
        this.setLogLevel(logLevel);
    }

    public Logger getLogger(){
        return logger;
    }

    public boolean loggingEnabled(){
        return !(logLevel == Level.OFF);
    }

    public void printStackTrace(Exception ex){
        if(printStack){
            ex.printStackTrace();
        }return;
    }

    public void setLogLevel(Level level){
        logLevel = level;
        logger.setLevel(logLevel);
        for(Handler handler : rootLogger.getHandlers()){
            handler.setLevel(logLevel);
        }
    }

    public void enablePrintStack(boolean enableArg){
        if(printStack == enableArg){
           logger.info("Print-Stack-Trace has already been set to: " + enableArg);
        }else{
            printStack = enableArg;
        }
    }

    public void enableLogging(boolean enableArg){
        if(enableArg == loggingEnabled()){
            logger.info("\nLogging has already been enabled  to: " + enableArg);
        }else if (enableArg){
            setLogLevel(Level.ALL);
        }else if(!enableArg){
            setLogLevel(Level.OFF);
        }
    }

}