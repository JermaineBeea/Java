import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Consumer;

public class CommandProcessor {
    // For functions that return a value
    private final Map<String, Function<Object[], Object>> functionCommands = new HashMap<>();
    
    // For functions that don't return a value (void)
    private final Map<String, Consumer<Object[]>> voidCommands = new HashMap<>();
    
    // Register a command that returns a value
    public void registerFunction(String commandName, Function<Object[], Object> function) {
        functionCommands.put(commandName, function);
    }
    
    // Register a command that doesn't return a value
    public void registerVoidFunction(String commandName, Consumer<Object[]> function) {
        voidCommands.put(commandName, function);
    }
    
    // Execute a function command
    public Object executeFunction(String commandName, Object... args) {
        if (functionCommands.containsKey(commandName)) {
            return functionCommands.get(commandName).apply(args);
        }
        throw new IllegalArgumentException("Command not found: " + commandName);
    }
    
    // Execute a void command
    public void executeVoidCommand(String commandName, Object... args) {
        if (voidCommands.containsKey(commandName)) {
            voidCommands.get(commandName).accept(args);
            return;
        }
        throw new IllegalArgumentException("Command not found: " + commandName);
    }
    
    // Check if a command exists
    public boolean hasCommand(String commandName) {
        return functionCommands.containsKey(commandName) || voidCommands.containsKey(commandName);
    }
    
    public static void main(String[] args) {
        CommandProcessor processor = new CommandProcessor();
        
        // Register some example commands
        
        // A function that adds two integers and returns the result
        processor.registerFunction("add", params -> {
            if (params.length != 2) throw new IllegalArgumentException("Add requires 2 parameters");
            return (Integer)params[0] + (Integer)params[1];
        });
        
        // A function that concatenates strings and returns the result
        processor.registerFunction("concat", params -> {
            StringBuilder sb = new StringBuilder();
            for (Object param : params) {
                sb.append(param.toString());
            }
            return sb.toString();
        });
        
        // A void function that prints a message
        processor.registerVoidFunction("print", params -> {
            for (Object param : params) {
                System.out.println(param);
            }
        });
        
        // Execute commands
        System.out.println(processor.executeFunction("add", 5, 7)); // Output: 12
        System.out.println(processor.executeFunction("concat", "Hello, ", "World!")); // Output: Hello, World!
        processor.executeVoidCommand("print", "This is a void command"); // Prints: This is a void command
    }
}