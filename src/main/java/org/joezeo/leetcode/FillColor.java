package com.joezeo.algorithm.leetcode;

/**
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 * <p>
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 * <p>
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 * <p>
 * 最后返回经过上色渲染后的图像。
 */
public class FillColor {
    public static void main(String[] args) {
        int[][] image = {{0, 0, 0}, {0, 1, 1}};
        image = floodFill(image, 1, 1, 1);
        System.out.println(image);
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        fillColor(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    public static void fillColor(int[][] image, int i, int j, int newColor, int oldColor) {
        if (i >= image.length || i < 0) {
            return;
        }
        if (j >= image[i].length || j < 0) {
            return;
        }
        if (image[i][j] != oldColor) {
            return;
        }
        if (image[i][j] == newColor) {
            return;
        }
        if (image[i][j] == oldColor) {
            image[i][j] = newColor;
        }
        fillColor(image, i + 1, j, newColor, oldColor);
        fillColor(image, i - 1, j, newColor, oldColor);
        fillColor(image, i, j + 1, newColor, oldColor);
        fillColor(image, i, j - 1, newColor, oldColor);
    }
}
