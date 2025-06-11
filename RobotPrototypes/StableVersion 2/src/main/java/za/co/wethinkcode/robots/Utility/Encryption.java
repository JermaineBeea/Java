package za.co.wethinkcode.robots.Utility;

import java.math.BigInteger;
import java.util.*;

/**
 * Utility class for performing encryption and decryption using a predefined list of prime numbers.
 */
public class Encryption {
    
    // A list of selected prime numbers used for encryption
    private final List<Integer> primeDigits = Arrays.asList(
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
        31, 37, 41, 43, 47, 53, 59, 61, 67, 71
    );
    
    /**
     * Concatenates all prime numbers in the list into a single long value.
     * For example: [3, 5, 13] becomes 3513.
     *
     * @return a long number formed by concatenating all the prime digits
     */
    public BigInteger getConcatenated() {
        StringBuilder strNum = new StringBuilder();
        primeDigits.forEach(k -> strNum.append(k));
        return new BigInteger(strNum.toString());
    }

    /**
     * Multiplies all of the prime digits in the list.
     * For example: [3, 5, 7] -> 3 * 5 * 7 = 105
     *
     * @return the product of all prime digits
     */
    public BigInteger encrypt() {
        return primeDigits.stream()
                .map(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
    /**
     * Decrypts a given number by extracting its prime factors and
     * concatenating them into a single long value.
     * For example: 105 -> prime factors = [3, 5, 7] -> returns 357
     *
     * @param number the number to decrypt
     * @return a long formed by concatenating the prime factors
     */
    public BigInteger decrypt(BigInteger number) {
        StringBuilder strNum = new StringBuilder();
        findPrimeFactors(number).forEach(n -> strNum.append(n));
        return new BigInteger(strNum.toString());
    }

    /**
     * Helper method to find all prime factors of a given number.
     * For example: 105 -> [3, 5, 7]
     *
     * @param number the number whose prime factors are to be found
     * @return a list of prime factors
     */
    private List<Integer> findPrimeFactors(BigInteger number) {
        List<Integer> primeFactors = new ArrayList<>();
        BigInteger temp = number;
        BigInteger divisor = BigInteger.valueOf(2);
    
        while (divisor.compareTo(temp) <= 0) {
            while (temp.mod(divisor).equals(BigInteger.ZERO)) {
                primeFactors.add(divisor.intValue());
                temp = temp.divide(divisor);
            }
            divisor = divisor.add(BigInteger.ONE);
        }
    
        return primeFactors;
    }
    
    /**
     * Demonstrates the functionality of the Encryption class.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Encryption enc = new Encryption();
        BigInteger encrypted = enc.encrypt();
        System.out.println("Encrypted: " + encrypted); // Should print product of all primes

        BigInteger decrypted = enc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted); // Should print concatenated prime factors
    }
}
