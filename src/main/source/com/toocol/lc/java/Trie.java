package com.toocol.lc.java;

/**
 * 前缀树/字典树
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <br>
 * 此类使用TrieNode实现
 * <br>
 * 使用二维数组：
 * @see TrieMatrix
 *
 * @author ZhaoZhe (joezane.cn@gmail.com)
 * @date 2022/7/7 10:50
 */
public class Trie {
    static class TrieNode {
        public boolean end;
        public TrieNode[] tns = new TrieNode[26];
    }
    final TrieNode root = new TrieNode();

    public void insert(String str) {
        TrieNode p = root;
        for (char ch : str.toCharArray()) {
            int u = ch - 'a';
            if (p.tns[u] == null) {
                p.tns[u] = new TrieNode();
            }
            p = p.tns[u];
        }
        p.end = true;
    }

    public boolean search(String str) {
        TrieNode p = root;
        for (char ch : str.toCharArray()) {
            int u = ch - 'a';
            if (p.tns[u] == null) {
                return false;
            }
            p = p.tns[u];
        }
        return p.end;
    }

    public boolean startsWith(String str) {
        TrieNode p = root;
        for (char ch : str.toCharArray()) {
            int u = ch - 'a';
            if (p.tns[u] == null) {
                return false;
            }
            p = p.tns[u];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("ape");

        System.out.println(trie.search("ap"));
        System.out.println(trie.startsWith("ap"));

        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("apples"));
    }
}
