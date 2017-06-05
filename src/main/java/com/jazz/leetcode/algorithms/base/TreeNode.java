package com.jazz.leetcode.algorithms.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }
    public String toString(){
        return Integer.toString(val);
    }

    public static TreeNode mkTree(String tree) {
        // {1,2,3,4,#,#,#,5,#,6,#,7,#,8}
        tree = tree.substring(1, tree.length() - 1);
        String []ss = tree.split(",");
        return createTree(ss);
    }
    private static TreeNode constructOne(String s) {
        if (s.compareTo("null") == 0) {
            return null;
        } else {
            return new TreeNode(Integer.parseInt(s));
        }
    }
    public static TreeNode createTree(String[] tree) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        // 1st one should not be #
        TreeNode root = constructOne(tree[0]);
        q.add(root);
        int idx = 1;
        while (!q.isEmpty()) {

            TreeNode tn = q.poll();
            if (tn == null) {
                continue;
            }
            // construct tn's left&right node
            // when to stop
            if (idx == tree.length) {
                break;
            }
            TreeNode left_ = constructOne(tree[idx]);
            tn.left = left_;
            q.add(left_);
            idx++;
            if (idx == tree.length) {
                break;
            }
            TreeNode right_ = constructOne(tree[idx]);
            idx++;
            tn.right = right_;
            // add to queue
            q.add(right_);
        }
        return root;

    }

    private static void printNode(TreeNode tn, int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("\t");
        }
        sb.append(tn.val);
        System.out.println(sb.toString());
    }

    public static void printTree(TreeNode root, int indent) {
        if (root == null) {
            return;
        }
//      if (root.left == null && root.right == null) {
//          printNode(root, indent);
//      }
        // right
        printTree(root.right, indent + 1);
        // self
        printNode(root, indent);
        // left
        printTree(root.left, indent + 1);
    }

    public static void printTree(TreeNode root) {
        // right first
        printTree(root, 0);
    }

    public static void main(String[] args) {
        String str = "[1,2,null,3,null,4,null,5]";
        TreeNode node = TreeNode.mkTree(str);
        System.out.println(1);
    }
}
