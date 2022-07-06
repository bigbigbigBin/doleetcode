package ultipa;

public class MyQueue {
    // 用数组实现
    // 队列特点，先进先出

    private int[] data = new int[1024];
    private int head = 0;
    private int tail = 0;

    public int remove() {
        // 只能从头部移除
        if (data.length == 0) {
            throw new RuntimeException("空队列，无法移除");
        }
        int[] newData = new int[data.length];
        int oldValue = data[0];
        System.arraycopy(data, 1, newData, 0, tail - head);
        data = newData;
        return oldValue;
    }

    public boolean add(int i) {
        // 只能从尾部插入
        // 首先判断是否到达了尾部
        if (tail < data.length) {
            data[tail++] = i;
        } else {
            // 当空间不够时要扩容
            int[] newData = new int[data.length * 2];
            int oldLength = tail - head;
            System.arraycopy(data, head, newData, 0, tail - head);
            data = newData;
            head = 0;
            tail = oldLength;
            data[tail++] = i; // 插入新数据
        }
        return true;
    }
}
