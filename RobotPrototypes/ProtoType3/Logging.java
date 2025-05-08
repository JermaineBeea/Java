import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Handler;

public class Logging{

    private final Logger logger;
    private boolean enable;
    private Level level;

    public Logging(Class<?> classObject){
        this.logger = Logger.getLogger(classObject.getName());
        this.enable = true;
        this.level = Level.ALL;
    }

    public void diasablelog(){
        this.enable = false;
        this.level = Level.OFF;
    }

    public void setLevel(Level logLevel){
        if(enable){
            logger.setLevel(logLevel);
            Logger rootLogger = Logger.getLogger("");
            for(Handler handler : rootLogger.getHandlers()){
                handler.setLevel(logLevel);
            }
        }else{
            System.out.println("Logging has been set to off!");
        }
    }
}