package org.joezeo.leetcode.algorithm.Y2020M11.b15t22;

/**
 * 归并排序
 *
 * @author Joezeo
 * @date 2020/11/21 15:48
 */
public class MergeSort {

    public static void sort(int[] arr, int left, int right) {
        // 对于一个完全无序的数组使用归并排序我们要使用到分治的思想
        if (left == right) { // 递归头：当子数组只有一个元素时，那它必然是有序的
            return;
        }
        int middle = (right + left) / 2; // fuck！！！中位数是(left+right)/2
        // 对左边进行分治归并排序
        sort(arr, left, middle);
        // 对右边进行分治归并排序
        sort(arr, middle + 1, right);
        // 合并
        merge(arr, left, middle, right);
    }

    // 对两个有序的数组进行合并，注：左边包含middle
    private static void merge(int[] arr, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        // 提出左边的有序数组
        int[] larr = new int[leftSize];
        int index = left;
        for (int i = 0; i < leftSize; i++) {
            larr[i] = arr[index++];
        }

        // 提出右边的有序数组
        int[] rarr = new int[rightSize];
        index = middle + 1;
        for (int i = 0; i < rightSize; i++) {
            rarr[i] = arr[index++];
        }

        // 合并
        int l = 0, r = 0, m = left;
        while (l < leftSize && r < rightSize) {
            arr[m++] = larr[l] < rarr[r] ? larr[l++] : rarr[r++];
        }

        while (l < leftSize) {
            arr[m++] = larr[l++];
        }
        while (r < rightSize) {
            arr[m++] = rarr[r++];
        }
    }
}
