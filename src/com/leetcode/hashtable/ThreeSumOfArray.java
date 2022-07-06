package hashtable;

import java.util.*;

public class ThreeSumOfArray {

    /**
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，
     *    使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     */

    // 使用哈希表，来判断是否存在存在两数之和 + 第三个数是否为0，只是判断的话，可行。
    // 但是要求不能出现重复的三元组，处理起来很麻烦。

    // 可以在排序之后的数组上，使用双指针。由于本题目仅要求打印数组，而不是下标，所以使用双指针合适。
    // 如果是打印下标，那么双指针不合适，因为双指针是要求数组有序，数组重排序会打乱下标的位置。
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        /** 先要排好序, 便于left、right 遇到相同元素时，可以方便移动 */
        /** 大致思想： 固定i， 然后使用两个指针，left、right, 判断这三者之和    */
        for (int i = 0; i < nums.length; i++) {
            /** i是以一个数,
             *  left 是第二个数(也叫左指针)，
             *  right是第三个数(也叫右子针)
             *  */
            int left = i + 1, right = nums.length - 1;

            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    List<Integer> tempRes = new ArrayList<>();
                    tempRes.add(nums[i]);
                    tempRes.add(nums[left]);
                    tempRes.add(nums[right]);
                    // 跨过相同的元素
                    while(left < right && nums[left] == nums[left+1]) {
                        left++;
                    }
                    // 跨过相同的元素
                    while(left < right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    right--;
                    left++;
                    result.add(tempRes);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
//        int []nums = {-1,0,1,2,-1,-4};
//        int []nums = {-2,0,0,2,2};
        int []nums = {0,0,0,0,0};
        ThreeSumOfArray threeSumOfArray = new ThreeSumOfArray();
        System.out.println(threeSumOfArray.threeSum(nums));
    }
}
