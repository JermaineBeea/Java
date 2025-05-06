package ServerPackage;

import java.util.function.Consumer;
import java.util.Map;
import java.util.HashMap;


public abstract class CommandProcessor extends Command{

    private Map<String, Consumer<Double>> mapCommands = new HashMap<>();
    
    public CommandProcessor(Robot instance){
        super(instance);
        mapCommands.put("rotateright", arg -> rotateRight(arg.intValue()));
        mapCommands.put("rotateleft", arg -> rotateLeft(arg.intValue()));
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("backward", this::moveBackward);
        mapCommands.put("right", this::moveRight);
        mapCommands.put("left", this::moveLeft);
    }
   
    public void executeCommand(String strCommand, Double argQuantity){
        Consumer<Double> function = mapCommands.get(strCommand);
        function.accept(argQuantity);
    }    
}
