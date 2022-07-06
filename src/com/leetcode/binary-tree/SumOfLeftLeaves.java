import java.util.LinkedList;
import java.util.Queue;

public class SumOfLeftLeaves {

    /**
     * 求左叶子节点之和
     */
    // 什么是 左叶子。
    // 节点！=null  && 节点.left != null && 节点.left.left == null && 节点.left.right == null
    public int sumOfLeftLeaves(TreeNode root) {
        return traversal(root);
    }

    public int traversal(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum = root.left.val;
        }

        return sum+ traversal(root.left) + traversal(root.right);
    }






    // 特例，取巧找的规律
    public int sumOfLeftLeaves2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }

        if (root!= null && root.left == null && root.right == null) {
            return 0;
        }
        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i % 2 == 0 && cur != null && cur.left == null && cur.right == null) {
                    sum += cur.val;
                }
                if (cur != null) {
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
            }
        }
        return sum;
    }




    public static void main(String[] args) {
        SumOfLeftLeaves ss  = new SumOfLeftLeaves();
        TreeNode node = new TreeNode(1);
        ss.sumOfLeftLeaves(node);
    }



}
