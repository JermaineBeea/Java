import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionToRunnableExamples {

    public static void main(String[] args) {
        // Example usage of different function conversions
        demoRunnableConversions();
    }

    public static void demoRunnableConversions() {
        // 1. Function that returns nothing and takes no parameters
        // This is already a Runnable
        Runnable alreadyRunnable = () -> System.out.println("I'm already a Runnable");
        
        // 2. Function that returns a value but takes no parameters (Supplier)
        Supplier<String> supplier = () -> "I return a String";
        Runnable supplierAsRunnable = () -> {
            String result = supplier.get(); // Call the supplier and ignore the result
            System.out.println("Supplier result (usually ignored): " + result);
        };
        
        // 3. Function that takes a parameter but returns nothing (Consumer)
        Consumer<String> consumer = (str) -> System.out.println("Consuming: " + str);
        Runnable consumerAsRunnable = () -> {
            consumer.accept("Default value"); // Call with a default parameter
        };
        
        // 4. Function that takes a parameter and returns a value
        Function<Integer, String> function = (num) -> "Number: " + num;
        Runnable functionAsRunnable = () -> {
            String result = function.apply(42); // Call with a default parameter and ignore result
            System.out.println("Function result (usually ignored): " + result);
        };
        
        // Execute all our converted Runnables
        alreadyRunnable.run();
        supplierAsRunnable.run();
        consumerAsRunnable.run();
        functionAsRunnable.run();
    }

    // Generic method that can convert almost any function type to a Runnable
    public static Runnable toRunnable(Object functionObject) {
        if (functionObject == null) {
            throw new NullPointerException("Function object cannot be null");
        }
        
        // Already a Runnable
        if (functionObject instanceof Runnable) {
            return (Runnable) functionObject;
        }
        
        // Supplier - takes no params, returns a value
        if (functionObject instanceof Supplier) {
            return () -> ((Supplier<?>) functionObject).get();
        }
        
        // Consumer - takes a param, returns void
        if (functionObject instanceof Consumer) {
            return () -> ((Consumer<Object>) functionObject).accept(null);
        }
        
        // Function - takes a param, returns a value
        if (functionObject instanceof Function) {
            return () -> ((Function<Object, ?>) functionObject).apply(null);
        }
        
        // If we can't recognize the function type, throw an exception
        throw new IllegalArgumentException("Cannot convert object to Runnable: " + functionObject.getClass());
    }
}