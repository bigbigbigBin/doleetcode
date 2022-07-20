package com.leetcode.dp.fullbag;

public class CoinChange {

    /**
     * 题目链接：https://leetcode-cn.com/problems/coin-change/
     *
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 你可以认为每种硬币的数量是无限的。
     *
     * 示例 1：输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     *
     * 示例 2：
     * 输入：coins = [2], amount = 3
     * 输出：-1
     *
     * 示例 3：
     * 输入：coins = [1], amount = 0
     * 输出：0
     *
     * 示例 4：
     * 输入：coins = [1], amount = 1
     * 输出：1
     *
     * 示例 5：输入：coins = [1], amount = 2
     * 输出：2
     *   提示：
     *
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 2^31 - 1
     * 0 <= amount <= 10^4
     * */

    public int coinChange(int[] coins, int amount) {
        // 完全背包，每次可以取0次或者无数次
        // dp[i]表示凑成金额i，需要的最少硬币个数
        // dp[i] = math.min(dp[i], dp[i - coins[j]] + 1)
        // dp[0] = 0; dp[i] 初始为MAX_INTEGER
        // 求的是个数，顺序就无所谓了
        if (amount == 0) {
            return 0;
        }

        int[] dp = new int[amount+1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i-coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
            // System.out.println(Arrays.toString(dp));
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}
