package com.jazz.lintcode.algorithms;

import java.util.Arrays;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team: 新金融业务研发团队
 * @date 2018/2/25 0:18
 */
public class Solution724 {

    public static int findMin(int[] arr) {
        // write your code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        if(arr.length==2){
            return Math.abs(arr[0]-arr[1]);
        }
        int start = 0;
        int end = arr.length-1;
        int res  =0;
        int last = 0 ;
        while (start  <  end) {
            if(start==0){
                last = arr[start] + arr[end];
            }else{
                int temp =  arr[start] + arr[end];
                res = Math.abs(Math.abs(last )-Math.abs(temp));
            }
            start ++ ;
            end -- ;
        }
        if(arr.length%2==1)        res = Math.abs(Math.abs(res )-Math.abs(arr[start]));
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{1, 6, 11, 5,21}));
    }
}
