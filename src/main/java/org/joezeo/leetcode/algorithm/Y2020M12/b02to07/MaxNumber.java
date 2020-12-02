package org.joezeo.leetcode.algorithm.Y2020M12.b02to07;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，
 * 要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * <p>
 * 示例 1:
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * <p>
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * <p>
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/12/2 10:31
 */
public class MaxNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxNumber(new int[]{5, 4, 6}, new int[]{5, 4, 6}, 2)));
    }

    /**
     * 本质上对两个数组进行排序，考虑使用归并？
     * 使用归并来尝试下
     * <p>
     * 不能使用归并，因为不能改变两个数组原来的顺序!!!!
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static int maxNumber_wrong(int[] nums1, int[] nums2, int k) {
        int[] arr = new int[nums1.length + nums2.length];
        int idx = 0;
        for (int num : nums1) {
            arr[idx++] = num;
        }
        for (int num : nums2) {
            arr[idx++] = num;
        }
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        int res = 0;
        for (int i = 0; i < k; i++) {
            res = res * 10 + arr[i];
        }
        return res;
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int middle = (left + right) / 2;
        // 左边包含middle
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    public static void merge(int[] arr, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];
        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = arr[i + left];
        }
        for (int i = 0; i < rightSize; i++) {
            rightArr[i] = arr[i + middle + 1];
        }
        int l = 0, r = 0, z = left;
        while (l < leftSize && r < rightSize) {
            arr[z++] = leftArr[l] > rightArr[r] ? leftArr[l++] : rightArr[r++];
        }
        while (l < leftSize) {
            arr[z++] = leftArr[l++];
        }
        while (r < rightSize) {
            arr[z++] = rightArr[r++];
        }
    }

    ////////////////////////////////////////// 分割线 /////////////////////////////////////


    /**
     * 其实像这样题目中出现不可以改变数组原先顺序的提，都可以考虑使用单调栈来解决
     * 1.首先先找到两个数组的最大子序列，且两个序列长度的和相加为k
     * 2.然后对两个子序列进行归并
     * <p>
     * 步骤1类似题目：org.joezeo.leetcode.algorithm.Y2020M11.b15t22.RemoveKdigits
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int left = 0, right = 0;
        Deque<Integer> stack1 = new LinkedList<>();
        while (left < nums1.length) {
            if (!stack1.isEmpty() && stack1.peekLast() < nums1[left]) {
                stack1.pollLast();
            }
            stack1.offerLast(nums1[left]);
            left++;
        }

        Deque<Integer> stack2 = new LinkedList<>();
        while (right < nums2.length) {
            if (!stack2.isEmpty() && stack2.peekLast() < nums2[right]) {
                stack2.pollLast();
            }
            stack2.offerLast(nums2[right]);
            right++;
        }
        int[] arr = new int[k];
        int idx = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty() && idx < k) {
            arr[idx++] = stack1.peekFirst() > stack2.peekFirst() ? stack1.pollFirst() : stack2.pollFirst();
        }
        while (!stack1.isEmpty()) {
            arr[idx++] = stack1.pollFirst();
        }
        while (!stack2.isEmpty()) {
            arr[idx++] = stack2.pollFirst();
        }
        return arr;
    }
}
