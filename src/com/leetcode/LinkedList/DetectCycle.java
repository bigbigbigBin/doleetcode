package LinkedList;

public class DetectCycle {

    /**
     * 题意：给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 为了表示给定链表中的环，使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
     *
     * 说明：不允许修改给定的链表。
     */

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        ListNode intersectionNode = null; // 相遇节点

        slow = slow.next;
        fast = fast.next;
        while(slow != null && fast != null) {
            fast = fast.next;
            if (slow == fast) { // 相等，说明二者相遇，必定存在环
                intersectionNode = slow;

                // 当他们相遇时，分别从相遇点，头结点开始走，当他们相遇时，就为环的入口。
                ListNode newHead = head;
                while (intersectionNode != newHead) {
                    newHead = newHead.next;
                    intersectionNode = intersectionNode.next;
                }
                return newHead;
            }
            if (fast == null) { // 快指针先到null，说明不存在环
                return null;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return null;
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




}
