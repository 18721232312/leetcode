package com.jazz.leetcode.algorithms;

import com.jazz.leetcode.algorithms.base.TreeNode;

public class Solution_100 {

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
//        System.out.println(isSameTree(TreeNode.mkTree("[1,2,3]"), TreeNode.mkTree("[1,2,6]")));
    }
}
