package org.joezeo.leetcode.algorithm.before;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * <p>
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/4/8 12:26
 */
public class RobootMoving {
    public static void main(String[] args) {
        System.out.println(movingCount_stack(23, 43, 9));
        System.out.println(movingCount(23, 43, 9));
    }

    /**
     * 使用二维数组，递归执行深度优先算法
     */
    public static int movingCount(int m, int n, int k) {
        int[][] grid = new int[m][n];

        return count(grid, 0, 0, m, n, k);
    }

    public static int count(int[][] grid, int x, int y, int m, int n, int k) {
        int sum = x % 10 + x / 10 + y % 10 + y / 10; // 坐标位数和
        if (x >= m || x < 0 || y >= n || y < 0 || sum > k || grid[x][y] == 1) {
            return 0;
        }
        grid[x][y] = 1;
        return count(grid, x + 1, y, m, n, k) + count(grid, x - 1, y, m, n, k)
                + count(grid, x, y + 1, m, n, k) + count(grid, x, y - 1, m, n, k) + 1;
    }

    /**
     * 使用Stack深度优先算法
     * 速度比递归慢
     */
    public static int movingCount_stack(int m, int n, int k) {
        int count = 0;
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        Set<Pair<Integer, Integer>> record = new HashSet<>();
        Pair<Integer, Integer> start = new Pair(0, 0);
        record.add(start);
        stack.push(start);

        while (!stack.isEmpty()) {
            Pair<Integer, Integer> pair = stack.pop();
            int x = pair.getKey();
            int y = pair.getValue();
            int sum = x % 10 + x / 10 + y % 10 + y / 10; // 坐标位数和
            if (sum > k) {
                continue;
            }
            count++;
            // 向左移动
            if (x - 1 >= 0) {
                addToStack(stack, record, new Pair<>(x - 1, y));
            }
            // 向右移动
            if (x + 1 < m) {
                addToStack(stack, record, new Pair<>(x + 1, y));
            }
            // 向上移动
            if (y - 1 >= 0) {
                addToStack(stack, record, new Pair<>(x, y - 1));
            }
            // 向下移动
            if (y + 1 < n) {
                addToStack(stack, record, new Pair<>(x, y + 1));
            }
        }
        return count;
    }

    public static void addToStack(Stack<Pair<Integer, Integer>> stack, Set<Pair<Integer, Integer>> record, Pair<Integer, Integer> pair) {
        if (record.contains(pair)) {
            return;
        }
        record.add(pair);
        stack.push(pair);
    }
}
