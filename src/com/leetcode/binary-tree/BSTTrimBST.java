import java.lang.reflect.InvocationTargetException;

public class BSTTrimBST {

    /**
     * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。
     * 通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
     * 修剪树 不应该改变保留在树中的元素的相对结构
     * (即，如果没有被移除，原有的父代子代关系都应当保留)。
     * 可以证明，存在唯一的答案。
     *
     * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
     *
     * 输入：root = [1,0,2], low = 1, high = 2
     * 输出：[1,null,2]
     *
     * 输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
     * 输出：[3,2,null,1]
     */

    public TreeNode trimBST(TreeNode root, int low, int high) {
        // 递归终止条件
        // 1、小于low的节点或者大于high的节点，没有找到，那么直接返回null
        if (root == null) {
            return null;
        }

        // 2、小于low的节点或者大于high的节点是叶子节点，则直接返回null，让上层递归获取处理
        if ((root.val < low || root.val > high) && root.left == null && root.right == null) {
            return null;
        }

        // 3、小于low的节点，是非叶子节点，由于二叉搜索树的特性，左子树也必定小于low，所以直接舍弃不要
        if (root.val < low && root.right != null) {
            return trimBST(root.right, low, high);
        } else if (root.val < low && root.right == null) {
            return null;
        }

        // 大于high的节点，是非叶子节点，由于二叉搜索树的特性，右子树也必定大于high，所以直接舍弃不要
        if (root.val > high && root.left != null) {
            return trimBST(root.left, low, high);
        } else if (root.val > high && root.left == null) {
            return null;
        }

        // 4、处于low high之间的节点，切实叶子节点，直接返回
        if (low <= root.val && root.val <= high && root.right == null && root.left == null) {
            return root;
        }

        // 剩下的便是 处于low high之间的节点，正常判断子树
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }


    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Integer[] array = {3,2,4,1, null};
        int low = 3;
        int high = 4;
        TreeNode root = ConstructBinaryTreeOfLevelOrder.constructTree(array, TreeNode.class);
        BSTTrimBST bb = new BSTTrimBST();
        bb.trimBST(root, low, high);

    }


}
