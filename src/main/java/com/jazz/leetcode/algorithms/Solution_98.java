package com.jazz.leetcode.algorithms;


import com.jazz.leetcode.algorithms.base.TreeNode;

public class Solution_98 {

   public static boolean isValidBST(TreeNode root) {
        return _valid(root ,Long.MIN_VALUE ,Long.MAX_VALUE);
    }

    private static boolean _valid(TreeNode childNode, long min, long max) {
        if(childNode == null ) return  true;
        if(childNode.val>min  && childNode.val<max){
            return _valid(childNode.left , min  , childNode.val)  &&  _valid(childNode.right , childNode.val , max);
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(isValidBST(TreeNode.mkTree("[2,1,3]")));
        System.out.println(isValidBST(TreeNode.mkTree("[10,5,15,null,null,6,20]")));

    }

}
