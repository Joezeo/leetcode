package com.joezeo.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    public static void main(String[] args) {
        int nums[] = {3,2,4};
        int result[] = twoSum(nums, 6);
    }

    /**
     * 使用HashMap一次迭代即可完成
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> nummap = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int cmp = target - nums[i];
            if(nummap.containsKey(cmp)){
                return new int[]{nummap.get(cmp),i};
            }
            nummap.put(nums[i], i);
        }
        return null;
    }

    /**
     *  暴力求解
     */
    public static int[] twoSumVolance(int[] nums, int target) {
        for(int i=0; i<nums.length-1; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[i]+nums[j]==target){
                    int[] result ={i,j};
                    return result;
                }
            }
        }
        return null;
    }
}
