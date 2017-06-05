package com.jazz.leetcode.algorithms;

import com.jazz.leetcode.algorithms.base.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


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
        Set<TreeNode> visited = new HashSet();
        queue.add(root);
        visited.add(root);
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

    public static void main(String[] args) {
        System.out.println(minDepth_bfs(TreeNode.mkTree("[1,2,3,4,5]")));
        System.out.println(minDepth_bfs(TreeNode.mkTree("[1,2]")));
        System.out.println(minDepth_bfs(TreeNode.mkTree("[1,2,null,3,null,4,null,5]")));
        System.out.println(minDepth_bfs(TreeNode.mkTree("[1,2,3,4,null,null,5]")));

    }


}
