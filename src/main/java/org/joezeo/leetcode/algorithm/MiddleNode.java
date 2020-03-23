package org.joezeo.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 */
public class MiddleNode {
    public static void main(String[] args) {

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode middleNode(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode node = head;
        int length = 0;
        while (node != null) {
            map.put(length, node);
            length++;
            node = node.next;
        }
        int index = length / 2;
        return map.get(index);
    }
}
