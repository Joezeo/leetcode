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

    public TreeNode() {
    }

    public static TreeNode fromString(String array) {
        array = array.replaceAll("\\[", "").replaceAll("\\]", "");
        TreeNode root = new TreeNode();
        TreeNode cur = root;
        int deep = 1;
        Queue<String> queue = new ArrayDeque<>();
        for (String sp : array.split(",")) {
            queue.offer(sp);
            int cnt = 0;
            for (int i = 0; i < deep * 2 - 1; i++) {

            }
            if ("null".equals(sp)) {

            } else {
                cur.val = Integer.parseInt(sp);
            }
        }
        return null;
    }
}
