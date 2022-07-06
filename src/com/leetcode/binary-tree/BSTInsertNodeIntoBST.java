import java.util.Stack;

public class BSTInsertNodeIntoBST {

    /**
     * 给定二叉搜索树（BST）的根节点root和要插入树中的值value，将值插入二叉搜索树。
     * 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
     *
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。
     * 你可以返回 任意有效的结果 。
     *
     * 输入：root = [4,2,7,1,3], val = 5
     * 输出：[4,2,7,1,3,5]
     * 解释：另一个满足题目要求可以通过的树是：[5,2,7,1,3,null, null,null,null,null,4]
     *
     * 输入：root = [40,20,60,10,30,50,70], val = 25
     * 输出：[40,20,60,10,30,50,70,null,null,25]
     *
     * 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
     * 输出：[4,2,7,1,3,5]
     */

    /**
     * 一开始没做出来，因为没想好到底在什么位置插入新数据，要不要修改树的结构，也没有想明确。
     *
     * 题目给出了多种解决办法，没规定一定要用那种，我们就按照最简单的来，不去修改树的结构，只在原树上修改
     */
    // 不要想得复杂，直接不修改树结构，只在叶子节点添加数据
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 递归终止条件
        if (root == null) { // 返回新插入的叶子节点，供上一层使用
            TreeNode cur = new TreeNode(val);
            return cur;
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val); // 将新插入的节点，和root节点关联起来
        } else {
            root.right = insertIntoBST(root.right, val); // 将新插入的节点，和root节点关联起来
        }
        return root;
    }

    // 迭代法,, 二叉搜索树的迭代法，好处是因为有序，已经指明了前进路径的方向，不必全部遍历整颗树
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur == null) {
                TreeNode node = new TreeNode(val);
                if (pre == null) {
                    return node;
                }
                if (pre.val < val) {
                    pre.right = node;
                } else {
                    pre.left = node;
                }
                break;
            }
            pre = cur;
            if (cur.val > val) {
                stack.push(cur.left);
            }
            if (cur.val < val) {
                stack.push(cur.right);
            }
        }
        return root;
    }
}