package org.joezeo.leetcode.algorithm.before;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * <p>
 * 重复出现的子串要计算它们出现的次数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * <p>
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * <p>
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 * <p>
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 * <p>
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-binary-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/8/10 10:41
 */
public class BinarySubString {
    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("00110011"));
    }

    /**
     * 按照字符连续段进行分组
     */
    public static int countBinarySubstrings(String s) {
        char[] chars = s.toCharArray();
        List<Integer> counters = new ArrayList<>();
        int cnt = 0;
        int ptr = 0, len = s.length();
        while (ptr < len) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < len && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            counters.add(count);
        }
        int res = 0;
        for (int i = 0; i < counters.size() - 1; i++) {
            res += Math.min(counters.get(i), counters.get(i + 1));
        }
        return res;
    }
}
