package LinkedList;

public class IntersectionNode {

    /**
     * 给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
     *
     * 示例 1：
     *
     * 输入：listA = [4,1,8,4,5], listB = [5,0,1,8,4,5]
     *
     * 输出：Reference of the node with value = 8
     *
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     */

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        int totalA = 0;
        int totalB = 0;
        while(pA != null) {
            pA = pA.next;
            totalA++;
        }
        while(pB != null) {
            pB = pB.next;
            totalB++;
        }

        pA = headA;
        pB = headB;
        if (totalA > totalB) {
            int goPaths = totalA - totalB;
            while(goPaths>0) {
                pA = pA.next;
                goPaths--;
            }
        } else {
            int goPaths = totalB - totalA;
            while(goPaths>0) {
                pB = pB.next;
                goPaths--;
            }
        }

        while(pA != null) {
            if (pA == pB) {
                return pA;
            }
            pA = pA.next;
            pB = pB.next;
        }
        return null;
    }

    public static void main(String[] args) {

    }

    class ListNode {
        int val;
        ListNode next;
        public ListNode() {
        }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private ListNode makeListNodeFromArray(int[] array) {
        ListNode dummyHead = new ListNode(0, null);
        ListNode current = dummyHead;
        for (int i = 0; i < array.length; i++) {
            current.next = new ListNode(array[i], null);
            current = current.next;
        }
        return dummyHead.next;
    }

    private void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ", ");
            head = head.next;
        }
        System.out.println();
    }
}
