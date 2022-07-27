package com.leetcode.greedy_algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MaximumSumOfArrayAfterKNegations {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/
     *
     * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
     *
     * 以这种方式修改数组后，返回数组可能的最大和。
     *
     * 示例 1：
     *
     * 输入：A = [4,2,3], K = 1
     * 输出：5
     * 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
     * 示例 2：
     *
     * 输入：A = [3,-1,0,2], K = 3
     * 输出：6
     * 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
     * 示例 3：
     *
     * 输入：A = [2,-3,-1,5,-4], K = 2
     * 输出：13
     * 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
     * 提示：
     *
     * 1 <= A.length <= 10000
     * 1 <= K <= 10000
     * -100 <= A[i] <= 100
     */
    // 局部最优：每次只选最小的元素进行取反，
    // 这样累加的和，一定是最大的

//    1 2    -1  1
    public int largestSumAfterKNegations(int[] nums, int k) {
        /**
         * 保持这个顺序就返回-1，
         * 交换顺序就返回1
         * 什么都不做就返回0；
         * 升序（默认，即官方定义，毕竟代码实现就是基于这个写的）：
         *
         * 所以 升序的话 如果1<2,返回-1,保持顺序[1,2],如果3>2,返回-1,交换顺序[2,3]
         * **/

        // 将数组按照绝对值大小从大到小排序
        nums = IntStream.of(nums)
                .boxed()
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        System.out.println("o1 = " + o1);
                        System.out.println("o2 = " + o2);
                        return Math.abs(o2) - Math.abs(o1);
                    }
                })
                .mapToInt(Integer::intValue)
                .toArray();

        System.out.println("排序后的数据：");
        System.out.println(Arrays.toString(nums));

        for (int i  = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        if (k % 2 == 1) {
            nums[nums.length - 1] = -nums[nums.length - 1];
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int [] nums = {-4, -2, -3};   //   -4 -3 -2
        int k = 4;

        // sort是按照升序排列的，
        // 我希望的是绝对值大的在前面，绝对值小的在后面。
        // 所以，当o1的绝对值大于o2的绝对值时，这个就是我希望的顺序了，所以要让compare方法在此种场景返回-1
        // -4, -2, -3
        // o2   o1   ====>     if (Math.abs(o1) - Math.abs(o2) > 0 ? 1 : -1)
        int [] num2 = IntStream.of(nums).boxed().sorted(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
//                        return Math.abs(o1) - Math.abs(o2);
//                        如果O1的绝对值大， O2的绝对值小;
                        // Math.abs(o1) - Math.abs(o2) > 1,则交换o1 和 o2的顺序
                        // 2 -4
                        System.out.println("o1 = " + o1);
                        System.out.println("o2 = " + o2);
                        return Math.abs(o1) - Math.abs(o2) >= 0 ? -1 : 1;
                    }
                }
        ).mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(num2));

//        MaximumSumOfArrayAfterKNegations mm = new MaximumSumOfArrayAfterKNegations();
//        System.out.println(mm.largestSumAfterKNegations(nums, k));

    }
}
