import java.util.*;

public class SymmetricTree {

    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     * https://leetcode.cn/problems/symmetric-tree/
     *
     */

    public boolean isSymmetric2(TreeNode root) {
        return isSymmetric2(root.left, root.right);
    }
    // 方法一：递归法  空间复杂度优了一些
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

    // // 方法二：使用队列。  本质是通过一个容器，来成对的存 要比较的数据
    // public boolean isSymmetric(TreeNode root) {
    //     if (root == null) {
    //         return true;
    //     }
    //     Queue<TreeNode> queue = new LinkedList<>();
    //     // 成对存放
    //     queue.add(root.left);
    //     queue.add(root.right);

    //     while(!queue.isEmpty()) {
    //         TreeNode left = queue.poll();
    //         TreeNode right = queue.poll();

    //         if (left != null && right == null) {
    //             return false;
    //         } else if (left == null && right != null) {
    //             return false;
    //         } else if (left == null && right == null) {
    //             continue;
    //         } else if (left.val != right.val) {
    //             return false;
    //         }

    //         queue.add(left.left);
    //         queue.add(right.right);
    //         queue.add(left.right);
    //         queue.add(right.left);
    //     }
    //     return true;
    // }

    // // 方法三：既然本质是通过一个容器，来成对的存 要比较的数据
    // // 那么使用栈应该也是可行的。
    // public boolean isSymmetric(TreeNode root) {
    //     if (root == null) {
    //         return true;
    //     }
    //     Stack<TreeNode> stack = new Stack<>();
    //     // 成对存放
    //     stack.add(root.left);
    //     stack.add(root.right);

    //     while(!stack.isEmpty()) {
    //         TreeNode left = stack.pop();
    //         TreeNode right = stack.pop();

    //         if (left != null && right == null) {
    //             return false;
    //         } else if (left == null && right != null) {
    //             return false;
    //         } else if (left == null && right == null) {
    //             continue;
    //         } else if (left.val != right.val) {
    //             return false;
    //         }

    //         stack.push(left.left);
    //         stack.push(right.right);
    //         stack.push(left.right);
    //         stack.push(right.left);
    //     }
    //     return true;
    // }

    // 方法四：既然本质是通过一个容器，来成对的存 要比较的数据
    // 使用数组，当做这个容器也是可行的
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<TreeNode> array = new ArrayList<>();

        // 成对存放
        array.add(root.left);
        array.add(root.right);

        int cur = 0;
        while(cur < array.size()) {
            TreeNode left = array.get(cur++);
            TreeNode right = array.get(cur++);

            if (left != null && right == null) {
                return false;
            } else if (left == null && right != null) {
                return false;
            } else if (left == null && right == null) {
                continue;
            } else if (left.val != right.val) {
                return false;
            }

            array.add(left.left);
            array.add(right.right);
            array.add(left.right);
            array.add(right.left);
        }

        return true;
    }


}
