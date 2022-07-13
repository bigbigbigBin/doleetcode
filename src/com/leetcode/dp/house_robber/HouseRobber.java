package dp.house_robber;

public class HouseRobber {
    /**
     * 题目链接：https://leetcode-cn.com/problems/house-robber/
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 示例 1：输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 示例 2：
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * 提示：
     *
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     */

    // dp[i] 表示，当到达房屋i时，能够偷窃的最高金额
    // dp[i] = max(dp[i-2] + money[i], dp[i-1])
    // dp[0] = 0
    // dp[1] = money[1]
    public int houseRobber(int[] money) {
        if (money.length == 1) {
            return money[0];
        }
        int [] dp = new int[money.length];
        dp[0] = money[0];
        dp[1] = money[1];

        for (int i = 2; i < money.length; i++) {
            dp[i] = Math.max(dp[i-2] + money[i] , dp[i-1]);
        }
        return dp[money.length-1];
    }

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        int[] money = {2,7,9,3,1};
        System.out.println(hr.houseRobber(money));
    }
}
