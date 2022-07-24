package com.toocol.lc.java;

import java.util.Stack;

/**
 * 树的遍历
 * 
 * @author Joe Zane (joezane.cn@gmail.com)
 */
public class TraversalTree {
    public static void main(String[] args) {
        TreeNode root = TreeNode.fromString("[1,2,3,4,5,6,7]");
        treeTraversal(root);
        treeTraversalRecursion(root);
    }

    /**
     * 树的中序遍历递归版
     */
    public static void treeTraversalRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        treeTraversalRecursion(node.left);
        System.out.print(node.val + " ");
        treeTraversalRecursion(node.right);
    }

    /**
     * 树的中序遍历遍历版
     */
    public static void treeTraversal(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.print(node.val + " ");
            node = node.right;
        }
        System.out.println();
    }
}
