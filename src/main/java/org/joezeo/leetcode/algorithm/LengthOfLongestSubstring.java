package org.joezeo.leetcode.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));
    }


    /**
     * 使用滑动窗口，设定左右两个指针，满足条件左指针右移，不满足条件左指针右移
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int length = 0;

        int left = 0, right = 0;
        List<Character> list = new LinkedList<>();
        while (right != s.length()) {
            if (list.contains(arr[right])) {
                list.remove(0);
                continue;
            }
            list.add(arr[right]);
            right++;
            length = Math.max(list.size(), length);
        }

        return length;
    }

    /**
     * 时间复杂度O（n2）最后会超出时间限制
     */
    public static int lengthOfLongestSubstring_n2(String s) {
        char[] arr = s.toCharArray();
        int length = 0;

        List<Character> list = null;
        for (int start = 0; start < s.length(); start++) {
            for (int end = start; end < s.length(); end++) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                if (list.contains(arr[end])) {
                    length = Math.max(length, list.size());
                    list = null;
                } else {
                    list.add(arr[end]);
                    length = Math.max(length, list.size());
                }
                if (end == s.length() - 1) {
                    list = null;
                }
            }
        }

        return length;
    }
}
