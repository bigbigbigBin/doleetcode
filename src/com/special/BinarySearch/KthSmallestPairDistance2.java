package special.BinarySearch;

import java.util.Arrays;

public class KthSmallestPairDistance2 {
    /**
     * https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
     *
     * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
     *
     * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
     *
     * 示例一：
     * 输入：nums = [1,3,1], k = 1
     * 输出：0
     * 解释：数对和对应的距离如下：
     * (1,3) -> 2
     * (1,1) -> 0
     * (3,1) -> 2
     * 距离第 1 小的数对是 (1,1) ，距离为 0 。
     *
     * */

    // 本题目同 KthSmallestPairDistance
    // 只是解法稍稍有一点点不一样

    public static void main(String[] args) {
//        int[] nums = {1,6,1};
//        int k = 3;
//        int[] nums = {1,1,1};
//        int k = 2;

        int[] nums = {1,3,1};
        int k = 1;

        KthSmallestPairDistance2 kk = new KthSmallestPairDistance2();
        System.out.println(kk.smallestDistancePair2(nums, k));

    }

    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, left = 0, right = nums[n - 1] - nums[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                int i = binarySearch(nums, j, nums[j] - mid);
                cnt += j - i;
            }
            if (cnt >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int binarySearch(int[] nums, int end, int target) {
        int left = 0, right = end;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
