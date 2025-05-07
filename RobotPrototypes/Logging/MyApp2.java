import java.util.logging.*;

public class MyApp2 {
    private static final Logger logger = Logger.getLogger(MyApp2.class.getName());

    public static void setLogging(boolean enable) {
        Level level = enable ? Level.ALL : Level.OFF;
        logger.setLevel(level);

        // Also update handlers (console, file, etc.)
        for (Handler handler : Logger.getLogger("").getHandlers()) {
            handler.setLevel(level);
        }
    }

    public static void main(String[] args) {
        setLogging(false); // Set to true to enable logging

        logger.info("User should not see this message");
        logger.warning("Nor this one");
    }
}
