package com.jazz.leetcode.algorithms;

import java.util.HashMap;
import java.util.Map;

public class Solution_1 {
    public static int[] twoSum(int[] nums, int target) {
       if(nums ==null ) return null;
        Map<Integer, Integer > mapping = new HashMap<Integer,Integer>();
        int []  result = new int [2];
        for (int i=0 ; i <nums.length  ; i++) {
            if(mapping.containsKey(target-nums[i])) {
                result[1] = i ;
                result[0] =mapping.get(target-nums[i]);
                return result;
            }
            mapping.put(nums[i], i ++ );
        }
        return null;
    }

    public static void main(String[] args) {
        for (int s : twoSum(new int[]{3,3}, 6)) {
            System.out.println(s);
        }
    }
}