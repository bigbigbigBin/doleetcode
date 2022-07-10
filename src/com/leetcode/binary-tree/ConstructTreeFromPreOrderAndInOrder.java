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
        // 第一步：递归终止条件：   如果前序数组长度为null，说明这个是个空节点
        if (preorder.length == 0) {
            return null;
        }

        // 第二步：从前序数组中找出根节点：  找出根节点，在前序数组的第一个位置
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);

        // 第三步：用根节点，在中序数组中找到分割点。
        int rootIndex = 0;
        for (; rootIndex < inorder.length; rootIndex++) {
            if (inorder[rootIndex] == rootVal)
                break;
        }

        // System.out.println("rootIndex = " + rootIndex);

        // 第四步：用分割点切割中序数组，切分出来左、右子树所对应的中序子数组
        int[] leftInOrder = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] rightInOrder = Arrays.copyOfRange(inorder, rootIndex+1, inorder.length);
//        System.out.println(Arrays.toString(leftInOrder));
//        System.out.println(Arrays.toString(rightInOrder));

        // 第五步：借助左、右中序子数组的长度，切分出来左、右子树所对应的前序子数组
        int[] leftPreOrder = Arrays.copyOfRange(preorder, 1, 1 + leftInOrder.length);
        int[] rightPreOrder = Arrays.copyOfRange(preorder, 1 + leftInOrder.length, preorder.length);
//        System.out.println(Arrays.toString(leftPreOrder));
//        System.out.println(Arrays.toString(rightPreOrder));

        // 第六步：递归构建左右子树
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
