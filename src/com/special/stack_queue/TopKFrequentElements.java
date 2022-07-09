package special.stack_queue;

import java.util.*;

public class TopKFrequentElements {

    /**
     * 前置知识点：大顶堆、小顶堆，这个是二叉树相关知识，需要再回头来看
     * 什么是大顶堆？
     * 什么是小顶堆？
     */

    // 此时要思考一下，是使用小顶堆呢，还是大顶堆？
    //有的同学一想，题目要求前 K 个高频元素，那么果断用大顶堆啊。
    //那么问题来了，定义一个大小为k的大顶堆，在每次移动更新大顶堆的时候，每次弹出都把最大的元素弹出去了，那么怎么保留下来前K个高频元素呢。
    //所以我们要用小顶堆，因为要统计最大前k个元素，只有小顶堆每次将最小的元素弹出，最后小顶堆里积累的才是前k个最大元素。
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> dataMap = new HashMap<>();
        for (int i : nums) {
            dataMap.put(i, dataMap.getOrDefault(i, 0) + 1);
        }
        // System.out.println(dataMap);

        // 根据map的value值正序排，相当于一个小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(
                (O1, O2) -> O1.getValue() - O2.getValue()
        );
        for (Map.Entry<Integer, Integer> entry : dataMap.entrySet()) {
            queue.add(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int [] result = new int[k];
        for (int i = 0 ; i < k; i++) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }


    public int[] topKFrequent2(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        // 根据map的value值正序排，相当于一个小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums = {1,1,1,2,2,3};
        int k = 2;

        TopKFrequentElements tt = new TopKFrequentElements();
        System.out.println(Arrays.toString(tt.topKFrequent(nums, k)));
        System.out.println(Arrays.toString(tt.topKFrequent2(nums, k)));
    }

}
