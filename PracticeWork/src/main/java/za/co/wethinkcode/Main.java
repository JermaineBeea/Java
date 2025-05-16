package za.co.wethinkcode;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {



    }

    static int find(int[] integers) {
        // Count even numbers in the first 5 elements (or fewer if array is smaller)
        int evenCount = 0;
        int limit = Math.min(integers.length, 5);
        
        for (int i = 0; i < limit; i++) {
            if (integers[i] % 2 == 0) {
                evenCount++;
            }
        }
        
        // Determine divisor based on even count
        int divisor = (evenCount >= 2) ? 2 : 3;
        
        // Find and return the first number divisible by the determined divisor
        for (int num : integers) {
            if (num % divisor == 0) {
                return num;
            }
        }
        
        // Default value if no matching element is found
        return 0;
    }

    static int find2(int[] integers) {
    // Convert primitive int[] to List<Integer>
    List<Integer> listInt = Arrays.stream(integers).boxed().collect(Collectors.toList());
    
    // Count even numbers in the first 5 elements (or fewer if array is smaller)
    long evenCount = listInt.stream().limit(5).filter(n -> n % 2 == 0).count();
    
    // Determine divisor based on even count
    int divisor = (evenCount >= 2) ? 2 : 3;
    
    // Find and return the first number divisible by the determined divisor
    return listInt.stream().filter(n -> n % divisor == 0).findFirst().orElse(0); // Default value if no matching element is found
}
}
