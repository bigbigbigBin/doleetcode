package dp;

public class UniqueBinarySearchTree {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/unique-binary-search-trees
     *
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     *
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：5
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     * */

    // dp[i] 表示 有i个节点所组成的互不相同的二叉搜索树的个数
    // dp[i] = sum(dp[以j为头结点的左子树的节点数量] * dp[以j为头节点的右子树的节点数量])   1<=j<=i
    //       = sum(dp[j-1] * dp[i-j])
    // dp[0] = 0  dp[1] = 1  dp[2] = 2

    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            for (int j = 1; j<= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

}
