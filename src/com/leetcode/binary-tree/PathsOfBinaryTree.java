import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathsOfBinaryTree {

    /**
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     *
     * 输入：root = [1,2,3,null,5]
     * 输出：["1->2->5","1->3"]
     */

    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        List<String> result = new ArrayList<>();

        preOrderTraversal(root, path, result);
        return result;
    }

    // 回溯
    // 回溯和递归是一一对应的，有一个递归，就要有一个回溯，
    // 所以回溯要和递归永远在一起，世界上最遥远的距离是你在花括号里，而我在花括号外！
    void preOrderTraversal(TreeNode root, List<Integer> path, List<String> result) {
        path.add(root.val);
        /**
         * 为什么不在此处写上终止条件为 root = null
         * 如果在此处写上的话，那么久会出现，当root = null时，也会进入到递归函数中，
         * 但是，当触发终止条件时，却又不回溯。
         * 这个和有一个递归就要有一个回溯相违背。
         */

        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            result.add(sb.toString());
            return;
        }

        if (root.left != null) {
            preOrderTraversal(root.left, path, result);
            // 删除最后一个
            path.remove(path.size() - 1);// 回溯,回溯和递归是一一对应的，有一个递归，就要有一个回溯
        }

        if (root.right != null) {
            preOrderTraversal(root.right, path, result);
            // 删除最后一个
            path.remove(path.size() - 1);// 回溯,回溯和递归是一一对应的，有一个递归，就要有一个回溯
        }
    }


    public List<String> binaryTreePaths2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<String> pathStack = new Stack<>();  // 用一个额外的栈，专门存放路径
        if (root != null) {
            stack.add(root);
            pathStack.add(String.valueOf(root.val));
        }

        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();

            if (cur.right == null && cur.left == null) {
                result.add(pathStack.pop());
                continue;
            }

            String curPath = pathStack.peek();
            pathStack.pop();
            if (cur.right != null) {
                stack.add(cur.right);
                pathStack.push( curPath + "->" + cur.right.val);
            }

            if (cur.left != null) {
                stack.add(cur.left);
                pathStack.push(curPath + "->" + cur.left.val);
            }
        }
        return result;
    }



    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        PathsOfBinaryTree pth = new PathsOfBinaryTree();
//        Integer[] array = {1,2,3,6,5};
        Integer[] array = {1,2,3,null,5};
        TreeNode root = ConstructBinaryTreeOfLevelOrder.constructTree(array, TreeNode.class);
        System.out.println(pth.binaryTreePaths2(root));
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
