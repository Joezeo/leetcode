package org.joezeo.leetcode.algorithm.before;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 */
public class LongestPalindrome {
    public static void main(String[] args) {
    }

    /**
     *  组成回文串的字符左右两侧相同，如 abb|bba
     *  所以可以组成回文串的字符出现的个数是偶数个，一个回文串最多出现一个奇数个的字符如abbcbba
     *
     *  这里的解法是使用map来记录字符出现的个数
     *  当然使用数组也行，效率也会更高
     */
    public static int longestPalindrome(String s) {
        int length = 0;
        char[] arr = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for(char ch : arr){
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch)+1);
            } else {
                map.put(ch, 1);
            }
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            int tmp = entry.getValue()%2==0?entry.getValue():entry.getValue()-1;
            length += tmp;
            map.put(entry.getKey(),entry.getValue() - tmp);
        }
        if(map.containsValue(1)){
            length++;
        }

        return length;
    }
}
