package dp;

import java.util.Arrays;
import java.util.Collections;

public class MaxSumOfSubArray {

    /**
     * 描述
     * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
     * 数据范围:
     *      1<= n <= 2×10^5;
     *      -100 <= a[i] <= 100
     * 要求:时间复杂度为 O(n) ，空间复杂度为 O(n)
     * 进阶:时间复杂度为 O(n) ，空间复杂度为 O(1)
     *
     *
     * 示例1
     *  输入：[1,-2,3,10,-4,7,2,-5]
     *  返回值：18
     *  说明：经分析可知，输入数组的子数组[3,10,-4,7,2]可以求得最大和为18
     *
     * 示例2
     *  输入：[2]
     *  返回值：2
     *
     * 示例3
     *  输入：[-10]
     *  输出: 10
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        if (array.length == 1) {
            return array[0];
        }
        /**
         * 本实现，
         * 空间复杂度为O(n), 使用了一个额外的数组dp
         * 时间复杂度为O(n), 算法速度随着数组的大小N而线性变化
         */
        int []dp = new int[array.length];
        dp[0] = array[0];
        int maxSum = dp[0];
        for (int i = 1; i < array.length; i++) {
            if (dp[i-1] > 0) {
                dp[i] = dp[i-1] + array[i];
            } else {
                dp[i] = array[i];
            }
            if (maxSum < dp[i]) {
                maxSum = dp[i];
            }
        }
        return maxSum;
    }

    public int FindGreatestSumOfSubArray2(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        /**
         * 时间复杂度为O（1）
         */
        int currentSubArraySum = array[0];
        int maxSum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (currentSubArraySum > 0) {
                currentSubArraySum += array[i];
            } else {
                currentSubArraySum = array[i];
            }
            if (maxSum < currentSubArraySum) {
                maxSum = currentSubArraySum;
            }
        }
        return maxSum;
    }


    public static void main(String[] args) {
        int []array = {1,-2,3,10,-4,7,2,-5};
//        int []array = {-1,-2,-3,-4,5};
        MaxSumOfSubArray maxSumOfSubArray = new MaxSumOfSubArray();
        System.out.println(maxSumOfSubArray.FindGreatestSumOfSubArray2(array));
    }

}
