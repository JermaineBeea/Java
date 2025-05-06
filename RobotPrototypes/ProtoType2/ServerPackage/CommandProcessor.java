package ServerPackage;

import java.util.function.Consumer;
import java.util.Map;
import java.util.HashMap;


public abstract class CommandProcessor {

    private Map<String, Consumer<Double>> mapCommands = new HashMap<>();
    {
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("forward", this::moveForward);
        mapCommands.put("forward", this::moveForward);
    }
   
    // Delta functions.
    public abstract void rotateRight(int rotation);

    public abstract void rotateLeft(int rotation);

    public abstract void moveForward(double distance);

    public abstract void moveBackward(double distance);

    public abstract void moveRight(double distance);

    public abstract void moveLeft(double distance);
    
}
