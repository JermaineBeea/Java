package za.co.wethinkcode;

public class Katas {

    // PROBLEM 1
    /*
     * Reverse the digits of a number
     * Given a number, return the number with its digits reversed.
     * For example, given the number 12345, return 54321.
     * Note: The number may be negative, in which case the sign should be preserved.
     * For example, given the number -12345, return -54321.
     * Constraints: The number will be a 32-bit signed integer.
     * The number will not be zero.
     */
    static int reverseOrder(int number) {
        // TODO: Implement this method
        return 0;
    }

    // PROBLEM 2
    /*
     * Count the number of vowels in a string
     * Given a string, return the number of vowels in the string.
     * For example, given the string "hello world", return 3.
     * Note: The string may contain uppercase and lowercase letters.
     * For example, given the string "Hello World", return 3.
     */
    public static int vowelCount(String str) {
        // TODO: Implement this method
        return 0;
    }

    // PROBLEM 3
    /*
     * Find the next perfect square
     * Given a number, return the next perfect square.
     * For example, given the number 4, return 9.
     * If the number is not a perfect square, return -1.
     * For example, given the number 7, return -1.
     */
    public static int findNextSquare(int number) {
        // TODO: Implement this method
        return 0;
    }

    // PROBLEM 4
    /*
     * Take 2 strings s1 and s2 including only letters from a to z. 
     * Return a new sorted string (alphabetical ascending), the longest possible, containing distinct letters - each taken only once - coming from s1 or s2.
     * Examples:
     * a = "xyaabbbccccdefww"
     * b = "xyaabbbccccdefww"
     * uniqueLetters(a, b) => "abcdefwxy"
     * 
     * a = "abcdefghijklmnopqrstuvwxyz"
     * b = "abcdefghijklmnopqrstuvwxyz" 
     * uniqueLetters(a, b) => "abcdefghijklmnopqrstuvwxyz"
     */
    static String uniqueLetters(String str1, String str2) {
        // TODO: Implement this method
        return "";
    }

    // PROBLEM 5
    /*
     * Write a function that accepts an array of 10 integers (between 0 and 9), that returns a string of those numbers in the form of a phone number.
     * The returned format should be "(xxx) xxx-xxxx" where x is the given integer.
     * Note that the numbers in the array are not necessarily distinct.
     * Example: Katas.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}) // => returns "(123) 456-7890"
     */
    static String createPhoneNumber(int[] numbers) {
        // TODO: Implement this method
        return "";
    }

    // PROBLEM 6
    /*
     * A pangram is a sentence that contains every single letter of the alphabet at least once. 
     * For example, the sentence "The quick brown fox jumps over the lazy dog" is a pangram, because it uses the letters A-Z at least once (case is irrelevant).
     * Given a string, detect whether or not it is a pangram. Return True if it is, False if not. Ignore numbers and punctuation.
     */
    static boolean pangram(String sentence) {
        // TODO: Implement this method
        return false;
    }

    // PROBLEM 7
    /*
     * You are given an array (which will have a length of at least 3, but could be very large) containing integers. 
     * The array is either entirely comprised of odd integers or entirely comprised of even integers except for a single integer N. 
     * Write a method that takes the array as an argument and returns this "outlier" N.
     * Example:
     * [2, 4, 0, 100, 4, 11, 2602, 36] -->  11 (the only odd number)
     * [160, 3, 1719, 19, 11, 13, -21] --> 160 (the only even number)
     */
    static int findOutlier(int[] integers) {
        // TODO: Implement this method
        return 0;
    }

    // PROBLEM 8
    /*
     * In this assignment you are given a string of space separated numbers, and have to return the highest and lowest number.
     * Examples:
     * highAndLow("1 2 3 4 5") // return "5 1"
     * highAndLow("1 2 -3 4 5") // return "5 -3"
     * highAndLow("1 9 3 4 -5") // return "9 -5"
     *
     * Notes:
     * There will always be at least one number in the input string.
     * Output string must be two numbers separated by a single space, and highest number is first.
     */
    public static String highAndLow(String numbers) {
        // TODO: Implement this method
        return "";
    }
}