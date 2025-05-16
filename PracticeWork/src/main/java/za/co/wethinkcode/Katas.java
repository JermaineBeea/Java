package za.co.wethinkcode;

import java.util.*;

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
    


    // PROBLEM 2
    /*
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



    //PROBLEM 3
    /*
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
    public static long findfindNextSquare(long squareInt) {
        long root = (long) Math.sqrt(squareInt); // Calculate the square root of the number
        return root * root == squareInt ? (root + 1) * (root + 1) : -1; // Check if the number is a perfect square
    }



    //PROBLEM 4
    /*
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

        Set<Character> unique = new HashSet<>();

        for(Character charN: (str1 + str2).toCharArray()){
            int unicode = (int) charN;
            if(Character.isAlphabetic(unicode)) unique.add(charN);
        }

        List<Character> sortedList = new ArrayList<>(unique);
        Collections.sort(sortedList);

        StringBuilder sortedLetters = new StringBuilder();
        for(Character n : sortedList){
            sortedLetters.append(n);
        }
        return sortedLetters.toString();
     }
     
     /*
      * Problem 4 alternative.
      * This approach uses a Set to store the distinct letters from both strings.
      * The Set automatically handles duplicates.
      * The sorted() method sorts the characters in the Set.
      * The collect() method collects the sorted characters into a StringBuilder.
      * The toString() method converts the StringBuilder to a string.
      * The distinct() method ensures that only unique characters are included.
        * The sorted() method sorts the characters in ascending order.
      */
     public static String uniqueLetters2 (String s1, String s2) {
        String s = s1 + s2;
        return 
        
        s.chars().distinct().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    /*
     * Problem 4 alternative.
     * This approach uses a StringBuilder to build the final string.
     * The chars() method returns a stream of characters from the string.
     * The distinct() method ensures that only unique characters are included.
     * The sorted() method sorts the characters in ascending order.
     * The forEach() method appends each character to the StringBuilder.
     * The toString() method converts the StringBuilder to a string.
     */
    public static String uniqueLetters3 (String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        (s1 + s2).chars().distinct().sorted().forEach(c -> sb.append((char) c));
        return sb.toString();
    }


    // PROBLEM 5
    /*
     * Write a function that accepts an array of 10 integers (between 0 and 9), that returns a string of those numbers in the form of a phone number.
     * The returned format should be "(xx) xxx-xxxx" where x is the given integer.
     * Note that the numbers in the array are not necessarily distinct.
     * Example: Kata.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}) // => returns "(123) 456-7890"
     */

     static String createPhoneNumber(int[] numbers) {
        StringBuilder strNumber = new StringBuilder("(");
        for(int n = 0; n < 3; n ++) strNumber.append(numbers[n]);
        strNumber.append(") ");
        for(int n = 3; n < 6; n ++) strNumber.append(numbers[n]);
        strNumber.append("-");
        for(int n = 6; n < 10; n ++) strNumber.append(numbers[n]);
        return strNumber.toString();
    }

    /*
     * Problem 5 alternative.
     * This approach uses a lambda expression to format the phone number.
     * The IntStream.of(numbers).boxed().toArray() converts the int array to an array of Integers.
     * The String.format method formats the phone number according to the specified format.
     */
     public static String createPhoneNumber2(int[] numbers) {
        return String.format("(%d%d%d) %d%d%d-%d%d%d%d", java.util.stream.IntStream.of(numbers).boxed().toArray());
    }

    /*
   * Problem 5 alternative.
   * This approach uses String.format to format the phone number.
   * The format string specifies the desired format for the phone number.
   * The numbers are passed as arguments to the format method.
   * The format method replaces the placeholders in the format string with the corresponding numbers.
   * The resulting string is the formatted phone number.
   */
    public static String createPhoneNumber3(int[] numbers) {
        return String.format("(%d%d%d) %d%d%d-%d%d%d%d", numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5], numbers[6], numbers[7], numbers[8], numbers[9]);
    }
    

    //PROBLEM 6
    /*
     A pangram is a sentence that contains every single letter of the alphabet at least once. 
     For example, the sentence "The quick brown fox jumps over the lazy dog" is a pangram, because it uses the letters A-Z at least once (case is irrelevant).
     Given a string, detect whether or not it is a pangram. Return True if it is, False if not. Ignore numbers and punctuation.
     */
    static boolean pangram(String sentence){
        Set<Character> unique = new HashSet<>();
        for(Character character : sentence.toLowerCase().toCharArray()){
            int unicode = (int) character;
            if(Character.isAlphabetic(unicode)) unique.add(character);
        }
         return (long) unique.size() == 26; // NB!!! CONVERT TO LONG.
    }

    /*
     * Problem 6 alternative.
     * This approach uses a lambda expression to count the number of distinct letters in the sentence.
     * The toLowerCase() method converts the sentence to lowercase.
     * The chars() method returns a stream of characters from the sentence.
     * The filter() method filters the characters based on whether they are alphabetic.
     * The count() method counts the number of distinct characters that pass the filter.
     */
    static boolean pangram2(String sentence){
        long count = sentence.toLowerCase().chars().filter(Character::isAlphabetic).count();
        return count == 26;
    }

}
