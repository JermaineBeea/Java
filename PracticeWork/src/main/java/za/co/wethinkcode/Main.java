package za.co.wethinkcode;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String strA = "xyaabbbcc1ccdefww";
        String strB = "xxx2xyyyya2bklmopq";
        String strC = strA + strB;
        
        List<Character> letters = new ArrayList<>();
        letters.add(strC.charAt(0));
        
        for(int n = 1; n < strC.length(); n++){
            try{
                Integer.parseInt(String.valueOf(strC.charAt(n)));
            }catch(NumberFormatException e){
                
            }
        }
        
        // Convert to list for sorting
        Collections.sort(letters);
        
        // Build the final string
        StringBuilder sorted = new StringBuilder();
        for(Character n : letters){
            sorted.append(n);
        }
        
        System.out.println(sorted.toString());
    }
    
}
