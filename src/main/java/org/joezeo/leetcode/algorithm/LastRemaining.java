package org.joezeo.leetcode.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * 示例 1：
 * 输入: n = 5, m = 3
 * 输出: 3
 * <p>
 * 示例 2：
 * 输入: n = 10, m = 17
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/3/30 12:20
 */
public class LastRemaining {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(lastRemaining_linkedlist(70866, 116922));
        long end = System.currentTimeMillis();
        System.out.println("链表法用时:" + (end - start));

        start = System.currentTimeMillis();
        System.out.println(lastRemaining_Joseph(70866, 116922));
        end = System.currentTimeMillis();
        System.out.println("公式法用时:" + (end - start));
    }

    public static int lastRemaining(int n, int m) {
        // return lastRemaining_linkedlist(n, m);
        // return lastRemaining_arraylist(n, m);
        return lastRemaining_Joseph(n, m);
    }

    /**
     * 这道题是经典的约瑟夫环问题
     * 方法一：模拟法，运用链表模拟整个游戏过程
     * 要模拟整个游戏过程，时间复杂度高达O(nm)，当n，m非常大(例如上百万，上千万)的时候，几乎是没有办法在短时间内出结果的。
     */
    public static int lastRemaining_linkedlist(int n, int m) {
        class Node {
            int num; // 编号
            Node next;

            public Node(int num) {
                this.num = num;
            }
        }
        Node head = new Node(0);
        Node cur = head;
        for (int i = 1; i < n; i++) {
            cur.next = new Node(i);
            cur = cur.next;
        }
        cur.next = head; // 形成闭环
        Node pre = cur;
        cur = head;
        while (n > 1) { // 当链表中只剩最后一个节点时结束
            for (int i = 1; i <= m; i++) {
                if (i == m) { // 移除cur节点
                    pre.next = cur.next;
                    cur = cur.next;
                    n--;
                } else {
                    pre = cur;
                    cur = cur.next;
                }
            }
        }
        return cur.num;
    }

    /**
     * 方法二：
     * 使用ArrayList代替链表
     * 假设当前的删除位置为idx，那么下个删除位置为idx+m-1，由于数到末尾会从头开始数
     * 所以下个删除位置为(idx+m-1)%n
     * <p>
     * ArrayList删除一个元素的时间复杂度为O(n)，故整个算法的时间复杂度为O(n2)，勉强能过LeetCode时间限制
     */
    public static int lastRemaining_arraylist(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    /**
     * 方法三：
     * 使用约瑟夫环数学公式
     * 时间复杂度为O(n);
     */
    public static int lastRemaining_Joseph(int n, int m) {
        // 我们容易知道当队列的长度为1时，胜利者的下标为0；
        int ans = 0;
        // 由此倒推，当长度为2时，胜利者的下标为 (0 + m) % n;
        // 故有此可以推导出长度为n时，胜利者的下标
        for (int i = 2; i <= n; i++) { // 通过迭代求解
            ans = (ans + m) % i;
        }
        return ans;
    }
}
