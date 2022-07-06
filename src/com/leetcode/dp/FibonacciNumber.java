package dp;

public class FibonacciNumber {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/fibonacci-number
     *
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：F(0) = 0，F(1) = 1 F(n) = F(n - 1) + F(n - 2)，其中 n > 1 给你n ，请计算 F(n) 。
     *
     * 示例 1：
     *
     * 输入：2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     * 示例 2：
     *
     * 输入：3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     * 示例 3：
     *
     * 输入：4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
     * 提示：
     *
     * 0 <= n <= 30
     */


    // 1、定义： dp[i] 表示第i个数的斐波那契数值
    // 2、转移公式：dp[i] = dp[i-1] + dp[i-2]
    // 3、初始化 dp[0] = 0, dp[1] = 1
    // 4、确定遍历顺序， dp[i]依赖于dp[i-1]和dp[i-2]，所以是从左到右遍历
    // 5、举例推导dp数组
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] fibArray = new int[n + 1];
        fibArray[0] = 0;
        fibArray[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibArray[i] = fibArray[i-1] + fibArray[i-2];
        }

        return fibArray[n];
    }


}
