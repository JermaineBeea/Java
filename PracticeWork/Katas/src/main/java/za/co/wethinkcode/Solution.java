package za.co.wethinkcode;
import java.util.*;
import java.util.stream.IntStream;


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        IntStream intStream = Arrays.stream(nums);
        if(nums.length <3) return result;
        if(nums.length == 3 && intStream.distinct().count() == 3){
            if(intStream.reduce(0, Integer::sum) == 0){
                
            }
        }

        return result;
    }
}