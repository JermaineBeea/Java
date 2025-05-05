import java.util.function.Consumer;
import java.util.Set;
import java.util.HashSet;

public class PositionalCommands {
    
    private static Set<Consumer<Double>> positionCommands = new HashSet<>();

    static
    {
        positionCommands.add(PositionalCommands::forward);
        positionCommands.add(PositionalCommands::backward);
        positionCommands.add(PositionalCommands::right);
        positionCommands.add(PositionalCommands::left);
    }

    public static Set<Consumer<Double>> getVoidFunctions(){
        return positionCommands;
    }
    
    private static void forward(double distance){

    }
    private static void backward(double distance){

    }
    private static void right(double distance){

    }
    private static void left(double distance){

    }

}
