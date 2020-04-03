package org.joezeo.leetcode.algorithm;

import java.util.*;

/**
 *请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 *
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *
 * 提示：
 *
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Atoi {
    public static void main(String[] args) {
        String str = "10522545459";
        System.out.println(myAtoi(str));
    }

    /**
     * 借鉴官方题解的思路，用Java实现自动机，使用一个二维int数组来表示自动机表。
     */
    // 第一维数组：0:started 1:singed 2:in_number 3:end
    // 第二维数组：各个状态下遇到' ','+/-','0-9','其他字符'的状态切换
    public static int[][] statusTable = {
            {0, 1, 2, 3},
            {3, 3, 2, 3},
            {3, 3, 2, 3}
    };
    public static int status = 0; // 当前状态
    public static int singed = 1; // 符号位
    public static int ans = 0;

    public static int getCol(char ch) { // 获取列数
        if (ch == ' ') {
            return 0;
        } else if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch >= '0' && ch <= '9') {
            return 2;
        } else {
            return 3;
        }
    }

    public static int myAtoi(String str) {
        for (char ch : str.toCharArray()) {
            int col = getCol(ch);
            status = statusTable[status][col];
            if (status == 3) {
                break;
            } else if (status == 1) {
                singed = ch == '-' ? -1 : 1;
            } else if (status == 2) {
                boolean isOverIntegerMaxValue = ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && ch - '0' > Integer.MAX_VALUE % 10);
                boolean isOverIntegerMinValue = ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && ch - '0' > ((long) Integer.MAX_VALUE + 1) % 10);
                if (isOverIntegerMaxValue && singed == 1) {
                    return Integer.MAX_VALUE;
                }
                if (isOverIntegerMinValue && singed == -1) {
                    return Integer.MIN_VALUE;
                }
                ans = (ch - '0') + ans * 10;
            }
        }
        return ans * singed;
    }


    /**
     * 最复杂的即是判断各种边界条件
     */
    public static int myAtoi_iselse(String str) {
        int len = str.length();

        // 去除前导空格
        int index = 0;
        while (index < len) {
            if (str.charAt(index) != ' ') {
                break;
            }
            index++;
        }

        if (index == len) {
            return 0;
        }

        // 第 1 个字符如果是符号，判断合法性，并记录正负
        int sign = 1;
        char firstChar = str.charAt(index);
        if (firstChar == '+') {
            index++;
            sign = 1;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        // 不能使用 long 类型，这是题目说的
        int res = 0;
        while (index < len) {
            char currChar = str.charAt(index);
            // 判断合法性
            if (currChar > '9' || currChar < '0') {
                break;
            }

            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            // 每一步都把符号位乘进去
            res = res * 10 + sign * (currChar - '0');
            index++;
        }

        return res;
    }


    public static int myAtoi_hard(String str) {
        str = str.trim();
        char flag = '+';
        char firstNum;
        char[] nums = str.toCharArray();
        char[] legal = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Stack<Character> res = new Stack<>();
        List<Character> legalList = new ArrayList<>();
        for (char ch : legal) {
            legalList.add(ch);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != ' ') {
                if (i == 0 && (nums[i] == '+' || nums[i] == '-')) {
                    if (nums[i] == '-') {
                        flag = '-';
                    }
                    res.push(nums[i]);
                    continue;
                }


                if (legalList.contains(nums[i])) {
                    res.push(nums[i]);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        long mul = 1;
        long result = 0;

        while (!res.isEmpty()) {
            if (res.size() == 1) {
                char ch = res.pop();
                if (ch == '-') {
                    result = 0 - result;
                } else if (ch == '+') {
                    break;
                } else {
                    long i = Long.valueOf("" + ch);
                    if (i == 0) {
                        i = 1;
                    }
                    if (result + i * mul > Integer.MAX_VALUE) {
                        if (flag == '-') {
                            return Integer.MIN_VALUE;
                        } else {
                            return Integer.MAX_VALUE;
                        }

                    }
                    result = result + i * mul;
                    mul *= 10;
                }
            } else {
                char ch = res.pop();
                long i = Long.valueOf("" + ch);
                if (i == 0) {
                    i = 1;
                }
                if (result + i * mul > Integer.MAX_VALUE) {
                    if (flag == '-') {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }

                }

                result = result + i * mul;
                mul *= 10;
            }
        }

        return (int) result;
    }
}
