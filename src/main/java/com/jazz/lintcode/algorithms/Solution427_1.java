package com.jazz.lintcode.algorithms;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**27
 * Created by XUJING592 on 2018/2/23.
 */
public class Solution427_1 {
    /**
     * @param n: n pairs
     * @return: All combinations of well-formed parentheses
     */
    public static List<String> generateParenthesis(int n) {
        // write your code here
        ArrayList<String> res = new ArrayList<String>();
        if(n==0) return res ;
        String temp = new String();
        _gen(n , n ,temp,res);
        return res;
    }

    private static void _gen(int l, int r, String temp, ArrayList<String> res) {
        /**
         * 如果右括号使用太多， 剩余的就比左括号少了 就终止
         */
        if(r < l) return ;
        if(l==0 && r==0){
            res.add(temp);
        }
        if(l>0) _gen(l-1 , r , temp+"(" , res );
        if(r>0) _gen(l , r-1 , temp+")" , res );
    }

    public static void main(String[] args) {
        System.out.println(Joiner.on(",").join(generateParenthesis(2)));
    }
}