package org.joezeo.leetcode.algorithm;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/4/6 15:49
 */
public class MinDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("ros", "roesf"));
    }

    public static int minDistance(String word1, String word2) {
        // 参考官方题解，使用动态规划解题
        // 动态规划数组opt[i][j]表示word1的前i个字母和word2前j个数组互相转换的最小编辑距离

        if (word1.length() * word2.length() == 0) {
            return Math.max(word1.length(), word2.length());
        }
        int[][] opt = new int[word1.length() + 1][word2.length() + 1];

        // 初始化边界
        for (int i = 0; i < word1.length() + 1; i++) {
            opt[i][0] = i;
        }
        for (int i = 0; i < word2.length() + 1; i++) {
            opt[0][i] = i;
        }

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                int word1Add = opt[i - 1][j] + 1;
                int word2Add = opt[i][j - 1] + 1;
                int twoModify = opt[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    twoModify += 1;
                }
                opt[i][j] = Math.min(Math.min(word1Add, word2Add), twoModify);
            }
        }

        return opt[word1.length()][word2.length()];
    }
}

