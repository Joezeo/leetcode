package com.joezeo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。

 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。

 注意：每次拼写时，chars 中的每个字母都只能用一次。

 返回词汇表 words 中你掌握的所有单词的 长度之和。
 */
public class CountCharacters {
    public static void main(String[] args) {
        String []words = {"cat","bt","hat","tree"};
        String chars = "atach";
        countCharactersVolence(words, chars);
    }

    /**
     * 暴力求解
     */
    public static int countCharactersVolence(String[] words, String chars) {
        int count = 0;
        char[] baseArr = new char[chars.length()];
        chars.getChars(0, chars.length(), baseArr, 0);
        List<Character> list = new ArrayList<>();
        for(char ch : baseArr){
            list.add((Character)ch);
        }

        start:for(String word : words){
            char[] charArr = new char[word.length()];
            word.getChars(0, word.length(), charArr, 0);
            List<Character> temp = new ArrayList(list);

            for(char ch:charArr){
                if(temp.contains((Character)ch)){
                    temp.remove((Character)ch);
                } else {
                    continue start;
                }
            }
            count+=word.length();
        }

        return count;
    }
}
