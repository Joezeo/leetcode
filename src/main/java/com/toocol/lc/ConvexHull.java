package com.toocol.lc;

import java.util.Arrays;

/**
 * Andrew算法
 *
 * @author ZhaoZhe (joezane.cn@gmail.com)
 * @date 2022/7/14 12:54
 */
public class ConvexHull {

    public int[][] convexHull(int[][] trees) {
        // 对所有坐标进行排序，先根据x坐标升序，x相同根据y坐标升序
        Arrays.sort(trees, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int m = trees.length;
        // 维护凸包的点下标
        int[] stack = new int[m + 1];
        // 记录原数据的点的下标是否是凸包上的点
        boolean[] used = new boolean[m];
        // 栈顶下标
        int top = 0;
        stack[top++] = 0; // 不记录起始点，第二轮找上壳时也会用到起始点

        // 第一轮：找下壳
        for (int i = 1; i < m; i++) {
            int[] c = trees[i];
            while (top >= 2) { // 只有当栈里的点个数大于等于2时，才能凑齐三个点来进行向量判断
                int[] a = trees[stack[top - 2]]; int[] b = trees[stack[top - 1]];
                if (getArea(a, b, c) < 0) {
                    // ac 在 ab 的顺时针方向上, 去除栈顶b
                    used[stack[--top]] = false;
                } else {
                    // ac 在 ab 的逆时针方向上, 非凸包上的点
                    break;
                }
            }
            stack[top++] = i;
            used[i] = true;
        }
        int size = top; // 记录此刻栈中的元素个数（即下壳有多少点）
        // 第二轮: 找上壳
        for (int i = m - 1; i >= 0; i--) {
            if (used[i]) continue;
            int[] c = trees[i];
            while (top >= size + 1) { // 比下壳多增加了一个点，此时可以凑齐三个点进行向量判断
                int[] a = trees[stack[top - 2]]; int[] b = trees[stack[top - 1]];
                if (getArea(a, b, c) < 0) {
                    // ac 在 ab 的顺时针方向上, 去除栈顶b
                    used[stack[--top]] = false;
                } else {
                    // ac 在 ab 的逆时针方向上, 非凸包上的点
                    break;
                }
            }
            stack[top++] = i;
            used[i] = true;
        }
        int[][] ret = new int[top-1][2]; // 起始点记录了两次，需要top-1
        for (int i = 0; i < top-1; i++) {
            if (used[stack[i]]) {
                ret[i] = trees[stack[i]];
            }
        }
        return ret;
    }

    /**
     * 将两个点转化为向量
     * b 向量终点
     * a 向量起点
     */
    public int[] toVector(int[] b, int[] a) {
        return new int[]{b[0] - a[0], b[1] - a[1]};
    }

    /**
     * 向量作叉乘
     */
    public double cross(int[] a, int[] b) {
        return a[0]*b[1] - a[1]*b[0];
    }

    /**
     * 给定三个点a, b, c
     * 求从 ab -> ac 转化过程中扫过的面积s
     * 如 s > 0, 则表明从 ab -> ac 需要逆时针旋转
     * 如 s < 0, 则表明从 ab -> ac 需要顺时针旋转
     * 如 s = 0, 则表明从 ab, ac 在同一水平线上
     */
    public double getArea(int[] a, int[] b, int[] c) {
        return cross(toVector(b, a), toVector(c, a));
    }
}
