package UtilityModules;

/**
 * Utility class for formatted console output with delays.
 */
public class PrintMethods {

    /**
     * Waits for a specified time, then prints a message character by character.
     * 
     * @param initialDelayMillis Initial delay in milliseconds before printing begins
     * @param charDelayMillis Delay in milliseconds between each character
     * @param outputString Message to be printed
     */
    public static void delaySlowPrint(int initialDelayMillis, int charDelayMillis, String outputString) {
        try {
            Thread.sleep(initialDelayMillis);
            printSlowly(charDelayMillis, outputString);
        } catch (InterruptedException e) {
            System.err.println("Error delaying print: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
    }

    /**
     * Print a message slowly, character by character
     * 
     * @param delayMillis Delay in milliseconds between characters
     * @param message Message to be printed
     */
    public static void printSlowly(int delayMillis, String message) {
        int strlenght = message.length();
        for (int n = 0; n < strlenght; n++) {
            System.out.print(message.charAt(n));
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        }
    }
    
    /**
     * Delays the program, then prints a message on a new line.
     * 
     * @param delayMillis Milliseconds of delay before printing message
     * @param outputString String to be printed
     */
    public static void delayPrint(int delayMillis, String outputString) {
        try {
            Thread.sleep(delayMillis);
            System.out.println(outputString);
        } catch (InterruptedException e) {
            System.err.println("Error delaying print: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
    }

    /**
     * Delays the program, then prints a message with or without a new line.
     * 
     * @param printLine If true, prints on a new line
     * @param delayMillis Milliseconds of delay before printing message
     * @param outputString String to be printed
     */
    public static void delayPrint(boolean printLine, int delayMillis, String outputString) {
        try {
            Thread.sleep(delayMillis);
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
     * 
     * @param delayMillis Milliseconds to display the message before clearing
     * @param message The message to display
     */
    public static void loopMessage(int delayMillis, String message) {
        System.out.print(message);
        try {
            Thread.sleep(delayMillis);
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
     * 
     * @param message The base message to display
     * @param character The character to repeat for animation
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