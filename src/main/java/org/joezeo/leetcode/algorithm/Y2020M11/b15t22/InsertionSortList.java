package org.joezeo.leetcode.algorithm.Y2020M11.b15t22;

/**
 * 对链表进行插入排序。
 * <p>
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * <p>
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insertion-sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/11/20 22:47
 */
public class InsertionSortList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 链表的前半部分是已经排好序的，且是升序排序，那么我们从左至右找第一个比当前节点大的位置前面插进去就完事
    // 暴力解法...非常耗时
    public ListNode insertionSortList(ListNode head) {
        ListNode curent = head;
        ListNode curentLast = null;
        ListNode cursor;
        ListNode cursorLast;
        while (curent != null) {
            if (curent.equals(head)) {
                curentLast = curent;
                curent = curent.next;
                continue;
            }
            cursor = head;
            cursorLast = null;
            ListNode tmpCur = curent;
            while (!cursor.equals(tmpCur)) {
                if (cursor.val > curent.val) {
                    if (cursorLast == null) { // 插在最前面
                        head = curent;
                        curentLast.next = curent.next;
                        curent.next = cursor;
                    } else { // 插在cursor前面
                        curentLast.next = curent.next;
                        cursorLast.next = curent;
                        curent.next = cursor;
                    }
                    tmpCur = curentLast;
                    break;
                }
                cursorLast = cursor;
                cursor = cursor.next;
            }
            // 没有发现比curent节点大的值,或者插入完毕，继续前行;
            curentLast = tmpCur;
            curent = tmpCur.next;
        }
        return head;
    }
}
