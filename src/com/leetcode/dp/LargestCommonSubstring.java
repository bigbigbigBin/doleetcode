package dp;

public class LargestCommonSubstring {

    /**
     * 描述
     * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
     * 题目保证str1和str2的最长公共子串存在且唯一。
     *
     * 数据范围： 1 <= |str1|,|str2| ≤ 5000
     * 要求： 空间复杂度 O(n^2)，时间复杂度 O(n^2)
     *
     * 示例1
     * 输入：  "1AB2345CD","12345EF"
     * 返回值："2345"
     */
    public String LCS (String str1, String str2) {
        //分布决策、分布存储、递推存储
        // ,假设最长子序列的长度为f(n)
        // ,f(n) = f(n-1) + 1     str1的下标n = str2的下标n
        // ,f(n) = 0              str1的下标n != str2的下标n
        int [][]dp = new int[str1.length()+1][str2.length()+1];
        /**
         * 当时递归方程自己是想出来了，但是没明白怎么去把dp表和最终需要的字符串联系起来
         * 最初的疑问是，dp表存的是最值，但不能去存储字符串，那怎么办呢？
         *
         * 借助题解，发现我就利用dp找到最长子序列的长度 & 下标，
         * 利用下标-最长子序列的长度，就可得到初始节点，这样就能够找到子序列
         */
        int maxLength = 0;
        int maxLengthSubstrIndex = 0;

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] +1;
                    if (dp[i+1][j+1] > maxLength) {
                        maxLength = dp[i+1][j+1];
                        maxLengthSubstrIndex = j+1;
                    }
                } else {
                    dp[i+1][j+1] = 0;
                }
            }
        }
        return str2.substring(maxLengthSubstrIndex - maxLength, maxLengthSubstrIndex);
    }

    public static void main(String[] args) {
//        String str1 =  "1AB2345CD" , str2 =  "12345EF";
        String str1 =  "1AB" , str2 =  "123";
        LargestCommonSubstring largestCommonSubstring = new LargestCommonSubstring();
        System.out.println(largestCommonSubstring.LCS(str1, str2));
    }
}
