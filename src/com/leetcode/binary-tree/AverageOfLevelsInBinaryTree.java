import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {

    /**
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[3.00000,14.50000,11.00000]
     * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
     * 因此返回 [3, 14.5, 11] 。
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Double> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            long sum = 0;
            int size = queue.size();
            for (int i = 0 ; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;

                if (cur.left!=null) {
                    queue.add(cur.left);
                }
                if (cur.right!=null) {
                    queue.add(cur.right);
                }
            }
            result.add((double)sum / size);
        }
        return result;
    }

}
