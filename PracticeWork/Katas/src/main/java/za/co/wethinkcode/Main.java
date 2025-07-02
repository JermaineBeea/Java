package za.co.wethinkcode;

import static java.util.Arrays.stream;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
       long number = 23445;
      System.out.println(String.format("R%.2f", (double)number/100));
    }

    
    public static int findOdd(int[] arr) {
        return stream(arr).reduce(0, (x, y) -> x ^ y);
  }

    static boolean isPalindrome(String string) {
        StringBuilder str = new  StringBuilder();
        string.toLowerCase().chars().filter(Character::isAlphabetic).forEach(n-> str.append((char) n));
        System.out.println( str + ":" + str.reverse());
        return str == str.reverse();
    }

    

    static String toCamelCase(String s){
        StringBuilder result = new StringBuilder();
        for(int n = 0; n < s.length(); n++){
          Character currentChar = s.charAt(n);
          if(! Character.isAlphabetic(currentChar)){
            result.append(Character.toUpperCase(s.charAt(n + 1)));
            n++;
            continue;
          }
          result.append(s.charAt(n));
        }
        
        return result.toString();
      }

      static String toCamelCase2(String str){
    String[] words = str.split("[-_]");
    return Arrays.stream(words, 1, words.length)
            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
            .reduce(words[0], String::concat);
  }
   
}
  