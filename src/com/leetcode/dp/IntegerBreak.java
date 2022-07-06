package dp;

public class IntegerBreak {

    /**
     *力扣题目链接：https://leetcode-cn.com/problems/integer-break
     *
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。返回你可以获得的最大乘积。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * 示例 2:
     *
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     * 说明: 你可以假设 n 不小于 2 且不大于 58。
     * */

    // 1、dp[i] 表示拆分i所得的最大乘积
    // 2、dp[i] = max(dp[i], max(j * (i-j), j * dp[i-j]) )  1<=j<=n
    //         为什么是两层max呢？因为j在移动，每当j取值一个数，这个j下，dp[i]的值就确定下来了。
    //         我们求得是最大的，不是最后的。要是不用max求出最大的，那么j去值后面的数时所得出的dp[i]就会覆盖前面的j所求出的dp[i]
    // 3、初始话 dp[0] = 0, dp[1] = 1
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i<=n ; i++) {
            for (int j = 1; j <= i; j++) {
                // 为什么
                dp[i] = Math.max(dp[i], Math.max(j * (i-j), j * dp[i-j]));
            }
        }
        return dp[n];
    }
}
