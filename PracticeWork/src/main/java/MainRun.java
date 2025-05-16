// package src.main.java;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainRun{

    public static void main(String[] args) {

        // Problem 1: Sort an intger in desending order
        int num = 143567;
        int reversed = reverseOrder(num);
        System.out.println(reversed);

        // Problem 2: Count all the vowels in a string
        String word = "Madagascar";
        int count =  vowelCount1(word);
        System.out.println(count);

        /* 
        Problem 3: 
        Complete the findfindNextSquare method that finds the next integral perfect square after the one passed as a parameter. 
        Return null if its not a square number
        Recall that an integral perfect square is an integer n such that sqrt(n) is also an integer.
        121 --> 144
        625 --> 676
        114 --> -1  #  because 114 is not a perfect square
        */
        long square = 26;
        long square2 = 100;
        long result = findNextSquare(square);
        long result2 = findNextSquare(square2);

        System.out.println(result);
        System.out.println(result2);
    }

    public static int reverseOrder(int num){
        List<Integer> listNum = new ArrayList<>();
        for(char k : String.valueOf(num).toCharArray()){
            listNum.add(k - '0');
        }

        listNum.sort(Comparator.reverseOrder());

        StringBuilder strNum = new StringBuilder();
        for(int n : listNum){
            strNum.append(n);
        }

        int reversedNum = Integer.parseInt(strNum.toString());
        return reversedNum;
    }
    
    // Solutions to Problem 2
    public static int vowelCount1(String string){
        List<Integer> unicodes = new ArrayList<>();
        Character[] vowels = {'a', 'e', 'i', 'o', 'u'};

        for(Character character : vowels){
            unicodes.add((int) character);
        }

        return (int) string.toLowerCase().chars().filter(k -> unicodes.contains(k)).count();
    }

    public static int vowelCount2(String str){
        return str.replaceAll("(?i)^[aeiou]", "").length();
    }

    public static int vowelCount3(String str){
        return (int) str.toLowerCase().chars().filter(k -> "aeiou".indexOf(k) >= 0).count();
    }


    public static long findNextSquare(long squareInt){
        // Check if squareInt is a square number
        long sqroot = (long) Math.sqrt(squareInt);
        if( ! (sqroot * sqroot == squareInt)) return -1;

        // If squareInt is a square, then get the squre if the root plus 1;
        return (long) (sqroot + 1) * (sqroot + 1);
    }

    // Solutions to problem 3.
    public static long findfindNextSquare(long sq) {
        long root = (long) Math.sqrt(sq);
        return root * root == sq ? (root + 1) * (root + 1) : -1;
    }
}