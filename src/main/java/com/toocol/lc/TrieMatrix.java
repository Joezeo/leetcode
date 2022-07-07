package com.toocol.lc;

import java.util.Arrays;

/**
 * 前缀树/字典树
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <br>
 * 此类使用二维数组实现
 * <br>
 * 使用TrieNode:
 * @see com.toocol.lc.Trie
 *
 * @author ZhaoZhe (joezane.cn@gmail.com)
 * @date 2022/7/7 10:34
 */
public class TrieMatrix {
    private static final int LINES = (int)1e6 + 9;

    private static final int[][] trie = new int[LINES][26];
    private static final int[] count = new int[LINES];
    private static int index = 0;

    public TrieMatrix() {
        for (int[] ts : trie) {
            Arrays.fill(ts, 0);
        }
        Arrays.fill(count, 0);
        index = 0;
    }

    public void insert(String str) {
        int p = 0;
        for (char ch : str.toCharArray()) {
            int u = ch - 'a';
            if (trie[p][u] == 0) {
                trie[p][u] = ++index;
            }
            p = trie[p][u];
        }
        count[p]++;
    }

    public boolean search(String str) {
        int p = 0;
        for (char ch : str.toCharArray()) {
            int u = ch - 'a';
            if (trie[p][u] == 0) {
                return false;
            }
            p = trie[p][u];
        }
        return count[p] != 0;
    }

    public boolean startsWith(String str) {
        int p = 0;
        for (char ch : str.toCharArray()) {
            int u = ch - 'a';
            if (trie[p][u] == 0) {
                return false;
            }
            p = trie[p][u];
        }
        return true;
    }

    public static void main(String[] args) {
        TrieMatrix trieMatrix = new TrieMatrix();
        trieMatrix.insert("apple");
        trieMatrix.insert("ape");

        System.out.println(trieMatrix.search("ap"));
        System.out.println(trieMatrix.startsWith("ap"));

        System.out.println(trieMatrix.search("apple"));
        System.out.println(trieMatrix.startsWith("apples"));
    }
}
