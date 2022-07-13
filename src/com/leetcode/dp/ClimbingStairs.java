package dp;

public class ClimbingStairs {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/climbing-stairs
     *
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入：2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1 阶 + 1 阶
     * 2 阶
     * 示例 2：
     *
     * 输入：3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1 阶 + 1 阶 + 1 阶
     * 1 阶 + 2 阶
     * 2 阶 + 1 阶
     */

    // 1、定义：dp[n] 表示爬n阶台阶，有多少中爬法
    // 2、递推公式：dp[n] = dp[n-1] + dp[n-2]
    // 3、初始化：dp[1] = 1 dp[0] = 1
    // 4、举例： dp[2] = 1 + 1
    public int climbStairs(int n) {
        if (n < 2) {
            return 1;
        }

        int[] array = new int[n+1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i-1] + array[i-2];
        }
        return array[n];
    }

    /**
     * 本质是只依赖于前两个数，所以使用大小为2的数组，能够节省资源
     */
    public int climbStairs2(int n) {
        if (n < 2) {
            return 1;
        }

        int[] array = new int[2];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            int sum = array[0] + array[1];
            array[0] = array[1];
            array[1] = sum;
        }
        return array[1];
    }

    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        System.out.println(cs.climbStairs(10));
        System.out.println(cs.climbStairs2(10));
    }
}
