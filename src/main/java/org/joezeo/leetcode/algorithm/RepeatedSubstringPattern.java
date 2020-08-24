package org.joezeo.leetcode.algorithm;

/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abab"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 * <p>
 * 输入: "aba"
 * <p>
 * 输出: False
 * 示例 3:
 * <p>
 * 输入: "abcabcabcabc"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/8/24 14:38
 */
public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        String s = "HelloWrold";
        System.out.println(s.substring(0, 1));
    }

    /**
     * 双重循环暴力解题：超时
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        for (int count = 0; count < s.toCharArray().length; count++) {
            String sub = s.substring(0, count + 1);
            if (s.toCharArray().length % (count + 1) == 0) {
                for (int index = count + 1; index < s.toCharArray().length - count; index += count + 1) {
                    if (s.toCharArray().length % (count + 1) != 0) {
                        break;
                    }
                    String _sub = s.substring(index, index + count + 1);
                    if (!sub.equals(_sub)) {
                        break;
                    }
                    if (index + count + 1 >= s.toCharArray().length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern_official(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }
}

