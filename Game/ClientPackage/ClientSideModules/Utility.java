package Game.ClientPackage.ClientSideModules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Utility {

    public static void main(String[] args) {
        Path path = Paths.get("Game", "ClientPackage", "ClientSideModules", "Commands.txt");
        System.out.println(displayTextVertically(path));
    }

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


    public static Object[] parseInput(String input) {
        try {
            if (input == null || input.trim().isEmpty()) {
                System.err.println("Error: Input is null or empty");
                return null;
            }
            
            String[] split = input.trim().split("\\s+");
            if (split.length < 2) {
                System.err.println("Error: Input must contain a command and a quantity");
                return null;
            }
            
            String command = split[0].toLowerCase();
            double quantity;
            
            try {
                quantity = Double.parseDouble(split[1]);
            } catch (NumberFormatException e) {
                System.err.println("Error: Quantity must be a numeric value");
                return null;
            }

            return new Object[] {command, quantity};

        } catch (Exception e) {
            System.err.println("Error parsing input: " + e.getMessage());
            return null;
        }
    }

}