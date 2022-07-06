import java.util.Arrays;

public class PartitionEqualSubsetSum {

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
     * */

    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println("sum = " + sum);
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;

        // 1、定义含义： dp[i][j]表示，从[0,...i]元素为止，背包容量为j时所能装的最大数字和
        int[][] dp = new int[nums.length+1][target+1];

        // 2、状态转移方程：dp[i][j] = max(dp[i-1][j], dp[i-1][j-w(i)] + w(i))

        // 背包容量为0的时候，放入物品i
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = 0;
        }

        // 放入物品0的时候，背包的价值
        for (int i =0; i <= target ;i++) {
            dp[0][i] = 0;
        }

        for (int i = 0; i <= nums.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println("================");

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1;j <= target; j++) {
                if (j - nums[i-1] >= 0) { // 如果容量能够放下物品i
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i-1]] + nums[i-1]);
                } else { // 如果容量不足以放下物品i
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        for (int i = 0; i <= nums.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[nums.length][target] == target;

    }

    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
//        int[] nums = {1,2,3,5};
        PartitionEqualSubsetSum pp = new PartitionEqualSubsetSum();
        System.out.println(pp.canPartition(nums));
    }


}
