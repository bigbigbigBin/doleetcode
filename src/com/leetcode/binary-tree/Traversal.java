import java.util.*;

public class Traversal {


    // 前序、中序、后续遍历



    List<Integer> res = new ArrayList<>();

    // 前序 递归
    public void preOrderTraversalRecursive(TreeNode head) {
        if (head == null) {
            return;
        }
        res.add(head.val); // 根
        preOrderTraversalRecursive(head.left); // 左
        preOrderTraversalRecursive(head.right); // 右
    }

    // 前序 非递归
    public void preOrderTraversal(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }


    // 中序 非递归
    public void inOrderTraversal(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        /** 记住：中序非递归遍历，一定要有一个辅助指针 */
        TreeNode cur = head;
        while(cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;   // 左子树一直进入栈中
            } else {   // 当cur第一次为空，说明左子树已经到头了
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
    }


    // 后续  非递归
    public List<Integer> postOrderTraversal(TreeNode head) {
        List<Integer> result = new ArrayList<>();
        if (head == null) {
            return result;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);      // 中，
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.left != null) {
                stack.push(cur.left); // 左
            }
            if (cur.right != null) {
                stack.push(cur.right); // 右
            }
        }
        // 中 左 右 的顺序入栈， 出站就是  中 右 左。
        // 反转结果为 左 右 中，符合后续遍历
        Collections.reverse(result);
        return result;
    }




}
