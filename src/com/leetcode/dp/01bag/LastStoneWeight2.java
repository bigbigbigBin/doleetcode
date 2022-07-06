public class LastStoneWeight2 {


    /**
     * 题目链接：https://leetcode-cn.com/problems/partition-equal-subset-sum/
     *
     * 题目难易：中等
     *
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 注意:
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     *
     * 示例 1:
     * 输入: [1, 5, 11, 5]
     * 输出: true
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     * 示例 2:
     * 输入: [1, 2, 3, 5]
     * 输出: false
     * 解释: 数组不能分割成两个元素和相等的子集.
     */


    // 求最小可能的质量，
    // 每次任意选择出来的俩石头，相撞，留下来剩余部分。
    // 将整堆石头，分成重量尽量相同的两堆，这样相撞后留下的石头最小。
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int target = sum /2;

        // 转换为背包问题，背包的容量为target，这个背包容量下，最大所能存放的石头重量
        // dp[i][j]表示，从[0,...,i-1]石头中，选择出来能够放在背包容量为j的背包中，最大所能存放的石头重量
        int[][] dp = new int[stones.length+1][target+1];
        for (int i = 1; i <= stones.length; i++) {
            for (int j = 1; j<= target; j++) {
                if (j >= stones[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - stones[i-1]] + stones[i-1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        // for (int i = 0; i < dp.length; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        return sum - 2 * dp[stones.length][target];
    }

    public static void main(String[] args) {

    }
}
