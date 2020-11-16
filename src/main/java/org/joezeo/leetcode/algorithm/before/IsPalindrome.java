package org.joezeo.leetcode.algorithm.before;

import java.util.Stack;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/10/23 11:32
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class IsPalindrome {
    // 使用栈 未知链表长度
    // 暴力解：求出链表长度，前一半压栈，后一半出栈判断
    public boolean isPalindrome(ListNode head) {
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        Stack<ListNode> stack = new Stack<>();
        node = head;
        for (int idx = 0; idx < length/2; idx++) {
            stack.push(node);
            node = node.next;
        }
        if (length % 2 != 0) { // 如果长度为奇数，中间的值不压栈，且不进行判断
            node = node.next;
        }
        while (node != null) {
            ListNode pop = stack.pop();
            if (pop.val != node.val) {
                return false;
            }
            node = node.next;
        }
        return true;
    }
}

