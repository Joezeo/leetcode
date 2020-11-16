package org.joezeo.leetcode.algorithm.before;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/4/18 17:07
 */
public class MaxWaterContain {
    /**
     * 使用双指针，借鉴滑动窗口的思想
     * 如果右指针的值比左指针的值大，则左指针向右一位
     * 反之亦然
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int len = height.length;
        int l=0; // 左指针
        int r=len-1; // 右指针

        int maxVal = 0;
        while(l < r){
            int contain = Math.min(height[l],height[r]) * (r - l);
            maxVal = Math.max(maxVal, contain);
            if(height[l] > height[r]){
                r--;
            } else {
                l++;
            }
        }
        return maxVal;
    }
}
