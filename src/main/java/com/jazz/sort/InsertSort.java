package com.jazz.sort;

/**
 * 插入排序
 * Created by XUJING592 on 2018/1/4.
 */
public class InsertSort {

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
     * 优化后的算法  插入时，改为二分查找
     * 1、二分查找 定位要插入的位置left
     * 2、移动要插入位置后面到i-1之间的数据 后移，腾出left的位置
     * 3、把当前的数值插入到left位置
     * @param array
     */
    private static void sort3(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int current =  array[i];
            int left= 0 , right  = i-1;
            int mid = 0 ;
            while(left <= right ){
                mid = (left+right)/2;
                if(current<array[mid]){
                    right = mid-1 ;
                }else{
                    left = mid+1 ;
                }
            }
            for(int x = i-1 ; x>=left  ; x--){
                array[x+1] = array[x];
            }
            array[left] = current;
        }
    }
    /**
     * 优化后的算法
     *
     * @param array
     */
    private static void sort2(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int current =  array[i];
//            int j = i-1 ;
//            while (j>=0 &&  current<array[j]){
//                array[j+1]=array[j];
//                j--;
//            }
            int j = 0 ;
            for ( j = i-1; j >=0 && current < array[j]; j--) {
                   array[j+1]=array[j];
            }
            array[j+1] = current;
        }
    }
    /**
     * 最原始的场
     *
     * @param array
     */
    private static void sort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i ; j++) {
                if (array[i] < array[j]) {
                    SortUtils.swap(array, j, i);
                }
            }
        }
    }
}
