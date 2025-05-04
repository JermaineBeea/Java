package Game2.ClientPackage.ClientSideModules;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Utility class providing helper methods for the client application.
 */
public class UtilityFunctions {

    /**
     * Test method to display the contents of the Commands.txt file.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Path path = Paths.get("Game", "ClientPackage", "ClientSideModules", "Commands.txt");
        System.out.println(displayTextVertically(path));
    }

    /**
     * Test if there is still connection to socket.
     * @param socket The socket being tested
     * @return True or False
     */
    public static boolean testConnection(Socket socket){
        try(
            DataOutputStream toSocket = new DataOutputStream(socket.getOutputStream())
        ){
            toSocket.writeInt(0);
            return true;
        }catch(IOException e){
            return false;
        }
    }

    /**
     * Delay the programs fro a specific given time.
     * @throws Interrupted Exception
     */
    public static void delayRun(int timeMilli){
        try{
            Thread.sleep(timeMilli);
        }catch(InterruptedException e){
            Thread.currentThread().isInterrupted();
        }
    }

    /**
     * Reads a text file and returns its contents as a single string.
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
            return "Error reading file: " + e.getMessage();
        }

        return result.toString();
    }

    /**
     * Parses user input for robot commands.
     * @param input String in format "command quantity" (e.g., "forward 3")
     * @return Object array containing the command string and quantity value
     * @throws IllegalArgumentException if input is invalid
     */
    public static Object[] parseInput(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input is null or empty");
        }
        
        String[] split = input.trim().split("\\s+");
        if (split.length < 2) {
            throw new IllegalArgumentException("Input must contain a command and a quantity");
        }
        
        String command = split[0].toLowerCase();
        double quantity;
        
        try {
            quantity = Double.parseDouble(split[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Quantity must be a numeric value");
        }

        return new Object[] {command, quantity};
    }
}