package za.co.wethinkcode;

import java.util.*;

public class KataSolutions {

   // PROBLEM 1
    /**
     * Sort the digits of a number in descending order
     * Given a number, return the number with its digits sorted in descending order.
     * For example, given the number 12345, return 54321.
     * Note: The number may be negative, in which case the sign should be preserved.
     * For example, given the number -12345, return -54321.
     * Constraints: The number will be a 32-bit signed integer.
     * The number will not be zero.
     */
    static int descendingOrder(int number){
        boolean isPos = number > 0;
        number = Math.abs(number);

        List<Integer> listNum = new ArrayList<>();
        int reversed = 0;

        // Convert each digit to an integer and add to list
        for(Character k : String.valueOf(number).toCharArray()){
            String strK = Character.toString(k);
            int intK = Integer.parseInt(strK);
            listNum.add(intK);
        }
        // Sort digits in descending order
        listNum.sort(Comparator.reverseOrder());

        // Reconstruct the number from the sorted digits
        for(int k = 0; k < listNum.size(); k++){
            reversed += listNum.get(k) * Math.pow(10, listNum.size() - (k + 1));
        }
        return isPos ? reversed : -reversed;
    }    
    
    /**
     * Problem 1 alternative.
     * This approach also sorts digits in descending order using a StringBuilder.
     */
    static int descendingOrder2(int number){
        boolean isPos = number > 0;
        number = Math.abs(number);

        List<Integer> listNum = new ArrayList<>();
        StringBuilder strNum = new StringBuilder();

        // Extract digits to list
        for(Character k : String.valueOf(number).toCharArray()){
            String strK = Character.toString(k);
            int intK = Integer.parseInt(strK);
            listNum.add(intK);
        }
        // Sort digits in descending order
        listNum.sort(Comparator.reverseOrder());

        // Rebuild number as string
        for(Integer n : listNum){
            strNum.append(n);
        }
        
        return isPos ? Integer.parseInt(strNum.toString()): -Integer.parseInt(strNum.toString());
    }
        


    // PROBLEM 2
    /**
     * Count the number of vowels in a string
     * Given a string, return the number of vowels in the string.
     * For example, given the string "hello world", return 3.
     * Note: The string may contain uppercase and lowercase letters.
     * For example, given the string "Hello World", return 3.
     */
    public static int vowelCount1(String str){
        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');
        int vowelCount = 0;
        // Iterate through each character and check if it's a vowel
        for(Character k : str.toLowerCase().toCharArray()){
            if(vowels.contains(k)) vowelCount ++;
        }
        return vowelCount;
    }
    
    /**
     * Problem 2 alternative.
     * Uses character streams to count vowels with a functional approach.
     */
    public static int vowelCount2(String str){
        // Convert to char stream, filter for vowels, and count
        return (int) str.toLowerCase().chars().filter(k -> "aeiou".indexOf(k) >= 0).count();
    }

    /**
     * Problem 2 alternative.
     * Uses regex to count vowels by removing all non-vowels and measuring length.
     */
    public static long vowelCount3(String str){
        // Remove all non-vowels and count the remaining characters
        return str.toLowerCase().replaceAll("[^aeiou]", "").length();
    }



    //PROBLEM 3
    /**
     * Find the next perfect square
     * Given a number, return the next perfect square if the input is a perfect square.
     * For example, given the number 4, return 9.
     * If the input is not a perfect square, return -1.
     * For example, given the number 7, return -1.
     */
    public static int findNextSquare(int number){
        int sqroot = (int) Math.sqrt(number);
        boolean isSquare = sqroot * sqroot == number;
        return isSquare ? (sqroot + 1)*(sqroot + 1) : -1;
    }


