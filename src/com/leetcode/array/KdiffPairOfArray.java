package array;

import java.util.Arrays;

public class KdiffPairOfArray {
    /**
     * https://leetcode.cn/problems/k-diff-pairs-in-an-array/
     *
     * 给你一个整数数组nums 和一个整数k，请你在数组中找出 不同的k-diff 数对，并返回不同的 k-diff 数对 的数目。
     *
     * k-diff数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
     *
     * 0 <= i, j < nums.length
     * i != j
     * nums[i] - nums[j] == k
     * 注意，|val| 表示 val 的绝对值。
     *
     */

    public int findPairs(int[] nums, int k) {
        // 双指针
        Arrays.sort(nums);
        int i = 0;
        int count = 0;
        for (int j = 1; j < nums.length; ) {
            if (j == i) {
                j++;
                continue;
            }
            int diff = nums[j] - nums[i];
            if (diff == k) {
                count++;
                i++;
                j++;
                while(i > 0 && i < j && nums[i] == nums[i-1]) {
                    i++;
                }
                while(j < nums.length && nums[j] == nums[j-1]) {
                    j++;
                }
            } else if (diff < k) {
                j++;
            } else {
                i++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {6,7,3,6,4,6,3,5,6,9};
        int k = 4;
        KdiffPairOfArray kk = new KdiffPairOfArray();
        kk.findPairs(nums, k);
    }
}
