package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    /**
     * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，
     * 找出candidates中可以使数字和为目标数target 的 所有不同组合，并以列表形式返回。
     * 你可以按 任意顺序 返回这些组合。
     *
     * candidates 中的 同一个 数字可以 无限制重复被选取。
     * 如果至少一个数字的被选数量不同，则两种组合是不同的。
     *
     * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSum(candidates, target, 0, new ArrayList<>(), 0);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    /**
     * 如果是[一个集合]来[求组合]的话，就需要startIndex，
     * 如果是[多个集合]来求组合，就不需要startIndex
     * */
    public void combinationSum(int[] candidates, int target, int startIndex, List<Integer> path, int sumPath) {
        if (sumPath > target) {
            return;
        }
        if (sumPath == target) {
            result.add(new ArrayList(path));
            return;
        }

        // 循环的终止条件，加了 sumPath + candidates[i] <= target，
        // 用来剪枝
        for (int i = startIndex; i < candidates.length; i++) {
            sumPath += candidates[i];
            path.add(candidates[i]);
            // 关键点:不用i+1了，用i表示可以重复读取当前的数
            combinationSum(candidates, target, i, path, sumPath);
            sumPath -= candidates[i];
            path.remove(path.size() - 1);
        }
    }
}
