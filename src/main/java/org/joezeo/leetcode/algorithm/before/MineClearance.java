package org.joezeo.leetcode.algorithm.before;

/**
 * 让我们一起来玩扫雷游戏！
 *
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
 * 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 *
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 *
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minesweeper
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/8/20 10:55
 */
public class MineClearance {

    char[] numChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public char[][] updateBoard(char[][] board, int[] click) {
        return updateBoard_Re(board, click);
    }

    public char[][] updateBoard_Re(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if (x < 0 || x >= board.length) {
            return board;
        }
        if (y < 0 || y >= board[x].length) {
            return board;
        }
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        if (board[x][y] == 'B') {
            return board;
        }

        int mineCount = check(board, x, y, true);
        if (mineCount == 0 && board[x][y] == 'E') {
            board[x][y] = 'B';
            // 进行递归扫雷
            updateBoard_Re(board, new int[]{x+1, y});
            updateBoard_Re(board, new int[]{x, y+1});
            updateBoard_Re(board, new int[]{x-1, y});
            updateBoard_Re(board, new int[]{x, y-1});
            updateBoard_Re(board, new int[]{x+1, y+1});
            updateBoard_Re(board, new int[]{x+1, y-1});
            updateBoard_Re(board, new int[]{x-1, y+1});
            updateBoard_Re(board, new int[]{x-1, y-1});
        } else {
            board[x][y] = numChar[mineCount];
        }

        return board;
    }

    public int check(char[][] board, int x, int y, boolean isCenter) {
        if (x < 0 || x >= board.length) {
            return 0;
        }
        if (y < 0 || y >= board[x].length) {
            return 0;
        }
        // 只检查与该位置相邻的位置
        if (board[x][y] == 'M') {
            return 1;
        }

        if (isCenter) {
            return check(board, x+1, y, false) + check(board, x, y+1, false)
                    + check(board, x-1, y, false) + check(board, x, y-1, false)
                    + check(board, x+1, y+1, false) + check(board, x+1, y-1, false)
                    + check(board, x-1, y+1, false) + check(board, x-1, y-1, false);
        } else {
            return 0;
        }
    }
}
