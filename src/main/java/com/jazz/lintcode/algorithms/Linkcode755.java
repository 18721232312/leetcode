package com.jazz.lintcode.algorithms;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team:
 * @date 2018/2/22 23:41
 */
public class Linkcode755
{
    public static int findPeak(int[] A) {
        // write your code here
//        for(int i=1;i<A.length-1;i++)
//            if(A[i]>A[i-1]&&A[i]>A[i+1])
//                return i;
//        return 0;

        int i = 1, j = A.length - 2;
        while (i <= j) {
            int m = (i + j) / 2;
            if (A[m - 1] < A[m] && A[m] > A[m + 1]) return m;
            if (A[m - 1] < A[m]) i = m + 1; else j = m - 1;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 1, 3, 4, 5,  5,5};
        System.out.println(findPeak(arr));

    }


}
