import java.util.*;

public class FindBottomLeftTreeNodeValue {

    /**
     * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     *
     * 假设二叉树中至少有一个节点。
     *
     */

    // 本题目想的复杂了，最后一层，最左边的节点。
    // 不必关心最左边的这个节点是，左叶子节点还是右叶子节点
    public int findBottomLeftValue2(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0)
                    result = cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.addLast(cur.right);
                }
            }
        }
        return result;
    }




    int maxLeafVal = 0;
    int maxDepth = 0;
    public void preTraversal(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            depth++;
            if (maxDepth < depth) {
                maxDepth = depth;
                maxLeafVal = root.val;
            }
        }

        if (root.left!=null) {
            depth++;
            preTraversal(root.left, depth);
            depth--; // 回溯
        }

        if (root.right != null) {
            depth++;
            preTraversal(root.right,depth);
            depth--; // 回溯
        }
    }

    public int findBottomLeftValue(TreeNode root) {
        preTraversal(root, 0);
        return maxLeafVal;
    }


}
