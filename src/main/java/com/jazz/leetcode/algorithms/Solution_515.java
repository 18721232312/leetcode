package com.jazz.leetcode.algorithms;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.jazz.leetcode.algorithms.base.TreeNode;

import java.util.*;


/**
 * Created by XUJING592 on 2017/6/5.
 */
public class Solution_515 {
    public List<Integer> largestValues(TreeNode root) {
        if(root == null ) return Lists.newArrayList();
        List<Integer> levelMax = new ArrayList<Integer>(0);
        int level = 0;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
              for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                  if(levelMax.size()<=level){
                      levelMax.add(currentNode.val);
                  }else{
                      levelMax.set(level,Math.max(levelMax.get(level) , currentNode.val));
                  }
                if(currentNode.left!=null) queue.add(currentNode.left);
                if(currentNode.right!=null) queue.add(currentNode.right);
            }
            level ++;
        }
        return levelMax;
    }
    private  List<Integer> levelMax = new ArrayList<Integer>(0);
    public  List<Integer> largestValues_DFS(TreeNode root) {
        _DFS(root, 0  );
        return levelMax;
    }

    private  void _DFS(TreeNode node, int level) {
        if(node == null ) return ;
        updateLevelMax(node , level);
        _DFS(node.left , level+1);
        _DFS(node.right , level+1);
    }

    private  void updateLevelMax(TreeNode node, int level) {
        if(levelMax.size()<=level){
            levelMax.add(node.val);
        }else{
            levelMax.set(level, Math.max(node.val,levelMax.get(level)));
        }
    }

    public static void main(String[] args) {
        Solution_515 test = new Solution_515();
//        System.out.println(Joiner.on(",").join(test.largestValues_DFS(TreeNode.mkTree("[1,2,3,4,5]"))));
//        System.out.println(Joiner.on(",").join(largestValues_DFS(TreeNode.mkTree("[1,2]"))));
        System.out.println(Joiner.on(",").join(test.largestValues_DFS(TreeNode.mkTree("[1,2,null,3,null,4,null,5]"))));
//        System.out.println(Joiner.on(",").join(largestValues_DFS(TreeNode.mkTree("[1,2,3,4,null,null,5]"))));

    }


}
