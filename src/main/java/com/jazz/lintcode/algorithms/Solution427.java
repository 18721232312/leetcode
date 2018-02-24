package com.jazz.lintcode.algorithms;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**27
 * Created by XUJING592 on 2018/2/23.
 */
public class Solution427 {
    /**
     * @param n: n pairs
     * @return: All combinations of well-formed parentheses
     */
    public static List<String> generateParenthesis(int n) {
        // write your code here
        ArrayList<String> ans = new ArrayList<String>();
        if (n == 0) return ans;
        String s = "";
        helper(n, n, s, ans);
        return ans;
    }
    /***
     * r为右括号个数，l为左括号个数
     * @param l
     * @param r
     * @param s
     * @param res
     */
    public static void helper(int l, int r, String s, ArrayList<String> res) {
        if (r < l) return;
        if (l == 0 && r == 0) {
            res.add(s);
        }
        if (l > 0) helper(l - 1, r, s + "(", res);
        if (r > 0) helper(l, r - 1, s + ")", res);
    }

    public static void main(String[] args) {
        System.out.println(Joiner.on(",").join(generateParenthesis(2)));
    }
}