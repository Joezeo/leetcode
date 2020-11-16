package org.joezeo.leetcode.algorithm.before;

import java.util.Arrays;

/**
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * <p>
 * 示例：
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/short-encoding-of-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumLengthEncoding {
    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        System.out.println(minimumLengthEncoding(words));
    }

    /**
     * 使用字典树
     * 因为相同后缀的单词需要合并，所以将所有单词反转后存入字典树
     */
    private static class Node {
        char val;
        Node[] children = new Node[26];
        boolean isStore = false; // 该单词是否已经存储过了

        public Node() {
        }

        public Node(char val) {
            this.val = val;
        }
    }

    private static Node root = new Node();

    public static int minimumLengthEncoding(String[] words) {
        // 首先将单词数组按照单词长度从长到短进行排序
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        int length = 0;
        // 将所有单词存入字典树
        for (String word : words) {
            length += insert(word);
        }
        return length;
    }

    /**
     * 以后序的方式将单词插入字典树
     * 返回插入的单词长度+1（#）
     */
    private static int insert(String word) {
        if (isSuffix(word)) {
            return 0;
        }
        Node cur = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char ch = word.charAt(i);
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new Node(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        return word.length() + 1;
    }

    /**
     * 判断当前单词是否是某个单词的后缀(包括相同的单词)
     */
    private static boolean isSuffix(String word) {
        Node cur = root;
        boolean isNew = false;
        for (int i = word.length() - 1; i >= 0; i--) {
            char ch = word.charAt(i);
            if(cur.children[ch - 'a'] == null){
                return false;
            }
            cur = cur.children[ch - 'a'];
        }
        for(Node node : cur.children){
            if(node != null){
                return true;
            }
        }
        return cur.isStore;
    }
}
