import java.util.Arrays;

public class ConstructTreeFromPreOrderAndInOrder {

    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * 注意: 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7] 中序遍历 inorder = [9,3,15,20,7] 返回如下的二叉树：
     * */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 如果前序数组长度为null，说明这个是个空节点
        if (preorder.length == 0) {
            return null;
        }

        // 找出根节点，在前序数组的第一个位置
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = 0;
        for (; rootIndex < inorder.length; rootIndex++) {
            if (inorder[rootIndex] == rootVal)
                break;
        }

        System.out.println("rootIndex = " + rootIndex);

        // 找出中序数组，被根节点，切分出来的左右子树，所对应的中序数组
        int[] leftInOrder = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] rightInOrder = Arrays.copyOfRange(inorder, rootIndex+1, inorder.length);
        System.out.println(Arrays.toString(leftInOrder));
        System.out.println(Arrays.toString(rightInOrder));


        int[] leftPreOrder = Arrays.copyOfRange(preorder, 1, 1 + leftInOrder.length);
        int[] rightPreOrder = Arrays.copyOfRange(preorder, 1 + leftInOrder.length, preorder.length);
        System.out.println(Arrays.toString(leftPreOrder));
        System.out.println(Arrays.toString(rightPreOrder));


        root.left = buildTree(leftPreOrder, leftInOrder);
        root.right = buildTree(rightPreOrder, rightInOrder);
        return root;
    }

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] preorder = {3,9,20,15,7};
//        int[] inorder = {2,1};
//        int[] postorder = {2,1};

        ConstructTreeFromPreOrderAndInOrder cc = new ConstructTreeFromPreOrderAndInOrder();
        cc.buildTree(preorder, inorder);
    }
}
