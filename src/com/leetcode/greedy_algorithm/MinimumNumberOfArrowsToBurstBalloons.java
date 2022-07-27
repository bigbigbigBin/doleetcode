package com.leetcode.greedy_algorithm;

import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloons {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
     *
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
     *
     * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     *
     * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
     *
     * 示例 1：
     *
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
     * 示例 2：
     *
     * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
     * 输出：4
     * 示例 3：
     *
     * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
     * 输出：2
     * 示例 4：
     *
     * 输入：points = [[1,2]]
     * 输出：1
     * 示例 5：
     *
     * 输入：points = [[2,3],[2,3]]
     * 输出：1
     * 提示：
     *
     * 0 <= points.length <= 10^4
     * points[i].length == 2
     * -2^31 <= xstart < xend <= 2^31 - 1
     */

    public int findMinArrowShots(int[][] points) {
        // 先按照气球起始位置排序
        Arrays.sort(points, (o1, o2) -> {
            return o1[0] < o2[0] ? -1 : 1;
        });

//        for (int i = 0; i < points.length; i++) {
//            System.out.println(Arrays.toString(points[i]));
//        }

        // 找重叠，看第i个气球，和前面既有的重叠部分，是否重叠。
        // 既有的重叠部分，可以使用原来的数组来存放
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            // 第i个气球的起点 大于 第i-1个气球的终点，
            // 那么说明这俩气球根本不重叠，需要同不同的箭
            if (points[i][0] > points[i-1][1]) {
                count++;
            } else {
                // 说明有重叠，不需要新的箭，
                // 只需要更新既有的重合位置，以便后面的气球来判断是否重叠
                points[i][1] = Math.min(points[i-1][1], points[i][1]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        MinimumNumberOfArrowsToBurstBalloons mm = new MinimumNumberOfArrowsToBurstBalloons();
        System.out.println(mm.findMinArrowShots(points));
    }
}
