package com.leetcode.dp.buy_sale_stock;

import java.util.Arrays;

public class BestTimeToBuySellStock4 {
    /**
     * 题目链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
     *
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1：
     * 输入：k = 2, prices = [2,4,1]
     * 输出：2
     * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2。
     *
     * 示例 2：
     * 输入：k = 2, prices = [3,2,6,5,0,3]
     * 输出：7
     * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4。随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
     *
     * 提示：
     *
     * 0 <= k <= 100
     * 0 <= prices.length <= 1000
     * 0 <= prices[i] <= 1000
     */

    // dp[i][0]    不操作（在上一次不持有、到持有之间）
    // dp[i][m]  第i天的状态是：第m持有
    // dp[i][n]  第i天的状态是：第n次不持有
    public int buySellStock(int[] prices, int k) {
        int[][] dp = new int[prices.length][2 * k + 1];
        for (int i = 1; i <= 2 * k; i++) {
            if (i % 2 != 0) {
                dp[0][i] = -prices[0];
            }
        }

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i-1][0];
            for (int j = 1; j < 2 * k + 1; j ++) {
                if (j % 2 != 0) { // 说明是持有
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1] - prices[i]);
                } else { // 说明是不持有
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1] + prices[i]);
                }
            }
        }

        return dp[prices.length - 1][2 * k];
    }

    public static void main(String[] args) {
//        int k = 2;
//        int[] prices = {2,4,1};
        int k = 2;
        int[] prices = {3,2,6,5,0,3};
//        int[] prices = {1,2,3,4,5};
        BestTimeToBuySellStock4 bst4 = new BestTimeToBuySellStock4();
        System.out.println(bst4.buySellStock(prices, k));

    }

}
