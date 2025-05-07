import java.util.logging.Logger;
import java.util.logging.Level;

public class MyApp {
    private static final Logger logger = Logger.getLogger(MyApp.class.getName());

    public static void main(String[] args) {
        logger.info("This is an info message");
        logger.warning("This is a warning");
        logger.severe("This is a severe error message");

        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }
    }
}
