package org.joezeo.leetcode.algorithm.Y2020M11.b15t22;

import java.util.Arrays;

/**
 * 给你链表的头结点head，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * 你可以在O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/11/21 15:44
 */
public class SortList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 首先从题意，对于链表的排序，我们可以用插入排序(org.joezeo.leetcode.algorithm.Y2020M11.b15t22.InsertionSortList)
    // 但是进阶要求时间复杂度位o(nlogn)，所以我们考虑使用归并排序
    // 首先我们来复习一下归并排序
    public static void main(String[] args) {
        int[] arr = {78, 12, 1, 999, 32, 8, 666, 3, 8};
        MergeSort.sort(arr, 0, arr.length - 1);
        System.out.printf(Arrays.toString(arr));
    }

    // 用归并来处理链表，最大的问题在于如何分治，如何找到链表的中间结点？？ 当然也可以转换成数组来做归并排序，但这样做好吗，这样做不好
    /*
    首先求得链表的长度 length，然后将链表拆分成子链表进行合并。

    buttom-to-up
    具体做法如下。
    1.用 subLength 表示每次需要排序的子链表的长度，初始时 subLength=1。
    2.每次将链表拆分成若干个长度为 subLength 的子链表（最后一个子链表的长度可以小于 subLength），按照每两个子链表一组进行合并，
      合并后即可得到若干个长度为 subLength×2 的有序子链表（最后一个子链表的长度可以小于 subLength×2）。
    3.将 subLength 的值加倍，重复第 2 步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或等于 length，整个链表排序完毕。
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = 0;
        ListNode cur = head;
        while(cur != null) {
            length++;
            cur = cur.next;
        }
        int subLength = 1;
        ListNode dummyHead = new ListNode(0, head);
        while (subLength < length) {
            // 问题来了。怎么把链表拆成若干个长度为subLength的子链表
            // 答：cut断链，怎么搞？不会
            // cut断链抄袭了官方题解：其实就是一次遍历，分别构建长度为subLength的子链
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
            subLength*=2;
        }
        return dummyHead.next;
    }

    // cut断链不会，先来把双路归并写了
    public ListNode merge(ListNode head_1, ListNode head_2) {
        ListNode dummyHead = new ListNode(0);
        ListNode node = dummyHead;
        ListNode lnode = head_1;
        ListNode rnode = head_2;

        while(lnode != null && rnode != null) {
            if (lnode.val < rnode.val) {
                node.next = lnode;
                lnode = lnode.next;
            } else {
                node.next = rnode;
                rnode = rnode.next;
            }
            node = node.next;
        }

        while (lnode != null) {
            node.next = lnode;
            lnode = lnode.next;
            node = node.next;
        }
        while (rnode != null) {
            node.next = rnode;
            rnode = rnode.next;
            node = node.next;
        }
        return dummyHead.next;
    }
}
