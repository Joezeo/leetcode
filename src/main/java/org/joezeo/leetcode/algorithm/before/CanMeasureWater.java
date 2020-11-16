package org.joezeo.leetcode.algorithm.before;

import javafx.util.Pair;

import java.util.*;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * <p>
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * <p>
 * 你允许：
 * <p>
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 */
public class CanMeasureWater {
    public static void main(String[] args) {
    }

    // 使用bfs
    // 在任何时刻，两个桶能够做的操作如下：
    // 1.填满桶x
    // 2.填满桶y
    // 3.排空桶x
    // 4.排空桶y
    // 5.x向y倒水,直至倒满或者倒空
    // 6.y向x倒水，直至倒满或者倒空
    // 以cur_x,cur_y表示两个桶剩余的水量
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        Set<Pair<Integer, Integer>> visted = new HashSet<>(); // 用于存储已经访问过的状态
        Pair<Integer, Integer> start = new Pair<>(0, 0);
        visted.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();
            Integer cur_x = pair.getKey();
            Integer cur_y = pair.getValue();
            if (cur_x == z || cur_y == z || cur_x + cur_y == z) {
                return true;
            }

            // 向x中填满水
            addIntoQueue(queue, visted, x, cur_y);
            // 向y中填满水
            addIntoQueue(queue, visted, cur_x, y);
            // 排空x
            addIntoQueue(queue, visted, 0, cur_y);
            // 排空y
            addIntoQueue(queue, visted, cur_x, 0);
            // x向y倒水，直至倒满或者倒空
            addIntoQueue(queue, visted, cur_x - Math.min(y - cur_y, cur_x), cur_y + Math.min(y - cur_y, cur_x));
            // y向x倒水，直至倒满或者倒空
            addIntoQueue(queue, visted, cur_x + Math.min(x - cur_x, cur_y), cur_y - Math.min(x - cur_x, cur_y));
        }
        return false;
    }

    public void addIntoQueue(Queue<Pair<Integer, Integer>> queue, Set<Pair<Integer, Integer>> visted, Integer cur_x, Integer cur_y) {
        Pair<Integer, Integer> pair = new Pair<>(cur_x, cur_y);
        if (!visted.contains(pair)) {
            visted.add(pair);
            queue.add(pair);
        }
    }
}
