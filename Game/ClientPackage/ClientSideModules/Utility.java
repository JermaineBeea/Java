package Game.ClientPackage.ClientSideModules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class containing static helper methods for the client application.
 * Provides functionality for file handling and command parsing.
 */
public class Utility {
    private static final Logger LOGGER = Logger.getLogger(Utility.class.getName());

    /**
     * Test method to demonstrate the displayTextVertically functionality.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Path path = Paths.get("Game", "ClientPackage", "ClientSideModules", "Commands.txt");
        System.out.println(displayTextVertically(path));
    }

    /**
     * Reads a text file and returns its contents as a single string.
     * Empty lines are trimmed.
     *
     * @param path Path to the file to read
     * @return String containing the file contents, with each line separated by a newline
     */
    public static String displayTextVertically(Path path) {
        StringBuilder result = new StringBuilder();

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                line = line.trim();
                if (!line.isEmpty()) {
                    result.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading file: {0}", e.getMessage());
            return "Error reading file: " + e.getMessage();
        }

        return result.toString();
    }

    /**
     * Parses user input into a command and quantity.
     * Expected format is "command quantity" (e.g., "forward 5").
     *
     * @param input The user input string to parse
     * @return Object array where the first element is the command (String) and the second is the quantity (Double),
     *         or null if the input is invalid
     */
    public static Object[] parseInput(String input) {
        try {
            if (input == null || input.trim().isEmpty()) {
                LOGGER.log(Level.WARNING, "Error: Input is null or empty");
                System.err.println("Error: Input is null or empty");
                return null;
            }
            
            String[] split = input.trim().split("\\s+");
            if (split.length < 2) {
                LOGGER.log(Level.WARNING, "Error: Input must contain a command and a quantity");
                System.err.println("Error: Input must contain a command and a quantity");
                return null;
            }
            
            String command = split[0].toLowerCase();
            double quantity;
            
            try {
                quantity = Double.parseDouble(split[1]);
                if (quantity < 0) {
                    LOGGER.log(Level.WARNING, "Error: Quantity must be a positive value");
                    System.err.println("Error: Quantity must be a positive value");
                    return null;
                }
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Error: Quantity must be a numeric value");
                System.err.println("Error: Quantity must be a numeric value");
                return null;
            }

            // Check for extra parameters and warn if present
            if (split.length > 2) {
                LOGGER.log(Level.INFO, "Note: Additional parameters will be ignored: {0}", 
                          input.substring(input.indexOf(split[2])));
                System.out.println("Note: Additional parameters will be ignored");
            }

            return new Object[] {command, quantity};

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error parsing input: {0}", e.getMessage());
            System.err.println("Error parsing input: " + e.getMessage());
            return null;
        }
    }
}