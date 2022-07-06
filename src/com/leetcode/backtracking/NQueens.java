package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/n-queens
     *
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     *
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * 输入：n = 4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     *
     * 输入：n = 1
     * 输出：[["Q"]]
     */


    // 回溯问题，转化为树问题。
    // 树的深度，就是棋盘的高，树的宽度，就是树的宽度
    public List<List<String>> solveNQueens(int n) {
        String[][] base = new String[n][n];
        backTracing(base, n, 0);
        return result;
    }

    List<List<String>> result = new ArrayList<>();

    public void backTracing(String[][] base, int n, int row) {
        // 到达叶子节点， 不满足放置条件的，row不会加一，所以无法到达深度为n
        if (n == row) {
            List<String> oneResult =  new ArrayList<>();
            for (int i = 0; i < base.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < base[0].length; j++) {
                    sb.append(base[i][j] == null ? "." : base[i][j]);
                }
                oneResult.add(sb.toString());
            }
            result.add(oneResult);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (shouldPut(row, col, n, base)) {
                base[row][col] = "Q";
                backTracing(base, n, row+1);
                base[row][col] = null;
            } else {
                continue;
            }
        }
    }

    private boolean shouldPut(int originRow, int originCol, int n, String[][] base) {
        int row = originRow;
        int col = originCol;
        while (row > 0) {   // 一个竖列上，判断有没有Q
            if ("Q".equals(base[--row][col]))
                return false;
        }

        row = originRow;
        col = originCol;
        while (row > 0 && col < n-1) {   // 45度斜线上，有没有Q
            if ("Q".equals(base[--row][++col]))
                return false;
        }

        row = originRow;
        col = originCol;
        while (row < n-1 && col > 0) {   // 45度斜线上，有没有Q
            if ("Q".equals(base[row++][--col]))
                return false;
        }

        // 135度斜线上，有没有Q
        row = originRow;
        col = originCol;
        while (row > 0 && col > 0) {
            if ("Q".equals(base[--row][--col]))
                return false;
        }

        row = originRow;
        col = originCol;
        while (row < n-1 && col < n-1) {
            if ("Q".equals(base[++row][++col]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        int n = 4;
        int n = 3;
        NQueens nn = new NQueens();
        System.out.println(nn.solveNQueens(n));
    }
}
