package org.joezeo.leetcode.algorithm.Y2020M11.b15t22;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 *
 * 示例 1：
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 *
 * 示例 2：
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 *
 * 示例 3：
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 *  
 *
 * 提示：
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/11/17 22:04
 */
public class AllCellsDistOrder {

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2};
        int[] arr2 = new int[]{1,2};
        System.out.println(arr1 == arr2); //false
        System.out.println(arr1.equals(arr2)); //false
        System.out.println(arr1.hashCode()); //false
        System.out.println(arr2.hashCode()); //false
        allCellsDistOrder(1,2,0,0);
    }

    // 方法一：广度优先算法
    // 这道题还是很简单，就是用到了广度优先遍历
    // 但是很不幸，直接超出了时间限制
    // 当然，还可以存储下所有点，然后按曼哈顿距离进行排序，这样的时间复杂度为O(RClog(RC))，也很高
    public static int[][] allCellsDistOrder_bfs(int R, int C, int r0, int c0) {
        // 首先我们需要一个容器来存放已经访问过的单元格
        List<Pair<Integer, Integer>> rec = new ArrayList<>();
        // 再定义一个展开广度优先遍历的队列
        Queue<Pair<Integer, Integer>> queue = new LinkedBlockingDeque<>();
        // 以r0,c0为中心开始遍历
        Pair<Integer, Integer> start = new Pair<>(r0, c0);
        rec.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> cell = queue.poll();
            int r = cell.getKey();
            int c = cell.getValue();
            if (r + 1 < R) {
                addIfNotExist(r + 1, c, rec, queue);
            }
            if (r - 1 >= 0) {
                addIfNotExist(r - 1, c, rec, queue);
            }
            if (c + 1 < C) {
                addIfNotExist(r, c + 1, rec, queue);
            }
            if (c - 1 >= 0) {
                addIfNotExist(r, c - 1, rec, queue);
            }
        }
        int[][] result = new int[rec.size()][];
        for (int i = 0; i < rec.size(); i++) {
            Pair<Integer, Integer> pair = rec.get(i);
            result[i] = new int[2];
            result[i][0] = pair.getKey();
            result[i][1] = pair.getValue();
        }
        return result;
    }
    public static void addIfNotExist(int r, int c, List<Pair<Integer, Integer>> rec, Queue<Pair<Integer, Integer>> queue) {
        Pair<Integer, Integer> cell = new Pair<>(r, c);
        if (rec.contains(cell)) {
            return;
        }
        rec.add(cell);
        queue.offer(cell);
    }

    // 方法二：桶排序
    // 这里我投机取巧用了TreeMap来自动排序桶......
    // 官方的做法也和我后来想的一样，求出最大的曼哈顿距离来建立桶，用空间换取时间
    public static int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        Map<Integer, List<int[]>> bucket = new TreeMap<>();
        int bucketCount = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int distance = Math.abs(r0 - r) + Math.abs(c0 - c);
                List<int[]> list = bucket.get(distance);
                if (list == null) {
                    list = new ArrayList<>();
                    bucket.put(distance, list);
                }
                list.add(new int[]{r, c});
                bucketCount ++;
            }
        }
        int[][] result = new int[bucketCount][];
        int row = 0;
        for (Map.Entry<Integer, List<int[]>> entry : bucket.entrySet()) {
            List<int[]> value = entry.getValue();
            for (int[] ints : value) {
                result[row] = new int[2];
                result[row][0] = ints[0];
                result[row][1] = ints[1];
                row++;
            }
        }
        return result;
    }
}
