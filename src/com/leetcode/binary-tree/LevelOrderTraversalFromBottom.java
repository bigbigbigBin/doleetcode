import java.util.*;

public class LevelOrderTraversalFromBottom {

    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。（即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[15,7],[9,20],[3]]
     *
     * 输入：root = [1]
     * 输出：[[1]]
     *
     * 输入：root = []
     * 输出：[]
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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        List<List<Integer>> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                curList.add(cur.val);
                if (cur.left !=null) {
                    queue.add(cur.left);
                }
                if (cur.right !=null) {
                    queue.add(cur.right);
                }
            }
            result.add(curList);
        }
        Collections.reverse(result);
        return result;
    }

}
