package hashtable;

import java.util.*;

public class FourSumOfArray {

//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        Arrays.sort(nums);
//        System.out.println(Arrays.toString(nums));
//
//        HashMap<Integer, List<List<Integer>>> dataMap = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j< nums.length; j++) {
//                int tempValue = nums[i] + nums[j];
//                List<Integer> index = new ArrayList<>();
//                index.add(i);
//                index.add(j);
//
//                if (dataMap.containsKey(tempValue)) {
//                    dataMap.get(tempValue).add(index);
//                } else {
//                    List<List<Integer>> allIndex = new ArrayList<>();
//                    allIndex.add(index);
//                    dataMap.put(tempValue, allIndex);
//                }
//            }
//        }
//
//        System.out.println(dataMap);
//
//        List<List<Integer>> result = new ArrayList<>();
//
//        for (Map.Entry<Integer, List<List<Integer>>> entry : dataMap.entrySet()) {
//            if (entry.getKey() > target)
//                continue;
//            for (List<Integer> list : entry.getValue()) {
//                int left = list.get(1) + 1, right = nums.length - 1;
//                while (left < right) {
//
//                    if (nums[left] == nums[left - 1]) {
//                        left++;
//                        continue;
//                    }
//                    if (entry.getKey() + nums[left] + nums[right] > target) {
//                        right--;
//                    } else if (entry.getKey() + nums[left] + nums[right] < target) {
//                        left++;
//                    } else {
//                        List<Integer> currentIndex = new ArrayList<>();
//                        currentIndex.add(nums[list.get(0)]);
//                        currentIndex.add(nums[list.get(1)]);
//                        currentIndex.add(nums[left]);
//                        currentIndex.add(nums[right]);
//                        result.add(currentIndex);
//                        while (left < right && nums[left] == nums[left + 1])
//                            left++;
//                        while (left < right && nums[right] == nums[right - 1])
//                            right--;
//                        left++;
//                        right--;
//                    }
//                }
//            }
//        }
//        return result;
//    }


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
