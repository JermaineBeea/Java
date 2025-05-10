package Utility;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;

public class LogConfig {

    private static boolean printStack = true;

    public static void setUp(Level level, boolean enablePrintStack){
        printStack = enablePrintStack;
        setLevel(level);
    }

    private static void setLevel(Level level){
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(level);

        for(Handler handler : rootLogger.getHandlers()){
            rootLogger.removeHandler(handler);
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        consoleHandler.setLevel(level);
        rootLogger.addHandler(consoleHandler);
    }

    public static void  printStackTrace(Exception exception){
        if(printStack){
            exception.printStackTrace();
        }return;
    }
}
