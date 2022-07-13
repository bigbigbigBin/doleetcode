public class TargetSum {
    // 通过添加+、-号，是的运算之后的结果为target
    // 我们将+号数字归为一类，假设为A，-号数字归为一类，假设为B
    // 那么 A-B = target , A+B = sum   能够推到出来 A = (target + sum) / 2
    // 那么此题目，转化为，从数组nums中，找出来和为A的种类
    //
    // dp[i][j] 表示从[0,...i]数字中，找出数字和为j的组合数
    // dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
    // 初始化： dp[0][0]  = 1 ，即，从0个数字中选择数字和为0，那么只有一种选法，即什么都不选

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ( (target + sum) % 2 == 1) {  //
            return 0;
        }
        int left = (target + sum) / 2;
        if (left < 0) {  // 我们假设的是，left为正数和，所以如果left < 0  则直接返回0
            return 0 ;
        }

        int dp[][] = new int[nums.length+1][left+1];

        // 从[0,...i]选择数字和为0，可能的组合数
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= left; j++) {
                if (j - nums[i-1] >= 0) {
                    // 这个题目求得是组合数，所以是+，而不是max
                    dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length][left];
    }

    public int findTargetSumWays2(int[] nums, int target) {

        int sum = 0;
        for (int nm : nums) {
            sum += nm;
        }
        // +号的数字组成的数字之和 = A ；  -号数字组成的和为B
        // A + B = sum   A - B = target
        // 整理公式则  A = (sum + target) / 2;
        int bagWeight = (sum + target) / 2;

        // dp[j] 表示找出数字和为j，共有多少种方案
        // 填满容量为j - nums[i]的背包，有dp[j - nums[i]]种方法。
        // 此题求得是组合数，并不是求价值或者重量，所以递归公式中，不加上数字价值
        int[] dp = new int[bagWeight + 1];

        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagWeight; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j-nums[i]];
            }
        }
        return dp[bagWeight];
    }
}
