package org.joezeo.leetcode.algorithm;

import java.util.List;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/11/13 23:07
 */
public class Paritylinkedlist {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 2; i <=5 ; i++) {
            ListNode node = new ListNode(i);
            cur.next = node;
            cur = cur.next;
        }

        ListNode resultNode = oddEvenList(head);
        while (resultNode != null) {
            System.out.print(":" + resultNode.val);
            resultNode = resultNode.next;
        }
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode offsetHead = new ListNode();
        ListNode cur = head;
        ListNode join = null;
        ListNode offset = offsetHead;
        ListNode lastNode = null;
        ListNode headJoin = null;
        int counter = 0;
        while(cur != null){
            if(counter % 2 != 0) {
                if (offsetHead.next == null){
                    offsetHead.next = cur;
                }
                offset.next = cur;
                offset = cur;

                lastNode.next = cur.next;
            } else {
                join = cur;
                if (lastNode != null) {
                    lastNode.next = cur.next;
                }
            }
            if (counter == 2) {
                headJoin = cur;
            }
            lastNode = cur;
            cur = cur.next;
            counter++;
        }
        join.next = offsetHead.next;
        head.next = headJoin;
        return head;
    }
}
