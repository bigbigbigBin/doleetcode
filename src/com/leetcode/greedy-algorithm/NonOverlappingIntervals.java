import java.util.Arrays;

public class NonOverlappingIntervals {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/non-overlapping-intervals
     *
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     *
     * 注意: 可以认为区间的终点总是大于它的起点。区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     *
     * 示例 1:
     *
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * 示例 2:
     *
     * 输入: [ [1,2], [1,2], [1,2] ]
     * 输出: 2
     * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
     * 示例 3:
     *
     * 输入: [ [1,2], [2,3] ]
     * 输出: 0
     * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     */

    public int eraseOverlapIntervals(int[][] intervals) {
        // 升序排列
        Arrays.sort(intervals, (o1, o2) -> o1[0] < o2[0] ? -1 : (o1[0] == o2[0]) ? 0 : 1);

        int count = 0;
        for (int i =1; i < intervals.length; i++) {
            // 如果有重叠，要保留起始位置更小的那个元素
            if (intervals[i][0] < intervals[i-1][1]) { // 说明有重叠
                intervals[i][1] = Math.min(intervals[i][1], intervals[i-1][1]);
                count++;
            }
        }
        return count;
    }

    public int eraseOverlapIntervals2(int[][] intervals) {
        // 按照右边界升序排列
        Arrays.sort(intervals, (o1, o2) -> { return o1[1] < o2[1] ? -1 : (o1[1] == o2[1]) ? 0: 1; });

        // 直接求重复的区间是复杂的，转而求最大非重复区间个数。

        int count = 1;
        // 记得每次出现不重复的区间，要将不重复的区间的终点，更新到end
        int end = intervals[0][1];
        for (int i =1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }

        return intervals.length - count;
    }

    public static void main(String[] args) {
//        int[][] intervals = {{1,2}, {1,2}, {1,2}};
        int[][] intervals = {{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}};
        NonOverlappingIntervals nn = new NonOverlappingIntervals();
        System.out.println(nn.eraseOverlapIntervals(intervals));
        System.out.println(nn.eraseOverlapIntervals2(intervals));
    }

}
