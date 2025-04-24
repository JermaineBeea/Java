import java.util.Scanner;

public class ContinuousInputProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            // Get input from user
            System.out.print("Enter input (or 'exit' to quit): ");
            String input = scanner.nextLine();
            
            // Check if user wants to exit
            if (input.equalsIgnoreCase("exit")) {
                running = false;
                continue;
            }
            
            // Process input and display result
            String result = processInput(input);
            System.out.println("Result: " + result);
        }
        
        scanner.close();
        System.out.println("Program terminated.");
    }
    
    public static String processInput(String input) {
        // Example processing: reverse the input string
        return new StringBuilder(input).reverse().toString();
    }
}