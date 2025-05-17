package za.co.wethinkcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        
        String numbers = "1 2 8 4 5 -46";
        System.out.println(highAndLow(numbers));
    }

    public static String highAndLow(String numbers) {
        List<Integer> intList = new ArrayList<>();
        Arrays.stream(numbers.split(" ")).forEach(n -> intList.add(Integer.parseInt(n)));
        Collections.sort(intList);
        return String.valueOf(intList.getLast()) + " " + String.valueOf(intList.getFirst());
    }
    
}