    //PROBLEM 4
    /**
     * Take 2 strings s1 and s2 including only letters from a to z. 
     * Return a new sorted string (alphabetical ascending), the longest possible, containing distinct letters - each taken only once - coming from s1 or s2.
     * Examples:
     * a = "xyaabbbccccdefww"
     * b = "xyaabbbccccdefww"
     * longest(a, b) => "abcdefwxy"
     * 
     * a = "abcdefghijklmnopqrstuvwxyz"
     * b = "abcdefghijklmnopqrstuvwxyz" 
     * longest(a, b) => "abcdefghijklmnopqrstuvwxyz"
     */
    static String uniqueLetters(String str1, String str2) {
        // TreeSet provides automatic sorting and uniqueness
        Set<Character> uniqueChars = new TreeSet<>();  
        
        String combined = (str1 + str2).toLowerCase();
        // Add only alphabetic characters to set
        for (char c : combined.toCharArray()) {
            if(Character.isAlphabetic(c)){
                uniqueChars.add(c);
            }
        }
        
        // Build result string from sorted unique characters
        StringBuilder result = new StringBuilder();
        for (Character c : uniqueChars) {
            result.append(c);
        }
        
        return result.toString();
    }

    /**
     * Problem 4 alternative.
     * Uses stream operations but doesn't sort the characters.
     * Note: This implementation is missing the sorting requirement.
     */
    static String uniqueLetters2 (String str1, String str2) {
        String combined = (str1 + str2).toLowerCase();
        StringBuilder result = new StringBuilder();
        // Missing sorting operation here - will not return alphabetically sorted result
        combined.chars().distinct().filter(n -> Character.isAlphabetic(n)).forEach(k -> result.append((char) k));
        return result.toString();
    }

    /**
     * Problem 4 alternative.
     * Uses streams with proper sorting to create a sorted unique character string.
     */
    static String uniqueLetters3 (String s1, String s2) {
        String s = s1 + s2;
        // Process chars as a stream: make distinct, sort, and collect into string
        return s.chars().distinct().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }


    // PROBLEM 5
    /**
     * Write a function that accepts an array of 10 integers (between 0 and 9), that returns a string of those numbers in the form of a phone number.
     * The returned format should be "(xxx) xxx-xxxx" where x is the given integer.
     * Note that the numbers in the array are not necessarily distinct.
     * Example: Kata.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}) // => returns "(123) 456-7890"
     */
    static String createPhoneNumber(int[] numbers) {
        // Manually construct phone number with specific formatting
        StringBuilder strNumber = new StringBuilder("(");
        for(int n = 0; n < 3; n++) strNumber.append(numbers[n]);
        strNumber.append(") ");
        for(int n = 3; n < 6; n++) strNumber.append(numbers[n]);
        strNumber.append("-");
        for(int n = 6; n < 10; n++) strNumber.append(numbers[n]);
        return strNumber.toString();
    }

    /**
     * Problem 5 alternative.
     * Uses Java's String.format with variadic arguments from boxed int array.
     */
    public static String createPhoneNumber2(int[] numbers) {
        // Convert int[] to Object[] for variadic arguments
        Object[] arrayStream = java.util.stream.IntStream.of(numbers).boxed().toArray();
        return String.format("(%d%d%d) %d%d%d-%d%d%d%d", arrayStream);
    }

