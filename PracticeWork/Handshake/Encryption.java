

import java.util.*;

public class Encryption {
    
    // private final List<Integer> orderKey = 
    // A list of selected prime numbers used for encryption
    private final List<Integer> primeDigits = Arrays.asList(
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
        31, 37, 41, 43, 47, 53, 59, 61, 67, 71
        
        );


    /*
     * Multiplies all of the prime digits in the list
     * Example primeDigits = [3, 5, 7], then encrypt() -> 3 * 5 * 7 = 105
     */
    public long encrypt(){
        return primeDigits.stream()
                .mapToLong(Integer::longValue)
                .reduce(1L, (a, b) -> a * b);
    }

    /*
     * Takes the number and returns the list that would be consisted of the prime factors
     * Example 105 -> prime factors = 3, 5, 7
     * returns List [3, 5, 7]
     */
    public Long decrypt(long number){
        StringBuilder strNUm = new StringBuilder();
        findPrimeFactors(number).stream().forEach(n-> strNUm.append(n));
        return Long.parseLong(strNUm.toString());
    }

    /*
     * Helper function to find all prime factors of a given number
     * Example: findPrimeFactors(105) returns [3, 5, 7]
     */
    private List<Integer> findPrimeFactors(long number) {
        List<Integer> primeFactors = new ArrayList<>();
        long temp = number;
        
        // Find all prime factors
        for (int n = 2; n <= temp; n++) {
            while (temp % n == 0) {
                primeFactors.add(n);
                temp = temp / n;
            }
        }
        
        return primeFactors;
    }

    // Helper method to test the functionality
    public static void main(String[] args) {
        Encryption enc = new Encryption();
        long encrypted = enc.encrypt();
        System.out.println("Encrypted: " + encrypted); // Should be 105
        
        Long decrypted = enc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted); // Should be [3, 5, 7]
    }
}