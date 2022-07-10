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
     *
     * PS : N皇后，指的是皇后们，不可同行、不可同列、不可同斜线。
     */


    // 回溯问题，转化为树问题。
    // 棋盘的高，就是树的深度，棋盘的宽度，就是每一个节点的子树的宽度。
    public List<List<String>> solveNQueens(int n) {
        String[][] base = new String[n][n];
        backTracing(base, n, 0);
        return result;
    }

    List<List<String>> result = new ArrayList<>();

    public void backTracing(String[][] base, int n, int row) {
        // 到达叶子节点， 不满足放置条件的，row不会加一，所以无法到达深度为n
        if (n == row) {
            result.add(processResult(base));
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

    /**
    * 将棋盘中的null，处理成.   以便满足展示结果
    */
    private List<String> processResult(String[][] base) {
        List<String> oneResult =  new ArrayList<>();
        for (int i = 0; i < base.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < base[0].length; j++) {
                sb.append(base[i][j] == null ? "." : base[i][j]);
            }
            oneResult.add(sb.toString());
        }
        return oneResult;
    }

    public static void main(String[] args) {
//        int n = 4;
        int n = 3;
        NQueens nn = new NQueens();
        System.out.println(nn.solveNQueens(n));
    }
}
