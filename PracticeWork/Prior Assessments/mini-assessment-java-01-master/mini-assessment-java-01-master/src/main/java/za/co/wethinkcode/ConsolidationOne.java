package za.co.wethinkcode;

import java.util.*;

public class ConsolidationOne {
    /**
     * Prints an integer as is if not divisible by 3 or 5.
     * It labels an integer divisible by 3 as "Fizz".
     * It labels an integer divisible by 5 as "Buzz".
     * It labels an integer divisible by both 3 & 5 as "FizzBuzz".
     *
     * @param input the input number as a parameter.
     * @return the "FizzBuzz" string corresponding to the input number.
     */
    static String fizzBuzzOnce(int input) {
        if (input % 3 == 0 && input % 5 ==0) {
            return "FizzBuzz";
        } else if (input % 3 == 0) {
            return "Fizz";
        } else if (input % 5 == 0) {
            return "Buzz";
        } else {
            return Integer.toString(input);
        }
    }


    /**
     * Generates a list of strings representing the "FizzBuzz" sequence from 1 to n.
     *
     * @param n the upper limit of the sequence
     * @return a list of strings representing the "FizzBuzz" sequence
     */
    static List<String> fizzBuzz(int n) {
        List<String> results = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            results.add(fizzBuzzOnce(i));
        }
        return results;
    }


    /**
     * Generates a Fibonacci sequence up to the specified number.
     * The sequence starts with 0 and 1.
     * Each subsequent number is the sum of the two previous numbers.
     *
     * @param n the number of elements to include in the Fibonacci sequence
     * @return a string representation of the Fibonacci sequence
     */
    static String getFibonacciSequence(int n) {
        if (n <= 0) return "";
        StringBuilder sequence = new StringBuilder();
        int k = 0, b = 1;
        for (int i = 0; i < n; i++) {
            sequence.append(k).append(", ");
            int next_sequence = k + b;
            k = b;
            b = next_sequence;
        }
        return "Fibonacci Sequence: " + sequence.toString().trim();
    }


    /**
     * Generates a Fibonacci sequence up to the specified number.
     * The sequence starts with 0 and 1.
     * Each subsequent number is the sum of the two previous numbers.
     *
     * @param n the number of elements to include in the Fibonacci sequence
     * @return a single value corresponding to the index of n should be returned
     * e.g. recursiveFibonacciN(6) should return 5.
     */
    static int recursiveFibonacciN(int n) {
        if (n <= 1) return n;
        return recursiveFibonacciN(n - 1) + recursiveFibonacciN(n - 2);
    }


    /**
     * Generates a string representation of a triangle made of asterisks (*).
     *
     * @param n the number(a positive integer) of rows in the triangle.
     * @return a string * representation of the triangle.
     */
    static String drawTriangle(int n) {
        StringBuilder triangle = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            triangle.append("*".repeat(i)).append("\n");
        }
        return triangle.toString();
    }


    /**
     * Calculates the result of the arithmetic operation
     * between two integers using the specified operator.
     *
     * @param a        the first integer operand.
     * @param b        the second integer operand.
     * @param operator The arithmetic operator to apply.
     *                 Supported operators are "+", "-", "*", "/", and "%".
     *                 Do not correct the functionality for modulo i.e. '%'.
     * @return the result of the arithmetic operation as number.
     */
    static String calculate(int a, int b, String operator) {
        switch (operator) {
            case "+":
                return "Okay: " + String.valueOf(a + b);
            case "-":
                return "Okay: " + String.valueOf(a - b);
            case "*":
                return "Okay: " + String.valueOf(a * b);
            case "/":
                if (b == 0) return "Error: Division by zero is not allowed.";
                return "Okay: " + String.valueOf(a / b);
            case "%":
                return "Okay: " + String.valueOf(a % b);
            default:
                return "Error: Invalid operator: ^";
        }
    }


    /**
     * Calculates the result of the arithmetic operation
     * between two integers using the specified operator for mod.
     * i.e. 5 modulo 3 = 2
     *
     * @param a        the first integer operand.
     * @param b        the second integer operand.
     * @param operator The arithmetic operator to apply.
     *                 Correct the functionality for modulo i.e. '%'.
     * @return the result of the arithmetic operation as number.
     */
    static String calculateModCorrectly(int a, int b, String operator) {
        if ("%".equals(operator)) {
            return "Okay: " + String.valueOf(a % b);
        } else {
            return calculate(a, b, operator);
        }
    }


    /**
     * Returns a new list of names sorted in ascending order based on their length.
     *
     * @param names the list of names to be sorted. It should not be modified.
     * @return a new list of names sorted in ascending order based on their length.
     */
    static List<String> correctOrder(List<String> names) {
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort(Comparator.comparingInt(String::length));
        return sortedNames;
    }


    /**
     * Determines whether the given DNA sequence represents a valid protein.
     * <p>
     * The genome of an organism stores all the genetic information necessary to build and maintain that organism.
     * It is an organism’s complete set of DNA. DNA is composed of a series of nucleotides abbreviated as:
     * A: Adenine
     * C: Cytosine
     * G: Guanine
     * T: Thymine
     * So a strand of DNA could look something like ACGAATTCCG.
     * <p>
     * Complete the method dnaProtein that determines whether there is a protein in a strand of DNA.
     * <p>
     * A protein has the following qualities:
     * - It begins with a “start codon”: ATG.
     * - It ends with a “stop codon”: TGA.
     * - In between, each additional codon is a sequence of three nucleotides.
     * So for example:
     * <p>
     * - ATGCGATACTGA is a protein because it has the start codon ATG, the stop codon TGA, and the length is divisible by 3.
     * <p>
     * - ATGCGATAGA is not a protein because the sequence length is not divisible by 3, so the third condition is not satisfied.
     *
     * @param dna - the DNA sequence to be checked.
     * @return - true if the DNA sequence represents a valid protein, false otherwise.
     */
    static boolean dnaProtein(String dna) {
        if (dna.startsWith("ATG") && dna.endsWith("TGA") && dna.length() % 3 == 0) {
            return true;
        }
        return false;
        
    }


    /**
     * Generates a Punnett square for two given genotypes.
     * <p>
     * The genotype of an organism can be determined by use of a Punnet Square.
     * <p>
     * The Punnett Square is a table in which all of the possible outcomes
     * for a genetic cross between two individuals with known genotypes are given.
     * <p>
     * For example, if he crossed two plants both heterozygous for height,
     * where T represents a dominant tall phenotype and t represents the recessive short phenotype,
     * the Punnett square would have the following appearance:
     * <p>
     * +--------+-------------------+----------------------------+
     * |         |                   |         Pollen            |
     * +---------+-------------------+---------------------------+
     * |         |                   |       T     |     t       |
     * |         +--------------------+------------+-------------+
     * | Ovules  |      T            | TT          | Tt          |
     * |         +-------------------+------------+--------------+
     * |         |      t            | Tt         | tt           |
     * +--------+-------------------+------------+---------------+
     * <p>
     * Complete the method punnettSquare that returns a 2D array of all possible genotypes.
     *
     * @param genotype1 the first genotype
     * @param genotype2 the second genotype
     * @return a 2D array representing the Punnett square.
     */
    static String[][] punnettSquare(String genotype1, String genotype2) {
        String[][] square = new String[2][2];
        char geno = genotype1.charAt(0);
        char geno1 = genotype1.charAt(1);
        char geno2 = genotype2.charAt(0);
        char geno3 = genotype2.charAt(1);

        square[0][0] = "" + geno + geno2;
        square[0][1] = "" + geno + geno3;
        square[1][0] = "" + geno1 + geno2;
        square[1][1] = "" + geno1 + geno3;

        return square;
    }


    /**
     * Converts a binary number to its decimal representation.
     * <p>
     * The decimal number is equal to the sum of binary digits (dn) times their power of 2 (2n):
     * <p>
     * decimal = d0×2^0 + d1×2^1 + d2×2^2 + ...
     * with d0 being the far most right index.
     * <p>
     * Example:
     * Find the decimal value of 111001:
     * binary number:	1	 1	 1	 0	 0	 1
     * power of 2:	    2^5	2^4	2^3	2^2	2^1	2^0
     * <p>
     * 111001 = 1⋅2^5+1⋅2^4+1⋅2^3+0⋅2^2+0⋅2^1+1⋅2^0 = 57
     * <p>
     * Complete the method binaryConversion that returns the decimal value
     * of a given binary number
     *
     * @param binaryNumber the binary number to be converted.
     * @return the decimal representation of the binary number.
     * @throws IllegalArgumentException if the input is not a valid binary number.
     */
    static String binaryConversion(String binaryNumber) {
        try {
            int decimal = Integer.parseInt(binaryNumber, 2);
            return String.valueOf(decimal);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Not a binary number.");
        }
    }


    /**
     * Finds the index of the first occurrence of a substring within a given string.
     *
     * @param subString   the substring to search for.
     * @param wholeString the whole string in which to search for the substring.
     * @return the index of the first occurrence of the substring in the whole string, or -1 if not found.
     */
    static int firstSubstring(String subString, String wholeString) {
        return wholeString.indexOf(subString);
    }


    /**
     * Calculates the frequency of a specific character in a given string.
     *
     * @param character the character to count the frequency of.
     * @param string    the string in which to count the frequency.
     * @return the frequency of the specified character in the string.
     */
    static int frequencyOf(int character, String string) {
        char target = (char) character;
        int count = 0;
        for (char c : string.toCharArray()) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }


    /**
     * Calculates the Hamming distance between two strings.
     * <p>
     * Complete the method hammingDistance,
     * Where the Hamming distance between two strings of equal length is
     * the number of positions at which the corresponding character is different
     * <p>
     * Return the total number of different indices between the two strings.
     *
     * @param firstString  the first string for comparison.
     * @param secondString the second string for comparison.
     * @return the Hamming distance between the two strings, or -1 if the strings have different lengths.
     */
    static int hammingDistance(String firstString, String secondString) {
        if (firstString.length() != secondString.length()) {
            return -1;
        }
        int distance = 0;
        for (int i = 0; i < firstString.length(); i++) {
            if (firstString.charAt(i) != secondString.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }


    /**
     * Checks if a given string is a palindrome or not.
     *
     * @param string the string to check for palindrome property.
     * @return true if the string is a palindrome, false otherwise.
     */
    static boolean isPalindrome(String string) {
        int left = 0, right = string.length() - 1;
        while (left < right) {
            if (string.charAt(left) != string.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * Calculates the sum of all integers in a given collection.
     *
     * @param numbers the collection of integers.
     * @return the sum of all integers in the collection.
     */
    static int sumIntegers(Collection<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }


    /**
     * Generates Pascal's triangle and returns the final row as a list.
     *
     * @param n the row number of Pascal's triangle to generate (0-based index)
     * @return the final row of Pascal's triangle as a list
     * <p>
     * Formula for Pascal's Triangle:
     * Each element in Pascal's triangle can be calculated using the following formula:
     * <p>
     * C(n, k) = n! / (k! * (n-k)!)
     * <p>
     * Where:
     * - n is the row number (0-based index)
     * - k is the column number (0-based index)
     * - C(n, k) represents the value at row n and column k in Pascal's triangle
     * - n! represents the factorial of n
     * <p>
     * To generate a row of Pascal's triangle, iterate over the columns from 0 to n.
     * Calculate each element using the formula and store them in a list to form the row.
     * Repeat this process for each row up to the desired row number.
     * <p>
     * Input: n = 6
     * Output:
     *           1
     *         1   1
     *       1   2   1
     *     1   3   3   1
     *   1   4   6   4   1
     * 1   5  10   10  5   1
     */
    static List<Integer> pascalTriangle(int n) {
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            row.add(pascal(n, i));
        }
        return row;
    }

    static int pascal(int n, int k) {
        if (k == 0 || k == n) return 1;
        return pascal(n - 1, k - 1) + pascal(n - 1, k);
    }
}