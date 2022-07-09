package special.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumOfArray {
    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     *
     * 所以返回 [0, 1]
     */



    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> dataMap = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int needValue = target - nums[i];
            if (dataMap.containsKey(needValue)) {
                int valueIndex = dataMap.get(needValue);
                if (valueIndex == i) {
                    continue;
                } else {
                    result[0] = i;
                    result[1] = valueIndex;
                    break;
                }
            } else {
                dataMap.put(nums[i], i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int [] nums = {2,7,11,15};
//        int target = 9;
//        int [] nums = {3,3};
//        int target = 6;

        int [] nums = {3,2,4};
        int target = 6;

        TwoSumOfArray twoSumOfArray = new TwoSumOfArray();
        System.out.println(Arrays.toString(twoSumOfArray.twoSum(nums, target)));
    }
}
