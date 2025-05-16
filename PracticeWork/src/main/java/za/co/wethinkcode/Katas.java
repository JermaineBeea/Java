package za.co.wethinkcode;

import java.util.*;

public class Katas {

    /*
     * PROBLEM  1: 
     * Reverse the digits of a number
     * Given a number, return the number with its digits reversed.
     * For example, given the number 12345, return 54321.
     * Note: The number may be negative, in which case the sign should be preserved.
     * For example, given the number -12345, return -54321.
     * Constraints: The number will be a 32-bit signed integer.
     * The number will not be zero.
     */
    public static int reverseOrder(int num){
        // Check if the number is negative
        boolean isNegative = num < 0;
        // Convert the number to a string
        String strNum = Integer.toString(Math.abs(num));
        // Reverse the string
        String reversedStrNum = new StringBuilder(strNum).reverse().toString();
        // Convert the reversed string back to an integer
        int reversedNum = Integer.parseInt(reversedStrNum);
        // If the number was negative, return the negative of the reversed number
        return isNegative ? -reversedNum : reversedNum;
    }
    

    /*
     * PROBLEM 2: 
     * Count the number of vowels in a string
     * Given a string, return the number of vowels in the string.
     * For example, given the string "hello world", return 3.
     * Note: The string may contain uppercase and lowercase letters.
     * For example, given the string "Hello World", return 3.
     */
    public static int vowelCount1(String string){
        List<Integer> unicodes = new ArrayList<>();
        Character[] vowels = {'a', 'e', 'i', 'o', 'u'};

        for(Character character : vowels){
            unicodes.add((int) character);
        }

        return (int) string.toLowerCase().chars().filter(k -> unicodes.contains(k)).count();
    }

    /*
     * Problem 2 alternative.
     * This approach uses regex to count the number of vowels in a string.
     * The regex pattern "(?i)[aeiou]" matches any vowel (case insensitive).
     * The replaceAll method replaces all occurrences of the pattern with an empty string.
     * The length of the resulting string is the number of vowels in the original string.
     */
    public static int vowelCount2(String str){
        return str.replaceAll("(?i)[aeiou]", "").length();
    }

    /*
     * Problem 2 alternative.
     * This approach uses a lambda expression to count the number of vowels in a string.
     * The chars() method returns a stream of characters from the string.
     * The filter method filters the characters based on whether they are vowels.
     * The count method counts the number of characters that pass the filter.
     */
    public static int vowelCount3(String str){
        return (int) str.toLowerCase().chars().filter(k -> "aeiou".indexOf(k) >= 0).count();
    }




    /*
     * PROBLEM 3
     * Find the next perfect square
     * Given a number, return the next perfect square.
     * For example, given the number 4, return 9.
     * Note: The number may be negative, in which case the sign should be preserved.
     * For example, given the number -4, return -9.
     */
    public static long findNextSquare(long squareInt){
        // Check if the number is negative
        if(squareInt < 0) return -1;
        // Check if the number is a perfect square
        // If the number is not a perfect square, return -1
        long sqroot = (long) Math.sqrt(squareInt);
        if( ! (sqroot * sqroot == squareInt)) return -1;

        // If the number is a perfect square, return the next perfect square
        return (long) (sqroot + 1) * (sqroot + 1);
    }

    /*
     * Problem 3 alternative.
     * This approach uses a ternary operator to check if the number is a perfect square.
     * If it is, it returns the next perfect square. Otherwise, it returns -1.
     */
    public static long findfindNextSquare(long sq) {
        long root = (long) Math.sqrt(sq); // Calculate the square root of the number
        return root * root == sq ? (root + 1) * (root + 1) : -1; // Check if the number is a perfect square
    }


    /*
     * PROBLEM 4
     * Take 2 strings s1 and s2 including only letters from a to z. 
     * Return a new sorted string (alphabetical ascending), the longest possible, containing distinct letters - each taken only once - coming from s1 or s2.
     * Exanples:
     * a = "xyaabbbccccdefww"
     * b = "xyaabbbccccdefww"
     * longest(a, b) => "abcdefxy"
     * 
     * a = "abcdefghijklmnopqrstuvwxyz"
     * b = "abcdefghijklmnopqrstuvwxyz" 
     * longest(a, b) => "abcdefghijklmnopqrstuvwxyz"
     *
     */

     public static String uniqueLetters(String str1, String str2){
        
        return "";
     }
}
