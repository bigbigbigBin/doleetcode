package dp;

public class UniquePaths {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/unique-paths
     *
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     *
     * 问总共有多少条不同的路径？
     *
     * 示例 1：
     * 输入：m = 3, n = 7
     * 输出：28
     *
     * 示例 2：
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     */

    // dp[i][j] 到达(i,j)所可能有的路径数目
    // dp[i][j] = dp[i-1][j] + dp[i][j-1]   到达(i,j)，他的前一步只能是从（i-1，j）或者(i, j-1)
    // 初始化，为0
    // 遍历顺序，从左到右从上到下
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m;i++) {
            dp[i][0] = 1;
        }
        for (int i = 0 ; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
