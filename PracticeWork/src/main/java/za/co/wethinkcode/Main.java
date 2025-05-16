package za.co.wethinkcode;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String sentence = "The quick brown fox jumps over the lazy dog.";
        boolean isPangram  = pangram(sentence);
        System.out.println(isPangram);


    }

    static boolean pangram(String sentence){
        Set<Character> unique = new HashSet<>();
        for(Character character : sentence.toLowerCase().toCharArray()){
            int unicode = (int) character;
            if(Character.isAlphabetic(unicode)) unique.add(character);
        }
        System.out.println(unique.size());
        return (long) unique.size() == 26;
    }
    
}
