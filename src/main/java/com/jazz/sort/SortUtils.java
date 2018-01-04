package com.jazz.sort;

/**
 * Created by XUJING592 on 2018/1/4.
 */
public class SortUtils {

    public static void swap(int [] array ,  int i , int j ){
        if(array== null || array.length<Math.max(i,j)) return ;
        int  temp = array[i];
        array[i] = array[j];
        array[j] = temp ;
    }
}
