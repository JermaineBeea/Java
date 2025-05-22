package za.co.wethinkcode;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(isPalindrome("level"));
        System.out.println(Character.toUpperCase(0));
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
  