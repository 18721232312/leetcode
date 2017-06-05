package com.jazz.leetcode.algorithms;

import com.jazz.leetcode.algorithms.base.Tree;
import com.jazz.leetcode.algorithms.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * Created by XUJING592 on 2017/6/5.
 */
public class Solution_111 {

    public static  int minDepth(TreeNode root) {
        if (root == null ) return 0 ;
        int left  = minDepth(root.left);
        int right = minDepth(root.right);
        return Math.min(left , right )==0  ?Math.max(left ,right )+1: Math.min(left , right) + 1;
    }
    public static  int minDepth_bfs(TreeNode root) {
        if (root == null ) return 0 ;
        Queue<TreeNode> queue= new LinkedList();
        queue.add(root);
        Integer depth =  0;
        while (!queue.isEmpty()){
            depth++;
            int levelSize = queue.size();
            for (int i = 1; i <= levelSize; i++) {
                TreeNode  node = queue.poll();
                if(node.left == null && node.right == null ){
                    return depth ;
                }
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right !=null ){
                    queue.add(node.right);
                }
            }
        }
        return depth ;
    }

    public static  int minDepth_dfs(TreeNode root) {
        if(root == null ) return 0 ;
        int minDepth = Integer.MAX_VALUE ;
        Stack<TreeNode>  stack = new Stack<TreeNode>();
        stack.add(root);
        int level = 0 ;
        while(!stack.isEmpty()){
            int levelSize = stack.size();
            level ++ ;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = stack.pop();
                if(node.left!=null) stack.push(node.left);
                if(node.right!=null ) stack.push(node.right);
                if(node.left == null && node.right == null ){
                    minDepth =  Math.min(minDepth , level);
                    continue;
                }
            }
        }
        return minDepth;
    }
    public static void main(String[] args) {
//        System.out.println(minDepth_dfs(TreeNode.mkTree("[1,2,3,4,5]")));
//        System.out.println(minDepth_dfs(TreeNode.mkTree("[1,2]")));
//        System.out.println(minDepth_dfs(TreeNode.mkTree("[0]")));
        System.out.println(minDepth_dfs(TreeNode.mkTree("[1,2,3,4,null,null,5]")));

    }


}
