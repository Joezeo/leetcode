package com.toocol.lc.java;

import java.util.Arrays;

/**
 * 线段树
 *
 * @author ：JoeZane (joezane.cn@gmail.com)
 * @date: 2022/7/5 23:54
 * @version: 0.0.1
 */
public class SegmentTree {

    /**
     * li 线段树中左节点下标，叶子节点此值为-1
     * ri 线段树中右节点下标，叶子节点此值为-1
     * idx 只有叶子节点有此值，表示在原数组中的下标，非叶子节点此值为-1
     * lazy 懒标记
     * val 值
     * range 该节点所包含的原数组下标范围(左闭右闭)
     */
    static class Node {
        public int li, ri, idx, lazy, val;
        public final int[] range = new int[2];

        public Node(int li, int ri, int idx, int lazy, int val) {
            this.li = li;
            this.ri = ri;
            this.idx = idx;
            this.lazy = lazy;
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 9, 11, 13, 15};
        Node[] tree = new Node[arr.length * 4];
        SegmentTree segmentTree = new SegmentTree();
        segmentTree.build(tree, arr, 0, 0, arr.length - 1);
        System.out.println(Arrays.toString(tree));
    }

    public void build(Node[] tree, int[] arr, int node, int st, int ed) {
        if (st == ed) {
            tree[node] = new Node(-1, -1, st, 0, arr[st]);
            tree[node].range[0] = st;
            tree[node].range[1] = ed;
            return;
        }
        int mid = st + ((ed - st) >> 1);
        int left = (node << 1) + 1;
        int right = (node << 1) + 2;
        build(tree, arr, left, st, mid);
        build(tree, arr, right, mid + 1, ed);
        tree[node] = new Node(left, right, -1, 0, tree[left].val + tree[right].val);
        tree[node].range[0] = st;
        tree[node].range[1] = ed;
    }

}
