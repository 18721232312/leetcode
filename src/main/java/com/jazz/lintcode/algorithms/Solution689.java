package com.jazz.lintcode.algorithms;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by XUJING592 on 2018/2/23.
 */
public class Solution689 {
    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    int[] res = null;

    public int[] twoSum(TreeNode root, int n) {
        Set<Integer> set = new HashSet<>();
        helper(root, n, set);
        return res;
    }

    private void helper(TreeNode root, int n, Set<Integer> set) {
        if (root == null) {
            return;
        }
        if (res != null) {
            return;
        }
        helper(root.left, n, set);
        if (set.contains(n - root.val)) {
            res = new int[]{root.val, n - root.val};
            return;
        } else {
            set.add(root.val);
        }
        helper(root.right, n, set);
    }

    public static void main(String[] args) {


    }
}