    /**
     * Problem 5 alternative.
     * Uses String.format with explicit arguments for clearer, more direct formatting.
     */
    public static String createPhoneNumber3(int[] numbers) {
        // Directly reference array indices for formatting
        return String.format("(%d%d%d) %d%d%d-%d%d%d%d", 
                            numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], 
                            numbers[5], numbers[6], numbers[7], numbers[8], numbers[9]);
    }
    

    //PROBLEM 6
    /**
     * A pangram is a sentence that contains every single letter of the alphabet at least once. 
     * For example, the sentence "The quick brown fox jumps over the lazy dog" is a pangram, because it uses the letters A-Z at least once (case is irrelevant).
     * Given a string, detect whether or not it is a pangram. Return True if it is, False if not. Ignore numbers and punctuation.
     */
    static boolean pangram(String sentence){
        // Use HashSet to track unique alphabetic characters
        Set<Character> unique = new HashSet<>();
        for(char c: sentence.toLowerCase().toCharArray()){
            if(Character.isAlphabetic(c)){
                unique.add(c);
            }
        }
        // Check if all 26 letters are present
        return unique.size() == 26;
    }

    /**
     * Problem 6 alternative.
     * Uses functional stream operations to check for pangram.
     */
    static boolean pangram2(String sentence){
        // Count unique alphabetic characters using streams
        int uniqueAlphaCount = (int) sentence.toLowerCase().chars()
                                    .distinct()
                                    .filter(k -> Character.isAlphabetic(k))
                                    .count();
        return uniqueAlphaCount == 26;
    }

    /**
     * Problem 6 alternative.
     * Uses direct character checking - more readable approach.
     */
    static boolean pangram3(String sentence){
        // Check if each letter appears at least once
        for (char c = 'a'; c <= 'z'; c++)
            if (!sentence.toLowerCase().contains("" + c))
                return false;
        return true;
    }


    //PROBLEM 7
    /**
     * You are given an array (which will have a length of at least 3, but could be very large) containing integers. 
     * The array is either entirely comprised of odd integers or entirely comprised of even integers except for a single integer N. 
     * Write a method that takes the array as an argument and returns this "outlier" N.
     * Example:
     * [2, 4, 0, 100, 4, 11, 2602, 36] -->  11 (the only odd number)
     * [160, 3, 1719, 19, 11, 13, -21] --> 160 (the only even number)
     */
    static int findOutlier(int[] integers) {
        // Determine if majority of first elements are even
        int count = (int) Arrays.stream(Arrays.copyOfRange(integers, 0, integers.length))
                          .filter(n -> n % 2 == 0)
                          .count();
        boolean evenDivisor = count >= 2;
        
        // Find the outlier based on determined majority
        return (int) Arrays.stream(integers)
                     .filter(n -> evenDivisor ? n % 2 != 0 : n % 2 == 0)
                     .findFirst()
                     .getAsInt();
    }

    /**
     * Problem 7 alternative.
     * More efficient implementation that examines only the necessary elements.
     */
    public static int findOutlier2(int[] integers) {
        // Calculate oddness/evenness of first three numbers to determine majority type
        int sum = Arrays.stream(integers)
                    .limit(3)
                    .map(i -> Math.abs(i) % 2)
                    .sum();
                    
        // If sum is 0 or 1, majority is even (looking for odd outlier)
        // If sum is 2 or 3, majority is odd (looking for even outlier)
        int mod = (sum == 0 || sum == 1) ? 1 : 0;

        // Use parallel stream for performance on large arrays
        return Arrays.stream(integers)
                 .parallel()
                 .filter(n -> Math.abs(n) % 2 == mod)
                 .findFirst()
                 .getAsInt();
    }


    //PROBLEM 8
    /**
     * In this assignment you are given a string of space separated numbers, and have to return the highest and lowest number.
     * Examples
       highAndLow("1 2 3 4 5") // return "5 1"
       highAndLow("1 2 -3 4 5") // return "5 -3"
       highAndLow("1 9 3 4 -5") // return "9 -5"

       Notes:
       There will always be at least one number in the input string.
       Output string must be two numbers separated by a single space, and highest number is first.
     */
    public static String highAndLow(String numbers) {
        List<Integer> intList = new ArrayList<>();
        // Parse each number and add to list
        Arrays.stream(numbers.split(" ")).forEach(n -> intList.add(Integer.parseInt(n)));
        Collections.sort(intList);
        // Return highest (last) and lowest (first) after sorting
        return String.valueOf(intList.getLast()) + " " + String.valueOf(intList.getFirst());
    }
    
    /**
     * Problem 8 alternative.
     * More concise implementation using Collections utility methods.
     */
    public static String highAndLow2(String numbers) {
        // Convert to integers and find min/max directly
        List<Integer> nums = Arrays.stream(numbers.split(" "))
                               .map(Integer::parseInt)
                               .toList();
        return "%s %s".formatted(Collections.max(nums), Collections.min(nums));
    }
}