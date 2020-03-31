package org.joezeo.leetcode.algorithm;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，请你将该数组升序排列。
 *
 * @author Joezeo
 * @date 2020/3/31 15:27
 */
public class SortArray {
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1, 14, 3, 21, 12};
        int[] tree = {1,18,3};
//        heapify(tree, tree.length, 0);
//        buildHeap(nums);
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static int[] sortArray(int[] nums) {
        // bubbleSort(nums);
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 冒泡排序，时间复杂度O(n2)，超出Leetcode时间限制
     */
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 选择排序, 时间复杂度O(n2)
     */
    public static void selectSort(int[] nums){
        for(int i=0; i<nums.length; i++){
            int idx = i;
            int min = nums[i];
            for(int j=i; j<nums.length; j++){
                if(nums[j] < min){
                    min = nums[j];
                    idx = j;
                }
            }
            int tmp = nums[i];
            nums[i] = nums[idx];
            nums[idx] = tmp;
        }
    }

    /**
     * 快速排序，时间复杂度O(nlogn)
     */
    public static void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int i = low, j = high;
        int base = nums[i];
        while (i < j) {
            while (nums[j] >= base && i < j) {
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }

            while (nums[i] < base && i < j) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        // 此时i，j相等
        nums[i] = base;
        // 对数组左边进行快排
        quickSort(nums, low, i - 1);
        // 对数组右边边进行快排
        quickSort(nums, i + 1, high);
    }

    /*----------------------------heap sort-------------------------------*/
    /**
     * 堆排序：O(nlogn)
     */
    public static void heapSort(int[] nums){
        buildHeap(nums);
        for(int i=nums.length-1; i>=0; i--){
            swap(nums, 0, i);
            heapify(nums, i, 0);
        }
    }

    /**
     * 从无序数组中构建一个堆
     * @param nums
     */
    private static void buildHeap(int[] nums){
        // 从最后一个节点的父节点依次向上开始堆化
        // 求出最后一个节点的父节点
        int last = (nums.length-1-1) / 2 ;
        for(int i=last; i>=0; i--){
            heapify(nums, nums.length, i);
        }
    }

    /**
     * 对第idx个节点进行堆化
     * @param nums
     * @param size 数组的大小
     * @param idx
     */
    private static void heapify(int[] nums, int size, int idx){
        if(idx >= size){
            return ;
        }

        int max = idx;
        int c1 = idx*2+1;
        int c2 = idx*2+2;
        if(c1<size && nums[c1]>nums[max]){
            max = c1;
        }
        if(c2<size && nums[c2]>nums[max]){
            max = c2;
        }
        if(max != idx){
            swap(nums, idx, max);
            heapify(nums, size, max);
        }
    }

    private static void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    /*----------------------------heap end-------------------------------*/
}
