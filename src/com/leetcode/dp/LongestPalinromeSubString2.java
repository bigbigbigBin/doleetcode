package dp;

import java.util.Arrays;

public class LongestPalinromeSubString2 {

    public String longestPalindrome(String s) {
        // dp[i][j] 表示从i--j是所构成的回文串的长度
        // dp[i][j] = dp[i+1][j-1] + 2   if s(i) == s(j)
        //          = 1

        int [][] dp = new int[s.length()][s.length()];
        int maxLength = 0;
        int index = 0;

        for (int i = s.length() -1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && i == j) {
                    dp[i][j] = 1;
                } else if (s.charAt(i) == s.charAt(j) && j == i + 1) {
                    dp[i][j] = 2;
                } else if (s.charAt(i) == s.charAt(j) && j == i + 2) {
                    dp[i][j] = 3;
                } else if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1] >= 2) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = 1;
                }
                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j];
                    index = i;
                }
            }
//            System.out.println(Arrays.toString(dp[i]));
        }

//        System.out.println("==============");
//        for (int i = 0; i < s.length(); i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        return s.substring(index, index + maxLength);
    }

    public static void main(String[] args) {
        String aa = "aacabdkacaa";
        LongestPalinromeSubString2 ll = new LongestPalinromeSubString2();
        System.out.println(ll.longestPalindrome(aa));
    }
}
