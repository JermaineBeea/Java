package Practice;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleLogging {
    
    // Configure logging once at application startup
    public static void configureLogging(Level level) {
        // Get the root logger and set its level
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(level);
        
        // Remove existing handlers to avoid duplicates
        for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }
        
        // Add console handler with simple formatter
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(level);
        rootLogger.addHandler(handler);
    }
    
    // Example class with logging
    public static class Car {
        // Get logger with class name
        private static final Logger logger = Logger.getLogger(Car.class.getName());
        
        public void drive() {
            logger.info("Car is driving");
            
            // Different log levels
            logger.fine("This is a FINE level message");
            logger.warning("This is a WARNING level message");
        }
    }
    
    // Another example class
    public static class Methods {
        private static final Logger logger = Logger.getLogger(Methods.class.getName());
        
        public void performAction() {
            logger.info("Performing action in Methods class");
        }
    }
    
    // Main method to demonstrate usage
    public static void main(String[] args) {
        // Set up logging - only needs to be done once
        configureLogging(Level.OFF);  // Show all log messages
        
        // Use the classes
        Car car = new Car();
        car.drive();
        
        Methods methods = new Methods();
        methods.performAction();
        
        // Example of changing log level for a specific class
        Logger.getLogger(Car.class.getName()).setLevel(Level.WARNING);
        car.drive();  // Now only WARNING and above will be shown for Car
    }
}