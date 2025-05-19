package za.co.wethinkcode;

import java.util.*;

public class Practice {
    /**
     * Prints an integer as is if not divisible by 3 or 5.
     * It labels an integer divisible by 3 as "Fizz".
     * It labels an integer divisible by 5 as "Buzz".
     * It lab
     * @param input the input number as a parameter.
     * @return the "FizzBuzz" string corresponding to the input number.
     */
    static String fizzBuzzOnce(int input) {
        // TODO: implement this method
        return null;
    }

    /**
     * Generates a list of strings representing the "FizzBuzz" sequence from 1 to n.
     *
     * @param n the upper limit of the sequence
     * @return a list of strings representing the "FizzBuzz" sequence
     */
    static List<String> fizzBuzz(int n) {
        // TODO: implement this method
        return null;
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
        // TODO: implement this method
        return null;
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
        if(n == 1) return 0;
        if(n == 2) return 1;
        return recursiveFibonacciN(n - 1) + recursiveFibonacciN( n - 2);
    }

    /**
     * Generates a string representation of a triangle made of asterisks (*).
     *
     * @param n the number(a positive integer) of rows in the triangle.
     * @return a string * representation of the triangle.
     */
    static String drawTriangle(int n) {
        return "";
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
        // TODO: implement this method
        return null;
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
        return "Okay: " + String.valueOf(a%b);
    }

    /**
     * Returns a new list of names sorted in ascending order based on their length.
     *
     * @param names the list of names to be sorted. It should not be modified.
     * @return a new list of names sorted in ascending order based on their length.
     */
    static List<String> correctOrder(List<String> names) {
        return null;
    }

    /**
     * Determines whether the given DNA sequence represents a valid protein.
     * @param dna - the DNA sequence to be checked.
     * @return - true if the DNA sequence represents a valid protein, false otherwise.
     */
    static boolean dnaProtein(String dna) {
        return true;
    }

    /**
     * Generates a Punnett square for two given genotypes.
     * @param genotype1 the first genotype
     * @param genotype2 the second genotype
     * @return a 2D array representing the Punnett square.
     */
    static String[][] punnettSquare(String genotype1, String genotype2) {
        return null;
    }

    /**
     * Converts a binary number to its decimal representation.
     * @param binaryNumber the binary number to be converted.
     * @return the decimal representation of the binary number.
     * @throws IllegalArgumentException if the input is not a valid binary number.
     */
    static String binaryConversion(String binaryNumber) {
        return "";
    }

    /**
     * Finds the index of the first occurrence of a substring within a given string.
     *
     * @param subString   the substring to search for.
     * @param wholeString the whole string in which to search for the substring.
     * @return the index of the first occurrence of the substring in the whole string, or -1 if not found.
     */
    static int firstSubstring(String subString, String wholeString) {
        return 0;
    }

    /**
     * Calculates the frequency of a specific character in a given string.
     *
     * @param character the character to count the frequency of.
     * @param string    the string in which to count the frequency.
     * @return the frequency of the specified character in the string.
     */
    static int frequencyOf(int character, String string) {
        return 0;
    }

    /**
     * Calculates the Hamming distance between two strings.
     * @param firstString  the first string for comparison.
     * @param secondString the second string for comparison.
     * @return the Hamming distance between the two strings, or -1 if the strings have different lengths.
     */
    static int hammingDistance(String firstString, String secondString) {
        return 0;
    }

    /**
     * Checks if a given string is a palindrome or not.
     *
     * @param string the string to check for palindrome property.
     * @return true if the string is a palindrome, false otherwise.
     */
    static boolean isPalindrome(String string) {
        return true;
    }

    /**
     * Calculates the sum of all integers in a given collection.
     *
     * @param numbers the collection of integers.
     * @return the sum of all integers in the collection.
     */
    static int sumIntegers(Collection<Integer> numbers) {
        return 0;
    }

    private static int combination(int n, int k) {
        return 0;
    }

    private static int factorial(int num) {
        return 0;
    }

    /**
     * Generates Pascal's triangle and returns the final row as a list.
     *
     * @param n the row number of Pascal's triangle to generate (0-based index)
     * @return the final row of Pascal's triangle as a list
     */
    static List<Integer> pascalTriangle(int n) {
        return null;
    }
}
