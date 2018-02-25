package com.jazz.lintcode.algorithms;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team: 新金融业务研发团队
 * @date 2018/2/25 0:18
 */
public class Solution718 {

    public static int repeatedString(String A, String B) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if(sb.toString().contains(B)) return count;
        if(sb.append(A).toString().contains(B)) return ++count;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(repeatedString("abcd", "cdabcdab"));
    }
}
