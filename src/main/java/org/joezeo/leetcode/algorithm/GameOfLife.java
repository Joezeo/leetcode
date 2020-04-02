package org.joezeo.leetcode.algorithm;

/**
 * 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 *  
 * 示例：
 * <p>
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *  
 * 进阶：
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/game-of-life
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/4/2 20:01
 */
public class GameOfLife {
    /**
     * 由于数组的值值有0和1
     * int有32位bit，但是只有最低位被利用了，那么我们可以利用最低位存储原始状态，第二位位存储跟新后的状态
     *
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int[] offX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] offY = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int x = i + offX[k];
                    int y = j + offY[k];
                    if (x < 0 || y < 0 || x >= board.length || y >= board[x].length) {
                        continue;
                    }
                    if ((board[x][y] & 1) == 1) { // &1取出最低位与1进行比较
                        count++;
                    }
                }
                if ((board[i][j] & 1) > 0) {
                    // 这个是活细胞
                    if (count >= 2 && count <= 3) {
                        // 周围有2/3个活细胞，下一个状态还是活细胞。
                        board[i][j] = 0b11;
                    }
                    // 周围活细胞过多或过少都会死，因为原数据是0b01，所以这里不用额外赋值。
                } else if (count == 3) {
                    // 这个是死细胞，周围有3个活细胞，下一个状态变为活细胞。
                    board[i][j] = 0b10;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }

    /**
     * 使用一个数组来保存原来的数组，有点类型双缓冲机制
     *
     * @param board
     */
    public void gameOfLife_buffer(int[][] board) {
        int[][] old = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                old[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int liveCount = countLiveAround(old, i, j);
                if (liveCount < 2) {
                    board[i][j] = 0;
                }
                if (liveCount == 3) {
                    board[i][j] = 1;
                }
                if (liveCount > 3) {
                    board[i][j] = 0;
                }
            }
        }
    }

    public int countLiveAround(int[][] board, int i, int j) {
        int count = 0;

        if ((i - 1 >= 0 && j - 1 >= 0) && board[i - 1][j - 1] == 1) {
            count++;
        }
        if ((i - 1 >= 0) && board[i - 1][j] == 1) {
            count++;
        }
        if ((i - 1 >= 0 && j + 1 < board[i - 1].length) && board[i - 1][j + 1] == 1) {
            count++;
        }
        if (j - 1 >= 0 && board[i][j - 1] == 1) {
            count++;
        }
        if (j + 1 < board[i].length && board[i][j + 1] == 1) {
            count++;
        }
        if ((i + 1 < board.length && j - 1 >= 0) && board[i + 1][j - 1] == 1) {
            count++;
        }
        if (i + 1 < board.length && board[i + 1][j] == 1) {
            count++;
        }
        if ((i + 1 < board.length && j + 1 < board[i + 1].length) && board[i + 1][j + 1] == 1) {
            count++;
        }
        return count;
    }
}
