package com.jazz.leetcode.algorithms;


import com.jazz.leetcode.algorithms.base.TreeNode;

public class Solution_98 {

   public static boolean isValidBST(TreeNode root) {
        return _valid(root ,Long.MIN_VALUE ,Long.MAX_VALUE);
    }

    /**
     * ·½·¨1
     * @param childNode
     * @param min
     * @param max
     * @return
     */
    private static boolean _valid(TreeNode childNode, long min, long max) {
        if(childNode == null ) return  true;
        return childNode.val>min  && childNode.val<max  && _valid(childNode.left , min  , childNode.val)  &&  _valid(childNode.right , childNode.val , max);
    }

    static int pre = Integer.MIN_VALUE ;
    public static boolean isValidBST_inOrder(TreeNode root) {
        if(root == null ) return true;

        if(!isValidBST_inOrder(root.left)) return false;

        if(root.val <= pre )return false;
        pre = root.val;

        if(!isValidBST_inOrder(root.right)) return false;
        return true;
    }



    public static void main(String[] args) {
        System.out.println(isValidBST(TreeNode.mkTree("[2,1,3]")));
        System.out.println(isValidBST(TreeNode.mkTree("[10,5,15,null,null,6,20]")));

        System.out.println(isValidBST_inOrder(TreeNode.mkTree("[2,1,3]")));
        System.out.println(isValidBST_inOrder(TreeNode.mkTree("[10,5,15,null,null,6,20]")));
    }

}
