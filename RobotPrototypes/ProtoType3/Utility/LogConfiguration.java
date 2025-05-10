package Utility;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogConfiguration {

    private final String className;
    private final Logger logger;
    
    private static Level GLOBAL_LEVEL = Level.INFO; 
    private static boolean GLOBAL_ENABLE_PRINT_STACK = false;
    
    private static final Map<String, Level> classConfigs = new HashMap<>();
    
    static {
        // Initialize with default configurations
        classConfigs.put("Practice.Class1", GLOBAL_LEVEL);
        classConfigs.put("Practice.Class2", GLOBAL_LEVEL);
    }
    
    public LogConfiguration(String className) {
        this.className = className;
        this.logger = Logger.getLogger(className);
        this.logger.setLevel(getConfig());
    }
    
    private Level getConfig() {
        return classConfigs.getOrDefault(className, GLOBAL_LEVEL);
    }
    
    public Logger getLogger() {
        return logger;
    }
    
    public Logger getLogger(Level level) {
        logger.setLevel(level);
        return logger;
    }
    
    public void printStack(Exception exception) {
        printStack(exception, GLOBAL_ENABLE_PRINT_STACK);
    }
    
    public void printStack(Exception exception, boolean enable) {
        if (enable) {
            exception.printStackTrace();
        }
    }

}