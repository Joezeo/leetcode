package org.joezeo.leetcode.algorithm.before;

import javafx.util.Pair;

import java.util.*;

/**
 * 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
 * <p>
 * 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
 * <p>
 * 如果我们的地图上只有陆地或者海洋，请返回 -1。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/as-far-from-land-as-possible
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AsFarFromLandAsPossible {
    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        System.out.println(maxDistance_dfs(grid));
    }

    /**
     * 采用广度优先算法 (BFS)
     * 求海洋离周边最近的陆地距离
     * 由于广度优先算法的性质，第一个找到的陆地即是最近的陆地距离
     */
    public static int maxDistance_bfs(int[][] grid) {
        int distance = -1;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j] == 0){
                    int tmp = explore_bfs(grid, i, j);
                    distance = tmp>distance? tmp : distance;
                }
            }
        }
        return distance;
    }

    public static int explore_bfs(int[][] grid, int i, int j){
        int distance = -1;
        Queue<Pair> queue = new ArrayDeque<>(); // 采用广度优先算法
        Set<Pair> record = new HashSet<>();
        queue.offer(new Pair<>(i, j));
        record.add(new Pair<>(i, j));
        while(!queue.isEmpty()){ // 操作栈不为空
            Pair<Integer, Integer> pos = queue.poll();
            if(grid[pos.getKey()][pos.getValue()] == 1){
                // 根据广度优先算法的性质，找到的第一个陆地即是最短距离
                return Math.abs(i - pos.getKey()) + Math.abs(j - pos.getValue());
            }
            // 向上探索
            if(pos.getKey() > 0){
                addToStack(queue, record, new Pair<>(pos.getKey()-1, pos.getValue()));
            }
            // 向下探索
            if(pos.getKey() < grid.length-1){
                addToStack(queue, record, new Pair<>(pos.getKey()+1, pos.getValue()));
            }
            // 向左探索
            if(pos.getValue() > 0){
                addToStack(queue, record, new Pair<>(pos.getKey(), pos.getValue()-1));
            }
            // 向右探索
            if(pos.getValue() < grid.length-1){
                addToStack(queue, record, new Pair<>(pos.getKey(), pos.getValue()+1));
            }
        }
        return distance;
    }

    public static void addToStack(Queue<Pair> queue, Set<Pair> record, Pair<Integer, Integer> pos){
        if(record.contains(pos)){
            return;
        }
        record.add(pos);
        queue.offer(pos);
    }

    /**
     * 采用深度优先算法，用Stack替代递归方法 (DFS)
     * 求海洋离周边最近的陆地距离
     *
     * 结果正确，超出时间限制
     */
    public static int maxDistance_dfs(int[][] grid) {
        int distance = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int tmp = explore(grid, i, j);
                    distance = tmp > distance ? tmp : distance;
                }
            }
        }
        return distance;
    }

    public static int explore(int[][] grid, int i, int j) {
        int distance = -1;
        Stack<Pair> stack = new Stack<>(); // 采用深度优先算法
        Set<Pair> record = new HashSet<>();
        stack.push(new Pair<>(i, j));
        record.add(new Pair<>(i, j));
        while (!stack.empty()) { // 操作栈不为空
            Pair<Integer, Integer> pos = stack.pop();
            if (grid[pos.getKey()][pos.getValue()] == 1) {
                int tmp = Math.abs(i - pos.getKey()) + Math.abs(j - pos.getValue());
                if (distance == -1) {
                    distance = tmp;
                } else {
                    distance = distance < tmp ? distance : tmp;
                }
            }
            // 向上探索
            if (pos.getKey() > 0) {
                addToStack(stack, record, new Pair<>(pos.getKey() - 1, pos.getValue()));
            }
            // 向下探索
            if (pos.getKey() < grid.length - 1) {
                addToStack(stack, record, new Pair<>(pos.getKey() + 1, pos.getValue()));
            }
            // 向左探索
            if (pos.getValue() > 0) {
                addToStack(stack, record, new Pair<>(pos.getKey(), pos.getValue() - 1));
            }
            // 向右探索
            if (pos.getValue() < grid.length - 1) {
                addToStack(stack, record, new Pair<>(pos.getKey(), pos.getValue() + 1));
            }
        }
        return distance;
    }

    public static void addToStack(Stack<Pair> stack, Set<Pair> record, Pair<Integer, Integer> pos) {
        if (record.contains(pos)) {
            return;
        }
        record.add(pos);
        stack.push(pos);
    }
}
