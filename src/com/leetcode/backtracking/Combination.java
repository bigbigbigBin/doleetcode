package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     *
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     */

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> curList = new ArrayList<>();
        traversal(1, n, k, curList);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    public void traversal(int start, int end, int k, List<Integer> curList) {
        // 回溯函数（递归函数）的终止条件
        if (k == 0) {
            result.add(new ArrayList<>(curList));
            return;
        }

        for (int i = start; i <= end; i++) {
            curList.add(i);
            k--;
            traversal(i+1, end, k, curList); // 递归调用
            k++; // 回溯k
            curList.remove(curList.size() - 1); // 回溯
        }
    }

    public static void main(String[] args) {
        int n = 4, k = 2;
        Combination cc = new Combination();

        System.out.println(cc.combine(n, k));

    }


}
