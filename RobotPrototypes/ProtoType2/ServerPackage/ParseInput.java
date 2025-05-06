package ServerPackage;

import java.util.Set;

public class ParseInput {
    
    private final String strInput;
    private String strCommand;
    private Double quantity;
    private final String DELIMITER = "\\s+"; // Fixed typo and escaped backslash for regex
    private final Set<String> validCommands = Set.of(
        "leftturn", "rightturn", "right", 
        "left", "forward", "backward"
    );

    public ParseInput(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        this.strInput = input;
        try {
            this.splitInput();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public String getCommand() {
        return strCommand;
    }

    public Double getQuantity() {
        return quantity;
    }

    private boolean isValidCommand() {
        return validCommands.contains(this.strCommand.toLowerCase());
    }

    private void splitInput() throws Exception {
        String[] splitInput = this.strInput.split(DELIMITER);
        if (splitInput.length != 2) {
            throw new Exception("Incorrect Format");
        }

        this.strCommand = splitInput[0].toLowerCase(); // Convert to lowercase for case-insensitive comparison

        if (!isValidCommand()) {
            throw new Exception("Invalid command! Enter one of " + validCommands);
        }

        String strQuantity = splitInput[1];
        try {
            this.quantity = Double.parseDouble(strQuantity);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Quantity must be a number! " + e.getMessage());
        }
    }
}