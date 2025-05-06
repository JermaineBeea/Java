package ServerPackage;

import java.util.function.Consumer;
import java.util.Map;
import java.util.HashMap;


public abstract class CommandProcessor extends Command{

    private Map<String, Consumer<Double>> mapCommands = new HashMap<>();
    
    public CommandProcessor(Robot instance){
        super(instance);
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("forward", this::moveForward);
    }
   
    
}
