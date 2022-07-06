import java.util.Arrays;

public class ConstructTreeFromInOrderAndPostOrder {

    /**
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     *
     * 注意: 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     */

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 如果数组大小为零的话，说明是空节点了。
        if (postorder.length == 0) {
            return null;
        }

        // 后序遍历数组的最后一个节点，就是根节点
        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        if (postorder.length == 1) {
            return root;
        }

        // 根节点，在中序数组中的位置
        int rootIndex = 0;
        for (; rootIndex < inorder.length; rootIndex++) {
            if (inorder[rootIndex] == rootVal)
                break;
        }

        // 求出被根节点切分出的左、右子树的中序数组。
        int[] leftInOrder = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] rightInOrder = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);

        System.out.println(Arrays.toString(leftInOrder));
        System.out.println(Arrays.toString(rightInOrder));

        // 求出后续数组中，左右子树的后续数组；
        // 后续数组不是能直接用根节点切分出来，所以使用【中序数组的长度】来辅助找出左、右子树的后序数组
        int[] leftPostOrder = Arrays.copyOfRange(postorder, 0, leftInOrder.length);
        int[] rightPostOrder = Arrays.copyOfRange(postorder, leftInOrder.length, postorder.length - 1);


        System.out.println(Arrays.toString(leftPostOrder));
        System.out.println(Arrays.toString(rightPostOrder));
        root.left = buildTree(leftInOrder, leftPostOrder);
        root.right = buildTree(rightInOrder, rightPostOrder);
        return root;
    }

    public static void main(String[] args) {
//        int[] inorder = {9,3,15,20,7};
//        int[] postorder = {9,15,7,20,3};
        int[] inorder = {2,1};
        int[] postorder = {2,1};

        ConstructTreeFromInOrderAndPostOrder cc = new ConstructTreeFromInOrderAndPostOrder();
        cc.buildTree(inorder, postorder);
//        System.out.println(Arrays.toString(newArray));
    }




}
