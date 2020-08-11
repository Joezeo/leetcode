package org.joezeo.leetcode.algorithm;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/8/11 10:59
 */
public class SurroundArea {
    public static void main(String[] args) {
        char[][] board = {
                {'x', 'x', 'x', 'x'},
                {'x', 'o', 'o', 'x'},
                {'x', 'x', 'o', 'x'},
                {'x', 'o', 'x', 'x'}
        };
        solve(board);
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    /**
     * 首先对边界上每一个'O'做深度优先搜索，将与其相连的所有'O'改为'-'。然后遍历矩阵，将矩阵中所有'O'改为'X',将矩阵中所有'-'变为'O'
     *
     * @param board
     */
    public static void solve(char[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                boolean isBoarder = (y == 0 || y == board.length - 1 || x == 0 || x == board[y].length - 1);
                if (board[y][x] == 'o' && isBoarder) {
                    traval(board, x, y);
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'o') {
                    board[i][j] = 'x';
                }
                if (board[i][j] == '-') {
                    board[i][j] = 'o';
                }
            }
        }
    }

    public static void traval(char[][] board, int x, int y) {
        if (y < 0 || y > board.length - 1) {
            return;
        }
        if (x < 0 || x > board[y].length - 1) {
            return;
        }
        if (board[y][x] == 'x') {
            return;
        }
        if (board[y][x] == '-') {
            return;
        }
        if (board[y][x] == 'o') {
            board[y][x] = '-';
        }
        traval(board, x + 1, y);
        traval(board, x - 1, y);
        traval(board, x, y + 1);
        traval(board, x, y - 1);
    }
}
