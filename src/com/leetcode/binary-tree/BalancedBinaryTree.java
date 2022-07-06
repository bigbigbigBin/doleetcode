import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BalancedBinaryTree {

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1 。
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     *
     * 输入：root = [1,2,2,3,3,null,null,4,4]
     * 输出：false
     *
     * 输入：root = []
     * 输出：true
     */

    //二叉树节点的深度：指从【根节点】到【该节点】的最长简单路径边的条数。
    //二叉树节点的高度：指从【该节点】到【叶子节点】的最长简单路径边的条数。
    // 关于根节点的深度究竟是1 还是 0，不同的地方有不一样的标准，
    // leetcode的题目中都是以节点为一度，即根节点深度是1。
    // 但维基百科上定义用边为一度，即根节点的深度是0，
    // 我们暂时以leetcode为准（毕竟要在这上面刷题）

    // 求深度，是从根节点开始，所以适合用前序遍历
    // 求高度，是从某节点到叶子节点的路径，所以适合后续遍历。


    public boolean isBalanced(TreeNode root) {
        return getDepth(root) != -1;
    }

    // 如果不是平衡二叉树，直接返回-1，不用返回真实长度。
    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = getDepth(root.left);
        if (leftDepth == -1) {
            return -1;
        }

        int rightDepth = getDepth(root.right);
        if (rightDepth == -1) {
            return -1;
        }

        if (Math.abs(leftDepth - rightDepth) >1) {
            return -1;
        } else {
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }


    // 迭代法
//    public boolean isBalanced2(TreeNode root) {
//        Stack<TreeNode> stack = new Stack<>();
//        if (root != null) {
//            stack.add(root);
//        }
//        while (!stack.isEmpty()) {
//            TreeNode cur = stack.pop();
//            if (Math.abs(getDeep(cur.left) - getDeep(cur.right)) > 1) {
//                return false;
//            } else {
//                if (cur.left != null)
//                    stack.add(cur.left);
//                if (cur.right != null)
//                    stack.add(cur.right);
//            }
//        }
//        return true;
//    }

//    // 最大深度，即为高度，所以使用了前序遍历
//    private int getDeep(TreeNode root) {
//        Queue<TreeNode> queue = new ArrayDeque<>();
//        if (root != null) {
//            queue.add(root);
//        }
//
//        int depth = 0;
//        while (!queue.isEmpty()) {
//            for (int i = 0 ; i < queue.size(); i++) {
//                TreeNode cur = queue.poll();
//                if (cur != null) {
//                    depth++;
//                    queue.add(cur.left);
//                    queue.add(cur.right);
//                }
//            }
//
//
//
//            if (cur == null) {
//
//            } else {
//                depth++;
//                queue.add(cur.right);
//                queue.add(cur.left);
//            }
//            depth = Math.max(depth, );
//        }
//        return depth;
//    }




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

}
