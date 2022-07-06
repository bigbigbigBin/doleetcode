package dp;

import java.util.Arrays;
import java.util.Collections;

public class LongestPalindromeSubstring {

    /**
     * 描述
     * 对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
     *
     *
     * 数据范围： 1≤n≤1000
     * 要求: 空间复杂度 O(1)，时间复杂度 O(n^2)
     * 进阶: 空间复杂度 O(n)，时间复杂度 O(n)
     *
     * 示例1
     * 输入："ababc"
     * 输出：3
     * 说明：最长的回文子串为"aba"与"bab"，长度都为3
     *
     * 示例2
     * 输入："abbba"
     * 输出：5
     *
     * 示例3
     * 输入："b"
     * 输出：1
     *
     */





    /**
     * 错误思路：
     * 一开始我的想法是错误的，不能当做求和它的反转字符串的公共子序列，
     * 因为反转之后，回文的中心点和原来不是一个位置，求出的公共子串可能会变短
     */

    /**
     *
     * 1、首先明确一下dp数组的含义   dp[i][j] = true，表示从i到j位置的子串为回文串
     * 2、确定下递推公式
     *    dp[i][j] = true,  |---if A[i] == A[j], 还要看i到j之间的情况
     *                      |       |--- if j > i+1 && A[i+1][j-1] == true  说明i到j的子串为真的是回文串
     *                      |       |--- if j = i+1, 说明只有两个字符，必定为回文，如"aa"
     *                      |       |--- if j = i,说明只有一个字符，必定为回文串，如"a"
     *    dp[i][j] = false, |---if A[i] != A[j]
     *
     * 3、递推的顺序
     *   dp[i][j] 用到了dp[i+1][j-1], 在dp数组中，dp[i+1][j-1]所处位置在dp[i][j]的左下角位置。
     *   所以为了能够用到上一步暂存的数据，所以需要先从下往上，再从左往右。
     *
     * 4、dp数组初始化成什么样子？
     *    dp[i][i] = true，其它为false
     *
     * 5、最终所求结果怎么跟dp数组关联起来？
     *     dp数组存的只是字符串是否为回文串，我们求的是最长回文串的长度。
     *     所以在每一个回文串确定下来之后，判断一下当前的回文串的长度是否为最长。
     *     每个当前回文串的长度 = j-i+1
     *
     */
    public int getLongestPalindrome(String A) {
        boolean[][] dp = new boolean[A.length()][A.length()];
        int maxLength = 0;
        for (int i = A.length() - 1; i >= 0; i--) { // 从下往上
            for (int j = i; j < A.length(); j++) { // 从左往右，为什么不是从0开始呢？因为[i,j]表示的是一个字符串，j必定>=i,字符串没有负长度
                if (A.charAt(i) == A.charAt(j)) {
                    if (j == i) {
                        dp[i][j] = true;
                    } else if (j == i+1) {
                        dp[i][j] = true;
                    } else if (j > i+1 && dp[i+1][j-1]) {
                        dp[i][j] = true;
                    }
                    // 当dp[i][j]为回文串时，找到最大长度
                    if (dp[i][j] == true && maxLength < j - i + 1) {
                        maxLength = j - i + 1;
                    }
                }
            }
        }
        return maxLength;
    }


    public static void main(String[] args) {
//        String str = "ababc";
//        String str = "abbba";
//        String str = "b";
//        String str = "ccbcabaabba";   // 4
//      String str = "abbaabacbcc";
        // cbc、aba、abba

//        String str = "abaabba";   // 4
//      String str = "abbaaba";
        String str = "acbdcbbbdbdaaccbcacdacdccababcddabddaaaaaaabdbabcdddaacabacbacbbdabdacddbbadaacbbdcbccacacdabcabacacbbbdcccacdcdcdcbcbabdcdacdddbbabcaccddddddabdacaabccdcabcbcbabacaaaccaccaddabbdadcdacdcdbaadbcabdcdcaaacbcadccbbddbaddcaddcaadcbbcbbdcbdadcddabdddcdbddbbdabaaddcaadd";

//        String str = "abc1234321ab";
        LongestPalindromeSubstring longestPalindromeSubstring = new LongestPalindromeSubstring();
        System.out.println(longestPalindromeSubstring.getLongestPalindrome(str));
//        System.out.println(new StringBuilder(str).reverse());
    }
}
