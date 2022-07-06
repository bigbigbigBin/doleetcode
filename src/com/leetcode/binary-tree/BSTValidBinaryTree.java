import java.lang.reflect.InvocationTargetException;

public class BSTValidBinaryTree {

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */

    Long preVal = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {

        return preOrderTraversal(root);
    }

    // preVal 是我定义的前置节点，要求前置节点要一直小于新的root节点
    public boolean preOrderTraversal(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 左
        boolean leftIsValid = preOrderTraversal(root.left);
        if (!leftIsValid) {
            return false;
        }

        // 跟节点
        if (preVal >= root.val) {
            return false;
        }
        preVal = Long.valueOf(root.val);

        // 右
        boolean rightIsValid = preOrderTraversal(root.right);
        return rightIsValid;
    }

    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Integer[] array = {1,1};
        TreeNode root = ConstructBinaryTreeOfLevelOrder.constructTree(array, TreeNode.class);

        BSTValidBinaryTree bb = new BSTValidBinaryTree();
        bb.isValidBST(root);

    }

}
