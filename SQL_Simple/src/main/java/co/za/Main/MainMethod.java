package co.za.Main;

public class MainMethod {
    public static void main(String[] args) {

        Long a = 5L;   // Example value for variable a
        Long b = 3L;
        Long c = 2L;   // Example value for variable c

        // Initialize QueryImplementation with example values
        new QueryImplementation(a, b, c).runQuery();

        // Note: The QueryFunction class is used internally in VariableDatabase to compute values.
        // If you need to use it directly, you can create an instance like this:
        // QueryFunction queryFunction = new QueryFunction(a, b, c);
        // Long resultA = queryFunction.returnA();
        // System.out.println("Result A: " + resultA);
    }
}
