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
            if (count >= k) { //  有count个数对，它所组成的数对之和大于
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     * 求数组中，小于value的数对个数
     * @param nums
     * @param value
     * @return
     */
     private int check(int[] nums, int value) {
         int count = 0;
         for (int i = 0; i < nums.length; i++) {
             // 第一个能和i组成 数对距离小于等于value 、因为数组已经排好序，所以
             // nums[i] - nums[l] 一定是在满足小于value，同时距离又是最大
             int l = 0;
             int r = i;
             while(l < r) {
                 int mid = (l + r) / 2;
                 // 注意 mid 所处的区间为 0 <=（l --- r）<= i
                 if (nums[i]  - nums[mid] > value) {
                     // 当前最右节点和中间节点mid所组的数对，他们的大小，大于value
                     // 由于已经排好序，mid左边的数组与最右节点i，所组成的数对距离，一定更加大
                     // 则说明mid右边的区间，才有存在小于value的数对
                     // 由于while循环可以看出，定义的区间为左闭右开，说明mid位置不可能和做区间的元素构成数组使之差 = value
                     l = mid + 1;
                 } else {
                     // 当前最右节点和中间节点mid所组的数对，他们的大小，<= value
                     // 那么分开考虑 < 和 = 两种情况：
                     // 如果是 < value，那么mid左边区间，可能有数字，和最右边i位置的数字所组成的数对距离仍 < value
                     // 如果是 = value，那么mid左边区间，仍然可能有数字，和最右边i位置的数字所组成的数对距离 <= value
                     r = mid;
                 }
             }
             count += (i - l);
         }
         return count;
     }

    // 统计有多少个数对，，距离小于等于 value

    /**
     * 双指针法，统计有多少数对的距离，小于等于value
     * @param nums
     * @param value
     * @return
     */
    private int doublePointerCheckNum(int[] nums, int value) {
        int count = 0;
        int leftPointer = 0;
        for (int i = 0; i < nums.length; i++) {
            while(nums[i] - nums[leftPointer] > value) {
                leftPointer++;
            }
            count += i - leftPointer;
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
