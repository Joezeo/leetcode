package org.joezeo.leetcode.algorithm;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/4/4 14:05
 */
public class TrappingRain {
    /**
     * 使用暴力观察法，对于每一个下标我们都需要左右遍历寻找最大值，时间复杂度O(n2)
     * 使用动态规划，使用两个数组来分别存储某个位置的左右最大值，时间复杂度O(n)
     * 假设某个下标i 他的左边最大值为Math.max(height[i], leftMax[i-1])
     *             他的右边最大值为Math.max(height[i], rightMax[i+1])
     * 我们只需要设置两个递推的的起点，使用双指针，就可以一轮遍历存储下每个下标的左右最大值
     */
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0]; // 左边设置入口值
        rightMax[height.length - 1] = height[height.length - 1]; // 右边设置入口值
        for (int i = 1, j = height.length - 2; i < height.length && j >= 0; i++, j--) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
            rightMax[j] = Math.max(height[j], rightMax[j + 1]);
        }
        int count = 0;
        for (int i = 0; i < height.length; i++) {
            int mini = Math.min(leftMax[i], rightMax[i]);
            if (mini > height[i]) {
                count += mini - height[i];
            }
        }
        return count;
    }

    public int trap_observed(int[] height) {
        // 暴力观察法，时间复杂度为O(n2)
        // 一个位置，存在左边有值大于它，右边有值大于它便可以接雨水
        // 找左右两边大于当前值的最大高度的最小值，减去当前值便是当前值能接住的水的最大值
        // min(left,right)-cur
        int count = 0;
        for (int i = 1; i < height.length - 1; i++) { // 最左边和最右边无论如何接不了雨水
            //找左边大于当前值的最大高度
            int leftMax = height[i];
            for (int l = i - 1; l >= 0; l--) {
                if (height[l] > leftMax) {
                    leftMax = height[l];
                }
            }
            if (leftMax <= height[i]) {
                continue; // 在左边没有找到比当前值大的高度
            }

            // 找右边大于当前值的最大高度
            int rightMax = height[i];
            for (int r = i + 1; r < height.length; r++) {
                if (height[r] > rightMax) {
                    rightMax = height[r];
                }
            }
            if (rightMax <= height[i]) {
                continue;
            }
            count += Math.min(leftMax, rightMax) - height[i];
        }
        return count;
    }
}
