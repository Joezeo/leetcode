package org.joezeo.leetcode.algorithm;

import java.util.Arrays;

/**
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * <p>
 * 不占用额外内存空间能否做到？
 * <p>
 * 示例 1:
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * 示例 2:
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/4/7 17:25
 */
public class RotateRectangle {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate_2(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    /** 解法二：通过解法一我们可以得出一个点(i,j)经过旋转后的坐标为(j, col-i-1)
     点(j,col-i-1)旋转后的坐标为(col-i-1, col-j-1)
     点(col-i-1, col-j-1)旋转后的坐标为(col-j-1, i)
     点(col-j-1, i)旋转后的坐标为(i, j)
     可以得出一个点经过4次旋转后的位置回到原点
     进而，我们可以用一个临时变量就可以完成一个点的旋转
     */
    /**
     * 接下来讨论我们需要讨论数组需要处理的范围
     * <p>
     * 表示我们需要处理的范围
     * 数组的长度为偶数时：
     *    * * x x
     *    * * x x
     *    x x x x
     *    x x x x
     * <p>
     * 数组的长度为奇数时：
     *  * * * x x
     *  * * * x x
     *  x x x x x
     *  x x x x x
     *  x x x x x
     * <p>
     * 故我们只需使 i < row/2
     *            j < (col+1)/2
     */
    public static void rotate_2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row / 2; i++) {
            for (int j = 0; j < (col + 1) / 2; j++) {
                int tmp = matrix[col - j - 1][i];
                matrix[col - j - 1][i] = matrix[col - i - 1][col - j - 1];
                matrix[col - i - 1][col - j - 1] = matrix[j][col - i - 1];
                matrix[j][col - i - 1] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }

    public static void rotate_1(int[][] matrix) {
        // 解法一：使用辅助数组，明显达不到题目要求的进阶要求：不占用额外内存空间
        // 原数组的第i行第j列即是辅助数组的第j行第col-i-1列
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] buf = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                buf[j][col - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = buf[i][j];
            }
        }
    }
}
