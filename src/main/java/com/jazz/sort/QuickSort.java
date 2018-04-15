package com.jazz.sort;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team:
 * @date 2018/1/26 23:19
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arrays = {6,89,21,1,96,3,9,88};
        sort(arrays,0,arrays.length-1);
        print(arrays);

    }

    private static void print(int[] arrays) {
        System.out.println("排序后的结果是：");
        for (int array : arrays) {
            System.out.println(array);
        }
    }

    private static void sort(int[] arrays, int start, int end) {
        if(start>end) return ;
        int pivot = arrays[start];
        int i = start+1;
        int j = end ;
        while(true){
            while(i<=end && arrays[i]<pivot){
                i++;
            }
            while(j>start  && arrays[j]>pivot){
                j--;
            }
            if(i<j){
                SortUtils.swap(arrays,i,j);
            }else{
                break;
            }
        }
        SortUtils.swap(arrays,start,j);
        sort(arrays,start,j-1);
        sort(arrays,j+1,end);
    }


//    private static int[] sort2(int [] arr , int start , int end ){
//        if(start)
//    }
}
