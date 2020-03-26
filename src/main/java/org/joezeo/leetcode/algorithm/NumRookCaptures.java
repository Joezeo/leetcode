package org.joezeo.leetcode.algorithm;

/**
 * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
 * <p>
 * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
 * <p>
 * 返回车能够在一次移动中捕获到的卒的数量。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/available-captures-for-rook
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumRookCaptures {
    public static void main(String[] args) {
        char[][] board = {{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','R','.','.','.','p'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'}};
        System.out.println(numRookCaptures(board));
    }

    /**
     * 使用深度优先算法
     */
    public static int numRookCaptures(char[][] board) {
        // 首先找到白色车的位置
        int i = 0, j = 0;
        out: for(; i<board.length; i++){
            for(j=0; j<board[i].length; j++){
                if(board[i][j] == 'R'){
                    break out;
                }
            }
        }

        int count = 0;
        // 向东移动
        count += moveRook(board, i, j, 1);
        // 向南移动
        count += moveRook(board, i, j, 2);
        // 向西移动
        count += moveRook(board, i, j, 3);
        // 向北移动
        count += moveRook(board, i, j, 4);
        return count;
    }

    public static int moveRook(char[][] board, int i, int j, int direction) { // direction:1,2,3,4-东南西北
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length || board[i][j] == 'B') {
            return 0;
        }
        if (board[i][j] == 'p') {
            return 1;
        }

        if (direction == 1) {
            return moveRook(board, i, j + 1, direction);
        } else if (direction == 2) {
            return moveRook(board, i + 1, j, direction);
        } else if (direction == 3) {
            return moveRook(board, i, j - 1, direction);
        } else {
            return moveRook(board, i - 1, j, direction);
        }
    }
}
