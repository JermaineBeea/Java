package ServerPackage;

import java.util.function.Consumer;
import java.util.Map;
import java.util.HashMap;


public class CommandProcessor extends Command{

    private Map<String, Consumer<Double>> mapCommands = new HashMap<>();
    private Robot robot;
    
    public CommandProcessor(Robot instance){
        super(instance);
        this.robot = instance;
        mapCommands.put("rotateright", arg -> rotateRight(arg.intValue()));
        mapCommands.put("rotateleft", arg -> rotateLeft(arg.intValue()));
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("backward", this::moveBackward);
        mapCommands.put("right", this::moveRight);
        mapCommands.put("left", this::moveLeft);
    }
   
    public void executeCommand(String strCommand, Double argQuantity){
        
        if(isPositive(argQuantity)){

            Consumer<Double> function = mapCommands.get(strCommand.toLowerCase());
            if (function == null) {
                throw new IllegalArgumentException("Unknown command: " + strCommand);
            }
            
            // Check if there's enough fuel for movement commands
            if ((strCommand.equals("forward") || strCommand.equals("backward") || 
                 strCommand.equals("right") || strCommand.equals("left")) && 
                !robot.canMove(argQuantity)) {
                throw new IllegalStateException("Not enough fuel for this movement!");
            }
            
            function.accept(argQuantity);
        }else{
            throw new IllegalArgumentException("Quantity of command cannot be negative!");
        }
    }    

    // Validator for each method.
    private boolean isPositive(Number quantityArg){
        return (Double) quantityArg >= 0.0;
    }
}