package com.jazz.lintcode.algorithms;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team:
 * @date 2018/2/22 22:34
 */
public class Lintcode617 {

    /*
    * @param nums: an array with positive and negative numbers
    * @param k: an integer
    * @return: the maximum average
    */
    public static double maxAverage(int[] nums, int k) {

        int len = nums.length;  //数组长度
        int count = 0;  //数组指针
        double maxSum = 0;  //最大子数组的和

        for (int i =0  ; i < k ; i ++ ) {
            maxSum +=nums[i];
        }
        /*
         * 主要算法
         */
        while(count <= len - k){
            //temp每次大循环都要重置,所以在这里新建
            double temp = 0;
            //从第二数开始每k个数求和并赋给temo
            for (int j = 0; j < k ; j++) {
                temp += nums[count+j];
                System.out.print(nums[count+j] +",");
            }
            System.out.println("-------------------"+temp +" ==== " +temp/k);
//            System.out.println("-------------------"+temp/k);
            //比较temp和maxSun的值,记住每次计数器都要向后移一位
            if(temp > maxSum){
                maxSum = temp;
            }
            count++;
        }
        return maxSum/k;
    }

    public static void main(String[] args) {
        int[] array = {-1,-2,-3,-100,-1,-50};

        System.out.println( maxAverage(array,4));
    }
}
