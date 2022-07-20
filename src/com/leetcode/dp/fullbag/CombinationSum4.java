package com.leetcode.dp.fullbag;

public class CombinationSum4 {

    /**
     * 题目链接：https://leetcode-cn.com/problems/combination-sum-iv/
     * 难度：中等
     *
     * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
     *
     * 示例:
     * nums = [1, 2, 3] target = 4
     * 所有可能的组合为：(1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3, 1)
     * 因此输出为 7。
     *
     * 请注意，顺序不同的序列被视作不同的组合。
     */

    // 1、dp[i] 表示和为i的组合的个数
    // 2、dp[i] = dp[i] + dp[i - nums[j]]   // 选择nums[j] 加入组合 与 不选择
    // dp[0] = 1;
    //
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] = dp[i] + dp[i - nums[j]];
                } else {
                    dp[i] = 0;
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        int target = 4;
        CombinationSum4 cm = new CombinationSum4();
        System.out.println(cm.combinationSum4(nums, target));
    }

}
