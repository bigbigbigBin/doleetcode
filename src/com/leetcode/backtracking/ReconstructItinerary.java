package backtracking;

import java.util.*;

public class ReconstructItinerary {

    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/reconstruct-itinerary
     *
     * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
     *
     * 提示：
     * 如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）。
     * 假定所有机票至少存在一种合理的行程。
     * 所有的机票必须都用一次 且 只能用一次。
     *
     * 示例 1：
     * 输入：[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * 输出：["JFK", "MUC", "LHR", "SFO", "SJC"]
     *
     * 示例 2：
     * 输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
     * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
     */

    List<String> result = new ArrayList<>();
    Map<String, TreeMap<String, Integer>> map = new HashMap<>();


    public List<String> findItinerary(List<List<String>> tickets) {

        // 先初始化图
        for (List<String> ticket : tickets) {
            // 存在这个出发点
            if (map.containsKey(ticket.get(0))) {
                // 找到这个到达点，有几张票
                int oldTickets = map.get(ticket.get(0)).getOrDefault(ticket.get(1), 0);
                map.get(ticket.get(0)).put(ticket.get(1), oldTickets + 1);
            } else {
                TreeMap<String, Integer> newMap = new TreeMap<>();
                newMap.put(ticket.get(1), 1);
                map.put(ticket.get(0), newMap);
            }
        }
        // 打印图的构造情况
         System.out.println(map);

        result.add("JFK");
        traversal(tickets);
        return result;
    }




    public boolean traversal(List<List<String>> tickets) {
        if (result.size() == tickets.size() + 1) {
            return true;
        }

        String preNode = result.get(result.size() - 1);
        if (map.containsKey(preNode)) {
            for (Map.Entry<String, Integer> entry : map.get(preNode).entrySet()) {
                if (entry.getValue() > 0) {
                    result.add(entry.getKey());
                    entry.setValue(entry.getValue() - 1);
                    if (traversal(tickets))
                        return true;
                    result.remove(result.size() - 1);// 回溯
                    entry.setValue(entry.getValue() + 1); // 回溯
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
//        String[][] ticketsArray = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        String[][] ticketsArray = {{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
        List<List<String>> tickets = new ArrayList<>();
        for (String[] ct : ticketsArray) {
            tickets.add(Arrays.asList(ct));
        }

//        tickets.add(Arrays.asList("MUC","LHR"));
//        tickets.add(Arrays.asList("JFK","MUC"));
//        tickets.add(Arrays.asList("SFO","SJC"));
//        tickets.add(Arrays.asList("LHR","SFO"));

        ReconstructItinerary rr = new ReconstructItinerary();
        System.out.println(rr.findItinerary(tickets));
    }

}
