package org.joezeo.leetcode.algorithm.Y2020M11.b23t30;

import java.util.*;

/**
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，
 * 因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，
 * 若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足 xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。
 * 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 * <p>
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * <p>
 * 示例 2：
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * <p>
 * 示例 4：
 * 输入：points = [[1,2]]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 * <p>
 * 提示：
 * 0 <= points.length <= 104
 * points[i].length == 2
 * -231 <= xstart < xend <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/11/23 21:28
 */
public class FindMinArrowShots {
    /**
     * 首先需要对原数组进行升序处理，进行遍历操作
     * 1. 判断两个数列是否相交：
     * 如果相交，两个数列取并集，继续进行处理，遍历完后 result+1
     * 不相交，则添加至队列中，result+1，继续向前处理
     */
    // 这是自己想出的暴力解法
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        Set<int[]> transed = new HashSet<>();
        Queue<int[]> notTrans = new LinkedList<>();

        // 必须先要排一次升序
        Arrays.sort(points, (point1, point2) -> {
            if (point1[1] > point2[1]) {
                return 1;
            } else if (point1[1] < point2[1]) {
                return -1;
            } else {
                return 0;
            }
        });

        notTrans.add(points[0]);
        int result = 0;

        while (!notTrans.isEmpty()) {
            int[] target = notTrans.poll();

            for (int j = 0; j < points.length; j++) {
                if (transed.contains(points[j])) {
                    continue;
                }
                int[] cursor = points[j];
                if (cursor[0] > target[1] || target[0] > cursor[1]) { // 与target不相交
                    notTrans.add(cursor);
                    break;
                } else {
                    transed.add(target);
                    transed.add(cursor);
                    target = new int[]{Math.min(target[0], cursor[0]), Math.min(target[1], cursor[1])};
                }
            }
            result++;
        }
        return result;
    }

    // 其实排完序后就没有必要套两层循环了
    public int findMinArrowShots_1(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        // 必须先要排一次升序
        Arrays.sort(points, (point1, point2) -> {
            if (point1[1] > point2[1]) {
                return 1;
            } else if (point1[1] < point2[1]) {
                return -1;
            } else {
                return 0;
            }
        });

        int result = 0;
        int[] target = points[0];
        for (int j = 1; j < points.length; j++) {
            int[] cursor = points[j];
            if (cursor[0] > target[1] || target[0] > cursor[1]) { // 与target不相交
                result++;
                target=points[j];
            } else {
                target = new int[]{Math.max(target[0], cursor[0]), Math.min(target[1], cursor[1])};
            }
        }
        return ++result;
    }
}
