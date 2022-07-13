package dp.fullbag;

public class PerfectSquares {

    /**
     * 题目地址：https://leetcode-cn.com/problems/perfect-squares/
     *
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     *
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     *
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     * 示例 1：
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     *
     * 示例 2：
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     *   提示：
     *      1 <= n <= 10^4
     */

    // dp[i] 组成i需要的最少完全平方数的个数
    // dp[i] = min(d[i], dp[i - j *j])
    // dp[0] = 1
    public int minNumsForPerfectSquares(int target) {
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        int end = (int)Math.sqrt(target);

        for (int i = 1; i <= target; i++ ) {
            for (int j = 1; j < end; j++) {
                if (i >= j * j) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        PerfectSquares pp = new PerfectSquares();
        System.out.println(pp.minNumsForPerfectSquares(12));
    }
}
