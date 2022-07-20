package com.leetcode.dp.palindromic;

public class NumbersOfPalindromicSubstring {

    /**
     * 题目链接：https://leetcode-cn.com/problems/palindromic-substrings/
     *
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     *
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     *
     * 示例 1：
     *
     * 输入："abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     *
     * 示例 2：
     *
     * 输入："aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     *
     * 提示：输入的字符串长度不会超过 1000。
     */


    // dp[i][j] 表示 从下标i到下标j所构成的子串，是否是回文串
    //      注意别忘了两个相连的字符，组成的回文串
    // dp[i][j] = true   if  str[i] == str[j] && (dp[i+1][j-1] = true || j - i == 1)
    //          = true   if  i == j
    //          = false  else

    // 遍历顺序，从下到上，在从左到右
    public int numsOfPalindromic(String str) {
        int count = 0;

        boolean[][] dp = new boolean[str.length()][str.length()];

        for (int i = str.length() - 1; i >= 0; i--) {
            for (int j = i; j < str.length(); j++) {
                if (i == j || (str.charAt(i) == str.charAt(j) && (dp[i+1][j-1] || j - i == 1))) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = false;
                }

                if (dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        String str = "aaabbb";
        NumbersOfPalindromicSubstring mm = new NumbersOfPalindromicSubstring();
        System.out.println(mm.numsOfPalindromic(str));
    }
}
