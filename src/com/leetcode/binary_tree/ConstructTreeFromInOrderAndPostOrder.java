package com.leetcode.binary_tree;

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


    /**
     * 优化点：分割数组，我是使用了copy函数，效率很低
     * 直接修改递归函数，将中序数组&后序数组的起始下标都传进去。这样方法内就不需要copy分割了
     * buildTree(int[] inorder, int ileft, int iright, int[] postorder, int pleft, int pright) {
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 第一步：判断递归的终止条件： 如果数组大小为零的话，说明是空节点了。
        if (postorder.length == 0) {
            return null;
        }

        // 第二步：找出每次递归的根节点： 后序遍历数组的最后一个节点，就是根节点
        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        if (postorder.length == 1) {
            return root;
        }

        // 第三步：找到切割点：  根节点，在中序数组中的位置
        int rootIndex = 0;
        for (; rootIndex < inorder.length; rootIndex++) {
            if (inorder[rootIndex] == rootVal)
                break;
        }


        // 第四步： 切割中序数组，分为中序做数组、中序右数组
        // 注意：第四步和第五步，严格有序，第五步切割后序数组，要用到中序子数组的长度作为参考
        int[] leftInOrder = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] rightInOrder = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);

//        System.out.println(Arrays.toString(leftInOrder));
//        System.out.println(Arrays.toString(rightInOrder));

        // 第五步：切割后序数组，分为后续左数组、后续右数组；
        // 后续数组不是能直接用根节点切分出来，所以使用【中序数组的长度】来辅助找出左、右子树的后序数组
        int[] leftPostOrder = Arrays.copyOfRange(postorder, 0, leftInOrder.length);
        int[] rightPostOrder = Arrays.copyOfRange(postorder, leftInOrder.length, postorder.length - 1);


//        System.out.println(Arrays.toString(leftPostOrder));
//        System.out.println(Arrays.toString(rightPostOrder));

        // 第六步：递归处理左子数组 、右子数组。
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
