package org.joezeo.leetcode.algorithm.before;

import java.util.Arrays;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 */
public class GetLeatestNumbers {
    /**
     * 其实就是考察排序算法
     */
    /**
     *  进阶：只用快排排序小于k的一端即可
     */
    public static void main(String[] args) {
        int[] arr = {432,2,432,11,-123,123,554,2,0,-1,32,12,99};
        arr = getLeastNumbers(arr, 5);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        quickSort(arr);
        return Arrays.copyOf(arr, k);
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int min, int max) {
        if (min >= max) {
            return;
        }
        int i = min, j = max;
        int base = arr[i];
        while (i < j) {
            while (arr[j] >= base && i < j) {
                j--;
            }
            if (i < j) arr[i++] = arr[j];

            while (arr[i] <= base && i < j) {
                i++;
            }
            if (i < j) arr[j--] = arr[i];
        }

        // 此时i=j
        arr[i] = base;

        quickSort(arr, min, i - 1);
        quickSort(arr, i + 1, max);
    }
}
