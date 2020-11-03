package org.joezeo.leetcode.algorithm;

/**
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 *
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 *
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * 示例 1：
 * 输入：[2,1]
 * 输出：false
 *
 * 示例 2：
 * 输入：[3,5,5]
 * 输出：false
 *
 * 示例 3：
 * 输入：[0,3,2,1]
 * 输出：true
 *
 * 提示：
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/11/3 15:52
 */
public class ValidMountainArray {
    // 一个数组如果先是递增然后递减，那么他就是山脉数组
    // 时间复杂度O(n)
    public boolean validMountainArray_first(int[] A) {
        if (A.length < 3) {
            return false;
        }
        int direction = 0; // 当前方向，1表示递增，-1表示递减
        int cur = 0, next = 1;
        for (; next < A.length; cur++,next++) {
            if (A[cur] == A[next]) {
                return false;
            }

            if (A[cur] > A[next]) {
                if (direction == 0) {
                    return false;
                }
                direction = -1;
            } else {
                if (direction == -1) {
                    return false;
                }
                direction = 1;
            }
        }
        return direction == -1;
    }

    // 双指针，分别从左和从右扫描数组寻找山峰，如果山峰下标不一致，则返回false
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }
        int left = 0, right = A.length - 1;
        int leftMax = left, rightMax = right;
        boolean foundLeft = false, foundRight = false;
        while (left < A.length && right >= 0) {
            if (A[leftMax] < A[left] && !foundLeft) {
                leftMax = left;
            }
            if (A[leftMax] > A[left]) {
                foundLeft = true;
            }

            if (A[rightMax] < A[right] && !foundRight) {
                rightMax = right;
            }
            if (A[rightMax] > A[right]) {
                foundRight = true;
            }

            left++;
            right--;
        }
        return leftMax == rightMax && (leftMax != 0 && rightMax != A.length-1);
    }
}
