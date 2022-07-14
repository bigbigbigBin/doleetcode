package com.leetcode.dp.buy_sale_stock;

public class BestTimeToBuySellStockWithCooldown {
    /**
     * 题目链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     *
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 示例:
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     */

    // 本题目，需要严格区分出来 当天卖出 、 不持有这两种状态
    // dp[i][0] 持有（今天买入、或者之前就买入一直保持到今天）
    // dp[i][1] 不持有，之前就卖出了，且过了冷冻期，然后一直保持未持有状态到了今天
    // dp[i][2] 今天卖出
    // dp[i][3] 冷冻期，则前一天必定要求是卖出状态，不能为其他，不能为未持有状态。

    public int buySellStock(int[] prices) {
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            // 持有
            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][3]) - prices[i]);
            // 不持有
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][3]);
            // 今天卖出
            dp[i][2] = dp[i-1][0] + prices[i];
            // 冷冻期，  肯定前一天是卖出
            dp[i][3] = dp[i-1][2];
        }

        return Math.max(Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]), dp[prices.length - 1][3]);
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        BestTimeToBuySellStockWithCooldown bst4 = new BestTimeToBuySellStockWithCooldown();
        System.out.println(bst4.buySellStock(prices));

    }



}
