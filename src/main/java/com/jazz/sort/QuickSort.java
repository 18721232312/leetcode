package com.jazz.sort;

/**
 * @author BK
 * @version V2.0
 * @description:
 * https://www.jianshu.com/p/2ae6ba100d3a
 * @team:
 * @date 2018/1/26 23:19
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arrays = {102, 3, 5, 52, 6, 777, 88, 3, 32, 23, 15};
//        int[] arrays = {6,89,21,1,96,3,9,88};
        sort(arrays, 0, arrays.length - 1);
        print(arrays);

    }

    private static void print(int[] arrays) {
        System.out.println("排序后的结果是：");
        for (int array : arrays) {
            System.out.println(array);
        }
    }

    private static void sort(int[] arrays, int start, int end) {
        if (start > end) return;
        int pivot = arrays[start];
        int left = start + 1;
        int right = end;
        while (left <= right) {
            while (left <= right && arrays[left] <= pivot) {
                left++;
            }
            while (left <= right && arrays[right] >= pivot) {
                right--;
            }
            if (left <= right) {
                SortUtils.swap(arrays, left, right);
            }
        }
        SortUtils.swap(arrays, start, right);
        sort(arrays, start, right - 1);
        sort(arrays, right + 1, end);
    }

}
