public class BSTConvertBSTToGreaterTree {

    /**
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，
     * 请你将其转换为累加树（Greater Sum Tree），
     * 使每个节点 node的新值等于原树中大于或等于node.val的值之和。
     *
     * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     *
     */

    // 分析题目，node的新值，等于树种节点值 >= 此node对应val的所有节点的val值之和
    // 所以可以从最右下方开始算起，
    // 因为最右下方是树中最大的值，然后按照【右、根、左】的顺序遍历然后更新每个节点的val。
    // 也叫反中序遍历

    TreeNode afterNode = null; // 后指针节点
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        convertBST(root.right);
        if (afterNode == null) {
            // 说明是最后最右节点，此节点不动，val还是原值;
            // 但是after
            afterNode = root;
        } else {
            root.val += afterNode.val;
            afterNode = root;
        }
        convertBST(root.left);
        return root;
    }
}
