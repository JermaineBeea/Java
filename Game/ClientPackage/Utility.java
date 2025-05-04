package Game.ClientPackage;

public class Utility {
    
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