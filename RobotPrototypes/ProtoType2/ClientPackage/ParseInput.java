package ClientPackage;

import java.util.Set;

public class ParseInput {
    
    private final String command;
    private final Double quantity;
    private static final Set<String> VALID_COMMANDS = Set.of(
        "rotateleft", "rotateright", "right", 
        "left", "forward", "backward"
    );

    public ParseInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        
        String[] parts = input.split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Input must contain a command and quantity");
        }
        
        String cmd = parts[0].trim().toLowerCase();
        if (!VALID_COMMANDS.contains(cmd)) {
            throw new IllegalArgumentException("Invalid command! Valid options: " + VALID_COMMANDS);
        }
        this.command = cmd;
        
        try {
            this.quantity = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Quantity must be a number");
        }
    }

    public String getCommand() {
        return command;
    }

    public Double getQuantity() {
        return quantity;
    }
}