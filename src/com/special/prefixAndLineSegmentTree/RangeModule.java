package special.prefixAndLineSegmentTree;

import java.util.Map;
import java.util.TreeMap;

public class RangeModule {

    /**
     * https://leetcode.cn/problems/range-module/solution/by-lfool-eo50/
     *
     *
     */

    // 有序集合
    TreeMap<Integer, Integer> intervals;

    public RangeModule() {
        intervals = new TreeMap<Integer, Integer>();
    }

    public void addRange(int left, int right) {
        // 找到  区间起点 > left 的最小的一个区间
        Map.Entry<Integer,Integer> leastHigherEntry = intervals.higherEntry(left);
        // TreeMap存放的第一个区间
        Map.Entry<Integer,Integer> firstEntry = intervals.firstEntry();

        // 说明TreeMap中存放的区间，存在满足 区间起点 > left 的区间。
        if (leastHigherEntry != firstEntry) {
            // 满足 区间起点 <= left 的最大区间
            // 如果 leastHigherEntry = null，说明所有区间都是 <= left
            Map.Entry<Integer,Integer> greatestLowerEntry = (leastHigherEntry == null) ? intervals.lastEntry() : intervals.lowerEntry(leastHigherEntry.getKey());
            // l=9,r=15     start = [8,16]
            if (greatestLowerEntry != null && greatestLowerEntry.getValue() >= right) {
                return;
            }

            // l=9,r=15     start = [8,14]
            if (greatestLowerEntry != null && greatestLowerEntry.getValue() >= left) {
                left = greatestLowerEntry.getKey(); // 留着后面添加
                intervals.remove(greatestLowerEntry.getKey()); // 删掉[8,14]
            }
        }

        // l = 1, r = 90     TreeMap =  [2,3] [5,9] [10, 11] [15, 200]
        while (leastHigherEntry != null && leastHigherEntry.getKey() <= right) {
            right = Math.max(right, leastHigherEntry.getValue()); // 如上例子，找到最大的right
            intervals.remove(leastHigherEntry.getKey());
            leastHigherEntry = intervals.higherEntry(leastHigherEntry.getKey());
        }
        intervals.put(left, right);
    }


    public void removeRange(int left, int right) {
        // 找到 区间起点 > left 的最小区间
        Map.Entry<Integer,Integer> leastHigherEntry = intervals.higherEntry(left);
        // 如果entry = null 说明所有的区间的起始点，都 <= left
        // 如果entry != null 说明有区间, 起始点 > left。
        //              进一步，如果entry = 第一个区间，那么所有的区间起始点都 > left
        //              否则，有区间起始点<=left 有区间起始点>left

        if (leastHigherEntry != intervals.firstEntry()) {
            // 满足 区间起点 <= left 的最大区间
            // 如果 leastHigherEntry = null，说明所有区间都是 <= left
            Map.Entry<Integer,Integer> greatestLowerEntry = (leastHigherEntry == null) ? intervals.lastEntry() : intervals.lowerEntry(leastHigherEntry.getKey());
            // l=5,r=10    start = [3,15]  或者[5,15]
            if (greatestLowerEntry != null && greatestLowerEntry.getValue() >= right) {
                if (greatestLowerEntry.getKey() == left) {      // l=5,r=10    start = [5,15]
                    intervals.remove(greatestLowerEntry.getKey());
                } else {
                    // l=5,r=10    start = [3,15]
                    intervals.put(greatestLowerEntry.getKey(), left);
                }
                if (greatestLowerEntry.getValue() != right) {
                    intervals.put(right, greatestLowerEntry.getValue());
                }
                return;
            } else {
                // l=5,r=10    start = [3,9]
                if (greatestLowerEntry != null && greatestLowerEntry.getValue() > left) {
                    intervals.put(greatestLowerEntry.getKey(), left);
                }
            }
        }

        // 找出来 区间左节点 <= right 的区间，然后删掉
        while(leastHigherEntry != null && leastHigherEntry.getKey() < right) {
            if (leastHigherEntry.getValue() <= right) {   // 整个区间 完全在欲删除的区间内
                intervals.remove(leastHigherEntry.getKey());
                leastHigherEntry = intervals.higherEntry(leastHigherEntry.getKey());
            } else { // 部分区间，在欲删除的区间内
                intervals.put(right, leastHigherEntry.getValue());
                intervals.remove(leastHigherEntry.getKey());
                break;
            }
        }
    }

    public boolean queryRange(int left, int right) {
        // 最小的大于，区间起始点大于left
        Map.Entry<Integer,Integer> leastHigherEntry = intervals.higherEntry(left);

        // 第一个区间，他的起始点，就大于left，那么说明  从left到firstEntry这部分无法满足覆盖
        if (leastHigherEntry == intervals.firstEntry()) {
            return false;
        }

        Map.Entry<Integer,Integer> greatestLowerEntry = (leastHigherEntry == null) ? intervals.lastEntry() : intervals.lowerEntry(leastHigherEntry.getKey());

        return greatestLowerEntry != null && greatestLowerEntry.getKey() <= left && greatestLowerEntry.getValue() >= right;
    }




    public static void main(String[] args) {
        RangeModule rm = new RangeModule();
        rm.getIntervals().put(3,10);
        rm.getIntervals().put(5,20);
        rm.getIntervals().put(7,30);
        rm.getIntervals().put(9,40);
        rm.getIntervals().put(15,150);

        System.out.println(rm.getIntervals().higherEntry(2));
        System.out.println(rm.getIntervals().firstEntry());
    }

    public TreeMap<Integer, Integer> getIntervals() {
        return intervals;
    }


}
