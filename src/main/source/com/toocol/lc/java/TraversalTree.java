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

        preTraversalRecursion(root);
        System.out.println();
        preTraversal(root);
        System.out.println();

        middleTreeTraversal(root);
        System.out.println();
        middleTraversalRecursion(root);
        System.out.println();

        postTreeTraversal(root);
        System.out.println();
        postTraversalRecursion(root);
        System.out.println();
    }

    /**
     * 树的前序遍历递归版
     */
    public static void preTraversalRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preTraversalRecursion(node.left);
        preTraversalRecursion(node.right);
    }

    /**
     * 树的前序遍历迭代版
     */
    public static void preTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    /**
     * 树的中序遍历递归版
     */
    public static void middleTraversalRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        middleTraversalRecursion(node.left);
        System.out.print(node.val + " ");
        middleTraversalRecursion(node.right);
    }

    /**
     * 树的中序遍历迭代版
     */
    public static void middleTreeTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
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
    }

    /**
     * 树的后序遍历递归版
     */
    public static void postTraversalRecursion(TreeNode node) {
        if (node == null) {
            return;
        }
        postTraversalRecursion(node.left);
        postTraversalRecursion(node.right);
        System.out.print(node.val + " ");
    }

    /**
     * 树的后序遍历迭代版
     */
    public static void postTreeTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> postStack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            postStack.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!postStack.isEmpty()) {
            node = postStack.pop();
            System.out.print(node.val + " ");
        }
    }
}
