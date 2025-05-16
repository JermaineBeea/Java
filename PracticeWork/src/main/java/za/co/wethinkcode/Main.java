package za.co.wethinkcode;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String strA = "xyaabbbcc1ccdefww";
        String strB = "xxx2xyyyya2bklmopq";
        
        Set<Character> letters = new HashSet<>();
        for(Character k : (strA + strB).toCharArray()){
            try{
                Integer.parseInt(String.valueOf(k));
            }catch(NumberFormatException e){
                letters.add(k);
            }
        }
        
        // Convert to list for sorting
        List<Character> sortedList = new ArrayList<>(letters);
        Collections.sort(sortedList);
        
        // Build the final string
        StringBuilder sorted = new StringBuilder();
        for(Character n : sortedList){
            sorted.append(n);
        }
        
        System.out.println(sorted.toString());
    }
    
}
