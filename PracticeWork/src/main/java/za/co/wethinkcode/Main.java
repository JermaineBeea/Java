package za.co.wethinkcode;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
    //   System.out.println(createPhoneNumber2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
    }
    
    static boolean pangram(String str) {
        int uniqueAlphaCount =  (int) str.toLowerCase().chars().distinct().filter(k -> Character.isAlphabetic(k)).count();
        return uniqueAlphaCount == 26;
    }
}

