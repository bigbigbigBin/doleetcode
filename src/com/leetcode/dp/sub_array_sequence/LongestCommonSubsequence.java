package com.leetcode.dp.sub_array_sequence;

public class LongestCommonSubsequence {

    /**
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
     *
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
     *
     * 若这两个字符串没有公共子序列，则返回 0。
     *
     * 示例 1:
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace"，它的长度为 3。
     *
     * 示例 2:
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc"，它的长度为 3。
     *
     * 示例 3:
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0。
     *
     * 提示:
     * 1 <= text1.length <= 1000
     * 1 <= text2.length <= 1000 输入的字符串只含有小写英文字符。
     */

    // 本题目求解的是【子序列】，子序列要求序列中的字符在原串中不连续
    // dp[i][j]表示text1字符串中以i-1为结尾的字符串和text2字符串中以j-1为结尾的字符串的公共子序列的长度
    // dp[i][j] = dp[i-1][j-1] + 1, if text1[i] == text2[j]
    //          = Max(dp[i-1][j],dp[i][j-1])     if text1[i] != text2[j]
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        int maxLength = 0;

        // 因为dp[i][j]定义为以i-1为结尾的字符串A和以j-1为结尾的字符串B
        //  1 <= i, j <= text.length()
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                maxLength = Math.max(dp[i][j], maxLength);
            }
        }
        return maxLength;
    }



//    // 本题目求解的是【子序列】，子序列要求序列中的字符在原串中不连续
//    // dp[i][j]表示text1字符串中以i为结尾的字符串和text2字符串中以j为结尾的字符串的公共子序列的长度
//    // dp[i][j] = dp[i-1][j-1] + 1, if text1[i] == text2[j]
//    //          = Max(dp[i-1][j],dp[i][j-1])     if text1[i] != text2[j]
//    public int longestCommonSubsequence2(String text1, String text2) {
//        int[][] dp = new int[text1.length()][text2.length()];
//        int maxLength = 0;
//        // 初始化
//        for (int i = 0; i < text1.length(); i++) {
//            if (text1.charAt(i) == text2.charAt(0)) {
//                for (int j = i; j < text1.length(); j++) {
//                    dp[j][0] = 1;
//                }
//                maxLength = 1;
//                break;
//            }
//        }
//        // 初始化
//        for (int i = 0; i < text2.length(); i++) {
//            if (text1.charAt(0) == text2.charAt(i)) {
//                for (int j = i; j < text2.length(); j++) {
//                    dp[0][j] = 1;
//                }
//                maxLength = 1;
//                break;
//            }
//        }
//
////        System.out.println("初始化完毕之后");
////        for (int i = 0; i < dp.length; i++) {
////            System.out.println(Arrays.toString(dp[i]));
////        }
//
//        for (int i = 1; i < text1.length(); i++) {
//            for (int j = 1; j < text2.length(); j++) {
//                if (text1.charAt(i) == text2.charAt(j)) {
//                    dp[i][j] = dp[i-1][j-1] + 1;
//                } else {
//                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
//                }
//                if (dp[i][j] > maxLength) {
//                    maxLength = dp[i][j];
//                }
//            }
//        }
////        System.out.println("整个遍历完");
////        for (int i = 0; i < dp.length; i++) {
////            System.out.println(Arrays.toString(dp[i]));
////        }
//        return maxLength;
//    }

    public static void main(String[] args) {
//        String text1 = "abcde";
//        String text2 = "ace";
        String text1 = "xaxx";
        String text2 = "a";
        LongestCommonSubsequence ll = new LongestCommonSubsequence();
        System.out.println(ll.longestCommonSubsequence(text1, text2));
//        System.out.println(ll.longestCommonSubsequence2(text1, text2));
    }

}
