package com.joezeo.algorithm.leetcode;

/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * <p>
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 */
public class LandPerimeter {
    public static void main(String[] args) {
        int grid[][] = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int perimeter = islandPerimeter(grid);
        System.out.println(perimeter);
    }

    public static int islandPerimeter(int[][] grid) {
        int l = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    l = explore(grid, i, j);
                    return l;
                }
            }
        }
        return l;
    }

    public static int explore(int[][] grid, int i, int j) {
        // 1临近的是0或超出边界，表示该土地存在1的边长
        if (i >= grid.length || i < 0) {
            return 1;
        }
        if (j >= grid[i].length || j < 0) {
            return 1;
        }
        if (grid[i][j] == -1) { // 表明该土地已被探索
            return 0;
        }

        if (grid[i][j] == 1) {
            grid[i][j] = -1; // 标记
            return explore(grid, i + 1, j) + explore(grid, i, j + 1) + explore(grid, i - 1, j) + explore(grid, i, j - 1);
        }
        return 1;
    }
}
