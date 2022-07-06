package stackQueue;

import java.util.*;

public class SlidingWindowMaxNum {

    /*
    * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    * 返回滑动窗口中的最大值。
    * 进阶：你能在线性时间复杂度内解决此题吗？
    *
    *   1 <= nums.length <= 10^5
        -10^4 <= nums[i] <= 10^4
        1 <= k <= nums.length
    * */

    class MyQueue {
        Deque<Integer> deque = new LinkedList<>();

        public int poll(int val) {
            if (!deque.isEmpty() && deque.peek() == val) {
                return deque.poll();
            }
            return 0;
        }

        // 让队列中时刻保持最大值，去掉最大值之后，队列中剩余的次大值
        public void add(int val) {
            while (!deque.isEmpty() && deque.getLast() < val) {
                deque.removeLast();
            }
            deque.addLast(val);

        }

        // O(1)
        public int getMax() {
            return deque.peek();
        }
    }

    // 时间复杂度为 O(1) * n = O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        MyQueue myQueue = new MyQueue();
        int i = 0;
        while(i < k) {
            myQueue.add(nums[i]);
            i++;
        }
        result[i - k] = myQueue.getMax();

        while(i < nums.length) {
            myQueue.poll(nums[i-k]);
            myQueue.add(nums[i]);
            result[i - k + 1] = myQueue.getMax();
            i++;
        }
        return result;
    }

    // 暴力法，每次求解出滑动窗口（用的队列实现）内的最大值
    // 时间复杂度O(n*k)
    private int findMax(Queue<Integer> queue) {
        int max = Integer.MIN_VALUE;
        Iterator<Integer> it = queue.iterator();
        while (it.hasNext()){
            max = Math.max(max, it.next());
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        SlidingWindowMaxNum sl = new SlidingWindowMaxNum();
        System.out.println(Arrays.toString(sl.maxSlidingWindow(nums, k)));

    }
}
