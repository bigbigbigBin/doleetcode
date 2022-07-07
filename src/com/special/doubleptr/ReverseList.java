package special.doubleptr;

public class ReverseList {

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



    /**
     * 双指针法
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    // 高效法，双指针
    public ListNode reverseList2Pointer(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            // 先操作指针逆转指向
            ListNode temp = cur.next;
            cur.next = pre;

            //双指针都前进
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // 递归的反转链表
    public ListNode reverseListRecursive(ListNode head) {
        return recursive(head, null);
    }

    public ListNode recursive(ListNode head, ListNode pre) {
        if (head == null)
            return pre;

        ListNode temp = head.next;
        head.next = pre;
        pre = head;
        head = temp;
        return recursive(head, pre);
    }

//    /**
//     *  此法自己所想，效率不高
//     *  每次找到最后一个元素，然后添加到虚拟头结点的next位置
//     *  添加完毕之后，最后一个元素往前移动一位。
//     *
//     * 注意最后一步，要将最后一个节点的next置成null，避免循环链表的出现。
//     * 时间复杂度：O(n^2)
//     * 空间复杂度：O(1)
//     */
//    public ListNode reverseList(ListNode head) {
//        ListNode dummyHead = new ListNode(0, head);
//        ListNode dummyCurrent = dummyHead;
//        ListNode last = null;
//
//        ListNode current = dummyHead;
//
//        while(last != head) {
//            while (current.next != last) {
//                current = current.next;
//            }
//            last = current; // 每次截止的位置，每添加一个尾节点到头结点上，last就要更新完为最后一个节点。
//            current = head; //老循环回退到初始开始节点
//            dummyCurrent.next = last; // 找到的最后一个节点，连接到新的链表里面；
//            dummyCurrent = last;
//        }
//        if (dummyCurrent != null) {
//            dummyCurrent.next = null;
//        }
//        return dummyHead.next;
//    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
//        int[] array = {1,2};
//        int[] array = {1};
//        int[] array = {};

        ReverseList r = new ReverseList();
        ListNode head = r.makeListNodeFromArray(array);
        ListNode reversedNode = r.reverseList2Pointer(head);
//        ListNode reversedNode = r.reverseListRecursive(head);
        r.printListNode(reversedNode);
    }

    ListNode makeListNodeFromArray(int[] array) {
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
