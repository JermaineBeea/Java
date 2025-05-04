package Game.ClientPackage.Bin;

import java.util.*;
import java.util.function.Function;

import Game.ClientPackage.Position;

public class ClientCommands_2 {

    private Position posInstance;
    private Map<String, Function<Double, Void>> commands = new HashMap<>();
    
    public ClientCommands_2(Position pos) {
        this.posInstance = pos;
        
        // Initialize commands map with method references
        commands.put("forward", distance -> {
            posInstance.moveForward(distance);
            return null;
        });
        commands.put("backward", distance -> {
            posInstance.moveBackward(distance);
            return null;
        });
        commands.put("left", distance -> {
            posInstance.moveLeft(distance);
            return null;
        });
        commands.put("right", distance -> {
            posInstance.moveRight(distance);
            return null;
        });
    }

    public void executeCommand(String strCommand, double quantity) {
        Function<Double, Void> command = commands.get(strCommand.toLowerCase());
        if (command != null) {
            command.apply(quantity);
        } else {
            System.err.println("Unknown command: " + strCommand);
        }
    }
}