package com.leetcode.greedy_algorithm;

public class BestTimeToBuyAndSellStockWithFee {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)
     *
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * 返回获得利润的最大值。
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * 示例 1:
     *
     * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 输出: 8
     * 解释: 能够达到的最大利润:
     *
     * 在此处买入 prices[0] = 1
     * 在此处卖出 prices[3] = 8
     * 在此处买入 prices[4] = 4
     * 在此处卖出 prices[5] = 9
     * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     * 注意:
     *
     * 0 < prices.length <= 50000.
     * 0 < prices[i] < 50000.
     * 0 <= fee < 50000.
     * */


    public int maxProfit(int[] prices, int fee) {
        int result = 0;
        int startPrice = prices[0] + fee; // 初始买入价格，成本价 + 手续费
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > startPrice) { // 某一天的股票价格大于买入成本
                result += prices[i] - startPrice;
                // 有可能本次卖掉后，下一天可能继续飙升。所以我们要在此刻，继续持有股票，以便下一天可以继续判断是不是需要出售掉
                // 此时，买入价格，仅仅是成本价，不包含手续费
                startPrice = prices[i];
            } else if (prices[i] + fee < startPrice){
                startPrice = prices[i] + fee;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] price = {1,3,7,5,10,3};
        int fee = 3;

        BestTimeToBuyAndSellStockWithFee bb = new BestTimeToBuyAndSellStockWithFee();
        System.out.println(bb.maxProfit(price, fee));
    }
}
