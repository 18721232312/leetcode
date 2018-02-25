package com.jazz.lintcode.algorithms;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team: 新金融业务研发团队
 * @date 2018/2/25 0:18
 */
public class Solution729 {

    public static int computeLastDigit(long A, long B) {
        // write your code here
        if (B - A >= 10) {
            return 0;
        }
        int result = 1;
        for (long i = A + 1; i <= B; i++) {
            int temp = (int) (i % 10);
            result = (result * temp) % 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(computeLastDigit(20,28));
    }
}
