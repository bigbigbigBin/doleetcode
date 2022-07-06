package dp;

public class LongestValidParentheses {
    /**
     * https://leetcode-cn.com/problems/longest-valid-parentheses/
     * 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     *
     * 示例 1：
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     *
     * 示例 2：
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     *
     * 示例 3：
     * 输入：s = ""
     * 输出：0
     */

    public int longestValidParentheses(String s) {
        // dp[i] 表示以下标i为结尾的子串的最长有效括号的长度
        // 有效括号，则i位置必定要为 ')'

        // 如果 s(i) == ')' 且 s（i-1） = '('
        // dp[i] = dp[i-2] +2;

        // 如果 s(i) == ')' 且 s(i-1) == ')'
        //    i-1位置的最长有效括号长度 = dp[i-1]
        //    则 i- dp[i-1] -1 位置如果为 '('， 则说明从 i- dp[i-1]- 1 到i是一个合法的括号串
        // dp[i] = dp[i-dp[i-1] -2 ] + dp[i-1] + 2
        //       i- dp[i-1] -1 位置如果不是 '('   则说明不是一个合法的括号序列

        int max = 0;

        int[] dp = new int[s.length()+1];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')' && s.charAt(i-1) == '(') {
                dp[i] = (i - 2 >= 0 ? dp[i-2] : 0) + 2;
            } else if (s.charAt(i) == ')' && s.charAt(i-1) == ')') {
                if (i - dp[i-1] -1 >= 0 && s.charAt(i - dp[i-1] -1) == '(') {
                    dp[i] = (i-dp[i-1] -2 >=0 ? dp[i-dp[i-1] -2 ] : 0)  + dp[i-1] + 2;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
