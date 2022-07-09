package special.doubleptr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSumOfArray {

    /**
     * 本题目，依旧是打印满足四数之和为特定值的不重复数组
     *
     * 四数之和的双指针解法是两层for循环nums[k] + nums[i]为确定值，
     * 依然是循环内有left和right下表作为双指针，找出nums[k] + nums[i] + nums[left] + nums[right] == target的情况，
     * 三数之和的时间复杂度是O(n^2)，
     * 四数之和的时间复杂度是O(n^3) 。
     *
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            for (int j = i+1; j< nums.length; j++) {
                if (j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }
                int left = j + 1, right = nums.length - 1;
                while (left < right) {

                    if (nums[i] + nums[j] + nums[left] + nums[right] > target) {
                        right--;
                    } else if (nums[i] + nums[j] + nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        List<Integer> currentIndex = new ArrayList<>();
                        currentIndex.add(nums[i]);
                        currentIndex.add(nums[j]);
                        currentIndex.add(nums[left]);
                        currentIndex.add(nums[right]);
                        result.add(currentIndex);
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[]nums = {1,0,-1,0,-2,2};
        int target  =0;
//        int[]nums = {2,2,2,2,2};
//        int target = 8;
        FourSumOfArray fourSumOfArray = new FourSumOfArray();
        System.out.println(fourSumOfArray.fourSum(nums, target));
    }
}
