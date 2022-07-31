package com.toocol.lc.java;

import java.util.Arrays;

/**
 * 归并排序
 * 
 * @author JoeZane (joezane.cn@gmail.com)
 */
public class MergeSort {

    public void sort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        int lsize = m - l + 1;
        int rsize = r - m;
        int[] left = new int[lsize];
        int[] right = new int[rsize];
        System.arraycopy(arr, 0, left, 0, lsize);
        System.arraycopy(arr, lsize, right, 0, rsize);
        sort(left, l, m);
        sort(right, m + 1, r);
        merge(arr, left, right);
    }

    public void merge(int[] arr, int[] left, int[] right) {
        int l = 0, r = 0;
        int idx = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                arr[idx++] = left[l++];
            } else {
                arr[idx++] = right[r++];
            }
        }
        if (l < left.length)
            System.arraycopy(left, l, arr, idx, left.length - l);
        else if (r < right.length)
            System.arraycopy(right, r, arr, idx, right.length - r);
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 3, 23, 32, 2, 32, -1, 32, 44, 21, -32, -321, 77, 898, 23 };
        MergeSort ms = new MergeSort();
        ms.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
