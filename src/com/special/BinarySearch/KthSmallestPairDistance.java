package special.BinarySearch;

import java.util.Arrays;

public class KthSmallestPairDistance {
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


    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        // 最小距离，最大距离
        int start = 0, end = nums[nums.length - 1] - nums[0];
        while(start <= end) {
            // 中间距离
            int mid = (start + end) / 2;
            // 有多少个数据，小于等于mid距离
            int count = check(nums, mid);
            if (count < k) { //  有count个数对，它所组成的数对之和小于
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    private int check(int[] nums, int value) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = 0;
            int r = i;
            while(l < r) {
                int mid = (l + r) / 2;
                if (nums[mid] < nums[i] - value) { // 当前节点和中间节点mid所组的数对，大小
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            count += (i - l);
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] nums = {1,6,1};
//        int k = 3;
//        int[] nums = {1,1,1};
//        int k = 2;

        int[] nums = {1,3,1};
        int k = 1;

        KthSmallestPairDistance kk = new KthSmallestPairDistance();
        System.out.println(kk.smallestDistancePair(nums, k));
    }

}
