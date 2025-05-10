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
        // Initialize with default configurations for all classes
        // Client package
        classConfigs.put("Client.ClientApp", GLOBAL_LEVEL);
        classConfigs.put("Client.ClientCodes", GLOBAL_LEVEL);
        classConfigs.put("Client.ClientConnection", GLOBAL_LEVEL);
        classConfigs.put("Client.ClientSession", GLOBAL_LEVEL);
        
        // RobotModules package
        classConfigs.put("RobotModules.Direction", GLOBAL_LEVEL);
        classConfigs.put("RobotModules.Position", GLOBAL_LEVEL);
        classConfigs.put("RobotModules.Robot", GLOBAL_LEVEL);
        classConfigs.put("RobotModules.RobotTypes", GLOBAL_LEVEL);
        classConfigs.put("RobotModules.World", GLOBAL_LEVEL);
        
        // Server package
        classConfigs.put("Server.Client", GLOBAL_LEVEL);
        classConfigs.put("Server.ClientSet", GLOBAL_LEVEL);
        classConfigs.put("Server.ServerApp", GLOBAL_LEVEL);
        classConfigs.put("Server.ServerCodes", GLOBAL_LEVEL);
        classConfigs.put("Server.ServerConnection", GLOBAL_LEVEL);
        classConfigs.put("Server.ServerSession", GLOBAL_LEVEL);
        classConfigs.put("Server.ServerThread", GLOBAL_LEVEL);
        
        // Utility package
        classConfigs.put("Utility.HandShake", GLOBAL_LEVEL);
        classConfigs.put("Utility.LogConfiguration", GLOBAL_LEVEL);
        
        // Keep original config entries
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