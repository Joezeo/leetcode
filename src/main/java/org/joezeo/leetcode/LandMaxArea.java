package com.joezeo.algorithm.leetcode;

/**
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 */
public class LandMaxArea {
    public static void main(String[] args) {
        int grid[][] = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        int maxArea = maxAreaOfIsland(grid);
        System.out.println(maxArea);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int area = 0;
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    area = explore(grid, i, j);
                    maxArea = maxArea > area ? maxArea : area;
                }
            }
        }
        return maxArea;
    }

    public static int explore(int[][] grid, int i, int j) {
        if (i >= grid.length || i < 0) {
            return 0;
        }
        if (j >= grid[i].length || j < 0) {
            return 0;
        }
        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            return 1 + explore(grid, i + 1, j) + explore(grid, i, j + 1) + explore(grid, i - 1, j) + explore(grid, i, j - 1);
        }
        return 0;
    }
}
