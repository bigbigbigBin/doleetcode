import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReconstructQueueByHeight {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
     *
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     *
     * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     *
     * 示例 1：
     *
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
     * 示例 2：
     *
     * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
     * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
     * 提示：
     *
     * 1 <= people.length <= 2000
     * 0 <= hi <= 10^6
     * 0 <= ki < people.length
     * 题目数据确保队列可以被重建
     */

    // 本题有两个维度，h和k，看到这种题目一定要想如何确定一个维度，然后在按照另一个维度重新排列。
    // 如果两个维度一起考虑一定会顾此失彼。
    public int[][] reconstructQueue(int[][] people) {
        int peopleNum = people.length;
        Arrays.sort(people, ((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] > o2[1] ?  1 : -1;
            } else if (o1[0] > o2[0]) {
                return -1;
            }
            return 1;
        }));

        LinkedList<int[]> res = new LinkedList<>();
        for (int i = 0; i < peopleNum; i++) {
            res.add(people[i][1], people[i]);
        }

        int[][] resArray = res.toArray(new int[peopleNum][]);
        return resArray;
    }

    public int[][] reconstructQueue2(int[][] people) {
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        for(int i = 0; i < people.length; i++) {
            System.out.println(Arrays.toString(people[i]));
        }

        LinkedList<int[]> que = new LinkedList<>();

        for (int[] p : people) {
            que.add(p[1],p);
        }

        return que.toArray(new int[people.length][]);
    }

    public static void main(String[] args) {
        int[][] people = {{7,0},{4,4}, {7,1},{5,0},{6,1},{5,2}};
        ReconstructQueueByHeight rr = new ReconstructQueueByHeight();
        rr.reconstructQueue(people);
        System.out.println("==========queue2");
//        rr.reconstructQueue2(people);
    }
}
