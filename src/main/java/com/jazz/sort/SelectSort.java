package com.jazz.sort;

/**
 * 选择排序
 * Created by XUJING592 on 2018/1/4.
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {3, 2, 18, 6, 55, 4, 677, 200,1};
        sort1(array);
        for (int i : array) {
            System.out.println(i);
        }
    }


    /**
     * 最原始的场
     *
     * @param array
     */
    private static void sort1(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    SortUtils.swap(array, i, j );
                }
            }
        }
    }
}
