package backtracking;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddress {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/restore-ip-addresses/
     *
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     *
     * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     *
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
     *
     * 示例 1：
     *
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     * 示例 2：
     *
     * 输入：s = "0000"
     * 输出：["0.0.0.0"]
     * 示例 3：
     *
     * 输入：s = "1111"
     * 输出：["1.1.1.1"]
     * 示例 4：
     *
     * 输入：s = "010010"
     * 输出：["0.10.0.10","0.100.1.0"]
     * 示例 5：
     *
     * 输入：s = "101023"
     * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     * 提示：
     *
     * 0 <= s.length <= 3000
     * s 仅由数字组成
     */

    public List<String> restoreIpAddresses(String s) {
        backTracing(s, 0, new ArrayList<>());
        return result;
    }

    List<String> result = new ArrayList<>();


    // 仍然是一个切割问题，切割问题可以继续抽象成树形结构。
    // 需要想通的点，每次切割产生的数据，为从[start, i]组成的子串。而不是像组合那样，只是第i位
    public void backTracing(String s, int startIndex, List<String> path) {
        // 终止条件
        if (path.size() == 4 && startIndex >= s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++){
                sb.append(path.get(i)).append(".");
            }
            sb.append(path.get(path.size() - 1));
            result.add(sb.toString());
            return;
        }

        for (int i = startIndex; i < s.length() && path.size() < 4; i++) {
            if (isValidNum(s, startIndex, i + 1)) {
                path.add(s.substring(startIndex, i+1));
            } else {
                // 包含前导0、或者大于255
                break;
            }
            backTracing(s, i+1, path);
            path.remove(path.size() - 1);
        }
    }

    private boolean isValidNum(String s, int startIndex, int endIndex) {
        if (s.charAt(startIndex) == '0' && endIndex - startIndex > 1) {
            // 首位不能为为0
            return false;
        } else {
            return Integer.parseInt(s.substring(startIndex, endIndex)) <= 255;
        }
    }


    public static void main(String[] args) {
        String s = "101023";
        RestoreIpAddress rr = new RestoreIpAddress();
        rr.restoreIpAddresses(s);
    }
}
