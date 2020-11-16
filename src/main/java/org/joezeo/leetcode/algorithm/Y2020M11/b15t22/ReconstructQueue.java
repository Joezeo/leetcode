package org.joezeo.leetcode.algorithm.Y2020M11.b15t22;

import java.util.*;

/**
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/11/16 22:03
 */
public class ReconstructQueue {
    public static void main(String[] args) {
        int[][] people = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        reconstructQueue(people);
    }
    // 在使用comparable等接口时，如果想要升序排序就用o1-o2，如果想要降序排序就用o2-o1

    // 套路：遇到这样的数组排序，往往先正向排序再逆向排序能够往往能够解决问题
    // 在第一次降序排序后，按身高由高到低开始插入，所以前面插入的人的身高比当前人的身高要高(重点)，
    // 假如person[1] = 1，代表着前面应该有一个人当前人高，也就代表着这个人在插入列表的下标index应当是1
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> {
            if (p1[0] == p2[0]) {
                return p1[1] - p2[1];
            } else {
                return p2[0] - p1[0];
            }
        });
        List<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            if (list.isEmpty() || person[1] > list.size()) {
                list.add(person);
            } else {
                int index = person[1];
                list.add(index, person);
            }
        }
        for (int[] person : list.toArray(new int[list.size()][])) {
            System.out.println("[" + person[0] + ", " + person[1] + "]");
        }
        return list.toArray(new int[list.size()][]);
    }
}
