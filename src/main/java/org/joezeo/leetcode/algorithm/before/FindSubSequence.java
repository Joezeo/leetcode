package org.joezeo.leetcode.algorithm.before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * <p>
 * 示例:
 * <p>
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 * <p>
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/8/25 11:24
 */
public class FindSubSequence {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int cursor = i + 1;
            while (cursor != nums.length - 1) {
                List<Integer> collect = new ArrayList<>();
                collect.add(i);
                while (cursor != nums.length - 1) {
                    collect.add(nums[cursor++]);
                    boolean check = check(result, collect);
                    if (!check) {
                        result.add(collect);
                        cursor = i + 1;
                    }
                }
            }
        }
        return result;
    }

    public boolean check(List<List<Integer>> result, List<Integer> collect) {
        for (List<Integer> integers : result) {
            if (integers.size() != collect.size()) {
                continue;
            }

            for (int i = 0; i < collect.size(); i++) {
                if (!integers.get(i).equals(collect.get(i))) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
}
