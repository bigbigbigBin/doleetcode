package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsOfRepeatableNums {

    // 有重复数字的全排列

    public List<List<Integer>> permuteUnique(int[] nums) {
        // todo 去重, 一定要对元素进行排序，这样我们才方便通过相邻的节点来判断是否重复使用了。
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backTracing(nums, used, new ArrayList<>());
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();


    public void backTracing(int[] nums, boolean[] used, List<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            }

            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
            } else {
                continue;
            }

            backTracing(nums, used, path);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

}
