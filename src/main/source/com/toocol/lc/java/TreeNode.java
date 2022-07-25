package com.toocol.lc.java;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode() {
    }

    public static TreeNode fromString(String array) {
        array = array.replaceAll("\\[", "").replaceAll("\\]", "");
        Queue<String> queue = new ArrayDeque<>();
        for (String sp : array.split(",")) {
            queue.offer(sp);
        }
        String val = queue.poll();
        if ("null".equals(val)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);
        boolean buildLeft = true;
        while (!queue.isEmpty()) {
            val = queue.poll();
            if ("null".equals(val)) {
                if (!buildLeft) {
                    buildLeft = true;
                    nodes.poll();
                }
                continue;
            }
            TreeNode node = nodes.peek();
            if (buildLeft) {
                TreeNode left = new TreeNode(Integer.parseInt(val));
                node.left = left;
                nodes.offer(left);
                buildLeft = false;
            } else {
                nodes.poll();
                TreeNode right = new TreeNode(Integer.parseInt(val));
                node.right = right;
                nodes.offer(right);
                buildLeft = true;
            }
        }
        return root;
    }
}
