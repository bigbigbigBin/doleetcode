package dp.fullbag;

import java.util.Arrays;

public class CoinChange2 {


    public int change(int amount, int[] coins) {
        // dp[i] 表示凑成总金额为i时，可能的硬币组合数
        // dp[i] = dp[i] + dp[i - coins[j]]
        int[] dp = new int[amount+1];
        dp[0] = 1;

        // 遍历顺序，一定是先物品，后背包。否则会出现组合数重复的情况。比如 金额为3，会出现1+1+1、1+2 、 2+1
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount ; j++) {
                if (j >= coins[i]) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
//            System.out.println(Arrays.toString(dp));
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};

        CoinChange2 cc = new CoinChange2();
        System.out.println(cc.change(amount, coins));

    }
}
