package org.joezeo.leetcode.algorithm;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/8/27 14:59
 */
public class ReverseInteger {
    private static final char[] numChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public int reverse(int x) {
        long res = 0;
        char[] chars = Integer.valueOf(x).toString().toCharArray();
        for (int left = 0, right = chars.length - 1; left < right;) {
            if (chars[left] == '-') {
                left++;
                continue;
            }
            int tmp = chars[right] - 48;
            chars[right] = chars[left];
            chars[left] = numChar[tmp];
            left++;
            right--;
        }
        res = Integer.parseInt(new String(chars));
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            res = 0;
        }
        return (int) res;
    }
}
