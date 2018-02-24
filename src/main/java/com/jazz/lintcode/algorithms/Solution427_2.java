package com.jazz.lintcode.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team:
 * @date 2018/2/24 21:40
 */
public class Solution427_2 {
    List<String>  res = null;
    public List<String> generateParenthesis(int n){
        res = new ArrayList<>();
        _gen(n ,  1 , 1 ,  "" );


        return res ;
    }

    private void _gen(int n , int left, int right, String curState) {
        if(right>left) {
            return ;
        }
        if(left ==n && right == n ){
            res.add(curState);
            return ;
        }
        if(left <n ){
            _gen(n , left +1 , right , curState+"(");
        }
        if(right<n ){
            _gen(n , left , right + 1 , curState+")");
        }
    }

    public static void main(String[] args) {
        Solution427_2 test = new Solution427_2();
        System.out.println(test.generateParenthesis(4));
    }

}
