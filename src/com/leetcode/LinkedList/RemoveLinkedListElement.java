package LinkedList;

public class RemoveLinkedListElement {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 通过使用一个虚拟头结点，使删除头结点、其他节点共用了一个逻辑，不必单独处理删除头结点的情况
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(); // 使用一个虚拟头结点
        dummyHead.next = head;
        ListNode p = dummyHead; // p 指针

        while (p.next != null) {
            if (p.next.val == val) {
                ListNode delNode = p.next;
                p.next = delNode.next;
                delNode.next = null;
            } else {
                p = p.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {

    }
}
