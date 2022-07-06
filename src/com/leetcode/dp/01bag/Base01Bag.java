import java.util.Arrays;

public class Base01Bag {

    // dp[i][j] 表示从0--i个物品中，任意选择，放入背包后背包总价值
    // dp[i][j] = {dp[i-1][j]  背包不放入物品i
    //            {dp[i-1][j-weight[i]] + value[i]  如果背包剩余容量能够放下物品i，背包放入物品i
    //            取最大值
    public int bag(int[] weight, int[] value, int bagWeight){
        int[][] dp = new int[weight.length][bagWeight + 1];
        for (int i = 0; i < weight.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= bagWeight; i++) {
            dp[0][i] = value[0];
        }

        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagWeight; j++) {
                if (j - weight[i] >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - weight[i]] + value[i]);
                }
            }
        }
        for (int i = 0; i < dp.length; i++)
            System.out.println(Arrays.toString(dp[i]));
        return dp[weight.length-1][bagWeight];
    }


    // 滚动数组法
    // dp[j] 表示背包容量为j时，背包所存物品的最大价值
    // dp[j] = Max(dp[j], dp[j- weight[i]] + value[i])
    //         容量为j时，背包不额外加上物品i    VS   额外加上物品i
    public int bag2(int[] weight, int[] value, int bagWeight){
        int[] dp = new int[bagWeight + 1];
        // 初始化，就默认为0
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bagWeight];
    }

    public static void main(String[] args) {
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        int bagWeight = 4;
        Base01Bag bb = new Base01Bag();
        System.out.println(bb.bag(weight, value, bagWeight));
        System.out.println(bb.bag2(weight, value, bagWeight));
    }
}
