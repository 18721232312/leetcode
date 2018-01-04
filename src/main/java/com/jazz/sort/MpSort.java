package com.jazz.sort;

/**
 * 冒泡排序
 * Created by XUJING592 on 2018/1/4.
 */
public class MpSort {

    public static void main(String[] args) {
        int[] array = {3, 2, 18, 6, 55, 4, 677, 200,1};
//        sort1(array);
//        sort2(array);
        sort3(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    /**
     * 优化后的排序
     *  两边同时移动
     * @param array
     */
    private static void sort3(int[] array) {
        int low = 0 ;
        int hight  = array.length-1;
        while (low < hight){
           for (int j = low ; j < hight ; ++j ){
               if(array[j]>array[j+1]){
                   SortUtils.swap(array,j , j + 1);
               }
           }
           hight-- ;
            for (int j = hight; j>low ; --j ){
                if(array[j]<array[j-1]){
                    SortUtils.swap(array,j , j - 1);
                }
            }
            low++;
        }
    }


    /**
     * 优化后的排序
     *
     * @param array
     */
    private static void sort2(int[] array) {
        int lastSwapPosition = 0 ;
        int i = array.length-1;
        while (i>0 ) {
            lastSwapPosition = 0 ;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    SortUtils.swap(array, j, j + 1);
                    lastSwapPosition = j;
                }
            }
            i=lastSwapPosition;
        }
    }

    /**
     * 最原始的场
     *
     * @param array
     */
    private static void sort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    SortUtils.swap(array, j, j + 1);
                }
            }
        }
    }
}
