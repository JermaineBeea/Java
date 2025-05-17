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
    static int reverseOrder(int number){
        boolean isPos = number > 0;
        number = Math.abs(number);

        List<Integer> listNum = new ArrayList<>();
        int reversed = 0;

        for(Character k : String.valueOf(number).toCharArray()){
            String strK = Character.toString(k);
            int intK = Integer.parseInt(strK);
            listNum.add(intK);
        }
        listNum.sort(Comparator.reverseOrder());

        for(int k = 0; k < listNum.size(); k++){
            reversed += listNum.get(k) * Math.pow(10, listNum.size() - (k + 1));
        }
        System.out.println(isPos);
        return isPos ? reversed : -reversed;
    }    

    static int reverseOrder2(int number){
        boolean isPos = number > 0;
        number = Math.abs(number);

        List<Integer> listNum = new ArrayList<>();
        StringBuilder strNum = new StringBuilder();

        for(Character k : String.valueOf(number).toCharArray()){
            String strK = Character.toString(k);
            int intK = Integer.parseInt(strK);
            listNum.add(intK);
        }
        listNum.sort(Comparator.reverseOrder());

        for(Integer n : listNum){
            strNum.append(n);
        }
        
        return isPos ? Integer.parseInt(strNum.toString()): -Integer.parseInt(strNum.toString());
    }
    


    // PROBLEM 2
    /*
     * Count the number of vowels in a string
     * Given a string, return the number of vowels in the string.
     * For example, given the string "hello world", return 3.
     * Note: The string may contain uppercase and lowercase letters.
     * For example, given the string "Hello World", return 3.
     */
    public static int vowelCount1(String str){
        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');
        int vowelCount = 0;
        for(Character k : str.toLowerCase().toCharArray()){
            if(vowels.contains(k)) vowelCount ++;
        }
        return vowelCount;
    }
    
    /*
     * Problem 2 alternative.
     * This approach uses regex to count the number of vowels in a string.
     * The regex pattern "(?i)[aeiou]" matches any vowel (case insensitive).
     * The replaceAll method replaces all occurrences of the pattern with an empty string.
     * The length of the resulting string is the number of vowels in the original string.
     */
    public static int vowelCount2(String str){
        return  (int) str.toLowerCase().chars().filter(k -> "aeiou".indexOf(k) >= 0).count();
    }

    /*
     * Problem 2 alternative.
     * This approach uses a lambda expression to count the number of vowels in a string.
     * The chars() method returns a stream of characters from the string.
     * The filter method filters the characters based on whether they are vowels.
     * The count method counts the number of characters that pass the filter.
     */
    public static long vowelCount3(String str){
        return str.toLowerCase().replaceAll("[^aeiou]", "").length();
    }



    //PROBLEM 3
    /*
     * Find the next perfect square
     * Given a number, return the next perfect square.
     * For example, given the number 4, return 9.
     * Note: The number may be negative, in which case the sign should be preserved.
     * For example, given the number -4, return -9.
     */
    public static int findNextSquare(int number){
        int sqroot = (int) Math.sqrt(number);
        boolean isSquare = sqroot * sqroot == number;
        return isSquare ? (sqroot + 1)*(sqroot + 1) : -1;
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

    static String uniqueLetters(String str1, String str2) {
        
        Set<Character> uniqueChars = new TreeSet<>();  
        
        String combined = (str1 + str2).toLowerCase();
        for (char c : combined.toCharArray()) {
            if(Character.isAlphabetic(c)){
                uniqueChars.add(c);
            }
        }
        
        StringBuilder result = new StringBuilder();
        for (Character c : uniqueChars) {
            result.append(c);
        }
        
        return result.toString();
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
    static String uniqueLetters2 (String str1, String str2) {
        String combined = (str1 + str2).toLowerCase();
        StringBuilder result =  new StringBuilder();
        combined.chars().distinct().filter(n -> Character.isAlphabetic(n)).forEach(k -> result.append((char) k));
        return result.toString();
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
    static String uniqueLetters3 (String s1, String s2) {
        String s = s1 + s2;
        return 
        
        s.chars().distinct().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
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
        Object[] arrayStream = java.util.stream.IntStream.of(numbers).boxed().toArray();
        return String.format("(%d%d%d) %d%d%d-%d%d%d%d", arrayStream);
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
            for(char c: sentence.toLowerCase().toCharArray()){
                if(Character.isAlphabetic(c)){
                    unique.add(c);
                }
            }
            return unique.size() == 26;
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
        int uniqueAlphaCount =  (int) sentence.toLowerCase().chars().distinct().filter(k -> Character.isAlphabetic(k)).count();
        return uniqueAlphaCount == 26;
    }

    /*
     * 
     */
    static boolean pangram3(String sentence){
        for (char c = 'a'; c<='z'; c++)
            if (!sentence.toLowerCase().contains("" + c))
                return false;
        return true;

  }


    //PROBLEM 7
    /*
     * You are given an array (which will have a length of at least 3, but could be very large) containing integers. 
     * The array is either entirely comprised of odd integers or entirely comprised of even integers except for a single integer N. 
     * Write a method that takes the array as an argument and returns this "outlier" N.
     * Example:
     * [2, 4, 0, 100, 4, 11, 2602, 36] -->  11 (the only odd number)
     * [160, 3, 1719, 19, 11, 13, -21] --> 160 (the only even number)
     */
    static int findOutlier(int[] integers) {
        int count = (int) Arrays.stream(Arrays.copyOfRange(integers, 0, integers.length)).filter(n -> n % 2 == 0).count();
        boolean evenDivisor = count >= 2 ;
        return (int) Arrays.stream(integers).filter(n -> evenDivisor ? n % 2 != 0 : n % 2 == 0).findFirst().getAsInt();
    }

    public static int findOutlier2(int[] integers) {
        // Since we are warned the array may be very large, we should avoid counting values any more than we need to.

        // We only need the first 3 integers to determine whether we are chasing odds or evens.
        // So, take the first 3 integers and compute the value of Math.abs(i) % 2 on each of them.
        // It will be 0 for even numbers and 1 for odd numbers.
        // Now, add them. If sum is 0 or 1, then we are chasing odds. If sum is 2 or 3, then we are chasing evens.
        int sum = Arrays.stream(integers).limit(3).map(i -> Math.abs(i) % 2).sum();
        int mod = (sum == 0 || sum == 1) ? 1 : 0;

        return Arrays.stream(integers).parallel() // call parallel to get as much bang for the buck on a "large" array
                .filter(n -> Math.abs(n) % 2 == mod).findFirst().getAsInt();
    }

}
