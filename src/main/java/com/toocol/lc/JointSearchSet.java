package com.toocol.lc;

import java.util.Arrays;

/**
 * 并查集
 *
 * @author ：JoeZane (joezane.cn@gmail.com)
 * @date: 2022/7/5 23:55
 * @version: 0.0.1
 */
public class JointSearchSet {

    public static void main(String[] args) {
        JointSearchSet jointSearchSet = new JointSearchSet();
        int[][] graph = new int[][] {
                {0, 1}, {1, 2}, {1, 3}, {2, 3}, {2, 4}, {4, 5}, {3, 5}, {5, 6}, {8, 1}, {9, 10}, {13, 14}, {15, 16}, {16, 14}
        };
        int[] parent = new int[17], rank = new int[17];
        jointSearchSet.initialise(parent);
        for (int[] pos : graph) {
            jointSearchSet.joint(parent, rank, pos[0], pos[1]);
        }
        System.out.println(Arrays.toString(parent));
    }

    public void joint() {
    }

    public void initialise(int[] parent) {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public int findRoot(int[] parent, int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    public void joint(int[] parent, int[] rank, int x, int y) {
        int rootX = findRoot(parent, x);
        int rootY = findRoot(parent, y);
        if (rootX == rootY) {
            return;
        }
        if (rank[x] > rank[y]) {
            parent[y] = x;
            rank[x]++;
        } else if (rank[y] > rank[x]) {
            parent[x] = y;
            rank[y]++;
        } else {
            parent[x] = y;
            rank[y]++;
        }
    }

}
