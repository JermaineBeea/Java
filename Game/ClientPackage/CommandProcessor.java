package Game.ClientPackage;

import java.util.*;
import java.util.function.Consumer;

public class CommandProcessor {

    private Position posInstance;
    private Map<String, Consumer<Double>> commands = new HashMap<>();
    
    public CommandProcessor(Position pos) {
        this.posInstance = pos;
        
        // Initialize commands map with method references - cleaner with Consumer
        commands.put("forward", posInstance::moveForward);
        commands.put("backward", posInstance::moveBackward);
        commands.put("left", posInstance::moveLeft);
        commands.put("right", posInstance::moveRight);
    }

    public void executeCommand(String strCommand, double quantity) {
        Consumer<Double> command = commands.get(strCommand.toLowerCase());
        if (command != null) {
            command.accept(quantity);
        } else {
            System.err.println("Unknown command: " + strCommand);
        }
    }
}