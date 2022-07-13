package design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LRUCache {
    /**
     * 可参考 https://blog.csdn.net/weixin_44424668/article/details/103994619
     *
     * 链表（处理新老关系）+ 哈希（查询在不在）
     */

    private class ListNode {
        private String key;
        private String value;
        private ListNode pre, next;
        public ListNode(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public ListNode() {

        }
    }

    // 用于快速判断，缓存中是否存在某个数据
    HashMap<String, ListNode> dataMap = new HashMap<>(16);
    // 缓存总大小
    int maxCount;
    // 已使用的大小
    volatile int curCount = 0;

    private ListNode head, tail; // 存放缓存的内容

    public LRUCache(int maxCount) {
        this.maxCount = maxCount;
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.pre = head;
    }

    public synchronized void put(String key, String value) {
        // 先看缓存中是否存在，存在则更新，并提到首部
        if (dataMap.containsKey(key)) {
            dataMap.get(key).value = value;
            // 提到首端
            // todo
        }

        // 先看缓存够不够，够的话，直接放入；
        // 不够的话，淘汰掉最久未用,

        // 最久未使用的节点，为tail的前一个节点
        if (curCount >= maxCount) {
            ListNode del = tail.pre;

            del.pre.next = tail;
            tail.pre = del.pre;

            del.next = null;
            del.pre = null;

            dataMap.remove(del.key);
            curCount--;
        }

        ListNode newNode = new ListNode(key, value);

        newNode.next = head.next;
        head.next.pre = newNode;

        head.next = newNode;
        newNode.pre = head;

        // 将数据存放在map，供下次使用
        dataMap.put(key, newNode);

        curCount++;
    }

    public String get(String key) {
        // 获取不到数据，则直接返回
        if(!dataMap.containsKey(key)) {
            return null;
        }

        // 获取到数据，则更新到链表最前面

        // 现将原位置的节点断开
        ListNode curNode = dataMap.get(key);
        curNode.pre.next = curNode.next;
        curNode.next.pre = curNode.pre;
        // 将原节点，放在head处

        curNode.next = head.next;
        head.next.pre = curNode;
        head.next = curNode;
        curNode.pre = head;
        return curNode.value;
    }
}
