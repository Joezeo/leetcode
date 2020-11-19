package org.joezeo.leetcode.algorithm.Y2020M11.b15t22;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/11/19 20:54
 */
public class MoveZeroes {
    /**
     * 一次遍历：记录下当前已经遍历到的0的个数n，当遍历到不是0的数时，向前移动n位
     */
    public void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
                continue;
            }
            if (count > 0) {
                nums[i - count] = nums[i];
            }
        }

        int index = nums.length - 1;
        while (count > 0) {
            nums[index] = 0;
            count--;
            index--;
        }
    }

}
