package za.co.wethinkcode;


public class Main {

    public static void main(String[] args) {
        System.out.println(isPalindrome("level"));
    }

    static boolean isPalindrome(String string) {
        StringBuilder str = new  StringBuilder();
        string.toLowerCase().chars().filter(Character::isAlphabetic).forEach(n-> str.append((char) n));
        System.out.println( str + ":" + str.reverse());
        return str == str.reverse();
    }
   
}
  