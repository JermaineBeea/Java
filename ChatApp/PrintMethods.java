package ChatApp;

/**
 * Utility class for formatted console output with delays.
 */
public class PrintMethods {

    /**
     * Delays the program before a message.
     * @param printLine If true, prints on a new line.
     * @param seconds Seconds of delay before printing message.
     * @param outputString String to be printed.
     */
    public static void delayPrint(boolean printLine, int seconds, String outputString) {
        try {
            Thread.sleep(seconds * 1000);
            if (printLine) {
                System.out.println(outputString);
            } else {
                System.out.print(outputString);
            }
        } catch (InterruptedException e) {
            System.err.println("Error delaying print: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
    }

    /**
     * Prints a message with a delay, then clears it.
     * @param delaySeconds Seconds to display the message before clearing.
     * @param message The message to display.
     */
    public static void loopMessage(int delaySeconds, String message) {
        System.out.print(message);
        try {
            Thread.sleep(delaySeconds * 1000);
        } catch (InterruptedException e) {
            System.err.println("Sleep error: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
        System.out.print("\r" + " ".repeat(message.length()) + "\r");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
    }

    /**
     * Displays an animated waiting message.
     * @param message The base message to display.
     * @param character The character to repeat for animation.
     */
    public static void waitingMessage(String message, String character) {    
        try {
            for (int n = 1; n <= 3; n++) {
                String currentMessage = message + character.repeat(n);
                System.out.print("\r" + currentMessage);
                Thread.sleep(300);
            }
            // Clear the line after completing the animation
            System.out.print("\r" + " ".repeat(message.length() + 3) + "\r");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
    }
}