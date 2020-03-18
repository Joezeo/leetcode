package org.joezeo.leetcode;

public class LinkListAddTwoNum {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean mark = false; // 进位标记

        ListNode start = null;
        ListNode node = null;

        while(l1!=null || l2!=null){
            int num1 = l1==null?0:l1.val;
            int num2 = l2==null?0:l2.val;
            int sum = mark?num1+num2+1:num1+num2;

            if(sum >= 10){
                mark = true;
                sum = sum%10;
            } else {
                mark = false;
            }

            if(node == null){ // 第一次进入循环
                node = new ListNode(sum);
                start = node;
            } else {
                ListNode tmp = new ListNode(sum);
                node.next = tmp;
                node = tmp;
            }

            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        if(mark){ // 最后进一位
            node.next = new ListNode(1);
        }
        return start;
    }

    /**
     * 最初的写法，使用进位标记模拟运算
     * 代码很冗余，还可以精简
     */
    public ListNode addTwoNumbers_oringin(ListNode l1, ListNode l2) {
        boolean mark = false; // 进位标记

        ListNode start = null;
        ListNode node = null;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if (mark) {
                sum += 1;
            }
            if (sum >= 10) {
                mark = true;
                sum = sum % 10;
            } else {
                mark = false;
            }

            if (node == null) {
                node = new ListNode(sum);
                start = node;
            } else {
                ListNode tmp = new ListNode(sum);
                node.next = tmp;
                node = tmp;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int num = l1.val;
            if (mark) {
                num += 1;
                if (num >= 10) {
                    num %= 10;
                    mark = true;
                } else {
                    mark = false;
                }
            }

            ListNode tmp = new ListNode(num);
            node.next = tmp;
            node = tmp;
            l1 = l1.next;
        }

        while (l2 != null) {
            int num = l2.val;
            if (mark) {
                num += 1;
                if (num >= 10) {
                    num %= 10;
                    mark = true;
                } else {
                    mark = false;
                }
            }

            ListNode tmp = new ListNode(num);
            node.next = tmp;
            node = tmp;
            l2 = l2.next;
        }

        if (mark && l1 == null && l2 == null) {
            ListNode tmp = new ListNode(1);
            node.next = tmp;
            node = node.next;
        }

        return start;
    }
}
