package special.doubleptr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    /**
     * 本题目仅要求打印不重复的数组，而不是下标，所以使用双指针合适。
     * 在排序之后的数组上，使用双指针。
     *
     * 三数之和的双指针解法是一层for循环num[i]为确定值，
     * 然后循环内有left和right下表作为双指针，找到nums[i] + nums[left] + nums[right] == 0。
     * 时间复杂度为O(n^2)
     *
     * 如果是打印下边，则不能使用，因为为了去重而采用的重排序，会打乱数组的下标。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        /** 先要排好序, 便于left、right 遇到相同元素时，可以方便移动 */
        /** 大致思想： 固定i， 然后使用两个指针，left、right, 判断这三者之和    */
        for (int i = 0; i < nums.length; i++) {
            /** i是第一个数,
             *  left 是第二个数(也叫左指针)，
             *  right是第三个数(也叫右子针)
             *  */
            int left = i + 1, right = nums.length - 1;

            // 排序后，相同元素都聚在一起，所以遇到相同元素的，可以跨过。这样是去重
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    // 三数之和大于0，右指针左移
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    // 三数之和小于0，左指针右移
                    left++;
                } else { // 三数之和等于0
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
