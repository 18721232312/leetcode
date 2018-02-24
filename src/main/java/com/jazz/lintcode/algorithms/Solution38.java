package com.jazz.lintcode.algorithms;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**27
 * Created by XUJING592 on 2018/2/23.
 */
public class Solution38 {
    /*
  * @param matrix: A list of lists of integers
  * @param target: An integer you want to search in matrix
  * @return: An integer indicate the total occurrence of target in the given matrix
  */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        int x = row;
        int y = 0;
        int res = 0;
        while(x >=0 && y <= col){
            if(matrix[x][y] == target){
                x--;
                y++;
                res++;
            }else if(matrix[x][y] > target){
                x--;
            }else{
                y++;
            }
        }
        return res;
    }
}