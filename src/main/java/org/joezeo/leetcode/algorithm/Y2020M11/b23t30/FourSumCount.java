package org.joezeo.leetcode.algorithm.Y2020M11.b23t30;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 *
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/11/27 10:29
 */
public class FourSumCount {
    /**
        分组 + 哈希表
        分成两队数组,两两相加,记录两队数组相加结果
        修改：使用哈希表进行存储相加结果
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        Map<Integer, Integer> sum = new HashMap<>();

        for (int i : A) {
            for (int j : B) {
                if (!sum.containsKey(i+j)) {
                    sum.put(i+j, 0);
                }
                sum.put(i+j, sum.get(i+j) + 1);
            }
        }
        for (int i : C) {
            for (int j : D) {
                if (sum.containsKey((i+j) * -1)) {
                    res += sum.get((i+j) * -1);
                }
            }
        }
        return res;
    }
}
