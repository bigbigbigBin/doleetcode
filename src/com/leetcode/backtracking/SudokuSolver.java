package backtracking;

public class SudokuSolver {


    public void solveSudoku(char[][] board) {
        backTracing(board);
    }

    // 数独
    public boolean backTracing(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') { // 如果当前棋盘位置不可以填充
                    continue;
                }
                for (char k = '1'; k <= '9'; k++) {
                    if (shouldPut(i, j, k, board)) { // 如果此位置可以插入k，则继续填充下一层
                        board[i][j] = k;
                        if (backTracing(board)) // 找到一组合适的就立刻返回
                            return true;
                        board[i][j] = '.';
                    }
                }
                return false;  // 试了9个数字，每个数字都不成功
            }

        }
        return true;
    }

    private boolean shouldPut(int row, int col, char num, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] != '.' && board[i][col] == num) {
                return false;
            }
        }

        for (int j = 0; j < board[0].length; j++) {
            if (board[row][j] != '.' && board[row][j] == num) {
                return false;
            }
        }

        int rowIncr = (row / 3 ) * 3;
        int colIncr = (col / 3 ) * 3;
        for (int i = rowIncr; i < rowIncr + 3; i++) {
            for (int j = colIncr; j < colIncr + 3; j++) {
                if (board[i][j] != '.' && board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

}
