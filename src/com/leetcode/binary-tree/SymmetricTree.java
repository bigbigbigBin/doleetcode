import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class SymmetricTree {

    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     *
     */

    // 空间复杂度高，申请了2个栈
    public boolean isSymmetric1(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        if (root!= null) {
            stack1.add(root);
            stack2.add(root);
        }

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int size = stack1.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur1 = stack1.pop();
                TreeNode cur2 = stack2.pop();
                if (cur1 == null && cur2 != null) {
                    return false;
                }
                if (cur1 != null && cur2 == null) {
                    return false;
                }
                if (cur1 == null && cur2 == null) {
                    continue;
                }
                if (cur1.val != cur2.val) {
                    return false;
                }

                // 如果是cur1.left 或者 cur1.right为null，也添加到栈中去，这样会在迭代终止条件中触发终止
                // 放入null，是为了占住空间，让单个非空的左或者右能够对应上位置
                stack1.add(cur1.left);
                stack1.add(cur1.right);

                stack2.add(cur2.right);
                stack2.add(cur2.left);
            }
        }
        return true;
    }



    public boolean isSymmetric2(TreeNode root) {
        return isSymmetric2(root.left, root.right);
    }
    // 递归法  空间复杂度优了一些
    private boolean isSymmetric2(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right != null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            return isSymmetric2(left.left, right.right) && isSymmetric2(left.right, right.left);
        }
    }


    public boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null) {
            deque.add(root.left);
            deque.add(root.right);
        }

        while (!deque.isEmpty()) {
            int size = deque.size();
            if (size % 2 != 0)
                return false;
            for (int i = 0; i < size/2; i++) {
                TreeNode left = deque.pollFirst();
                TreeNode right = deque.pollFirst();

                if (left == null && right == null) {
                    continue;
                } else if (left != null && right == null) {
                    return false;
                } else if (left == null && right != null) {
                    return false;
                } else if (left.val != right.val) {
                    return false;
                }

                // 如果使用ArrayDeque, 插入null会报异常
                deque.addLast(left.left);
                deque.addLast(right.right);
                deque.addLast(left.right);
                deque.addLast(right.left);
            }
        }
        return true;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
