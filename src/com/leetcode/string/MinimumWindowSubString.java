package string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MinimumWindowSubString {

    public String minWindow(String s, String t) {
        // 滑动窗口：
        // 什么时候左指针移动？ 当窗口内能够满足覆盖t所有字符的要求。就尝试缩小窗口
        // 什么时候右指针移动？ 当串口不能覆盖t中所有字符。就扩大窗口
        HashMap<Character, Integer> origin = new HashMap<>();
        HashMap<Character, Integer> curent = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            origin.put(t.charAt(i), origin.getOrDefault(t.charAt(i), 0) + 1);
        }

        int curIndex = 0;
        int wl = 0;
        int res = Integer.MAX_VALUE;
        int start = 0; // 满足覆盖要求是，窗口的起始点
        int end = 0; // 满足覆盖要求是，窗口的终止点
        while (curIndex < s.length()) {
            curent.put(s.charAt(curIndex), curent.getOrDefault(s.charAt(curIndex), 0) + 1);
            while(meet(curent, origin, curIndex) && wl <= curIndex) {// 能满足覆盖t的要求
                System.out.println("curIndex = " + curIndex + ", wl = " + wl);
                if (curIndex - wl + 1 < res) {
                    start = wl;
                    end = curIndex;
                    res = curIndex - wl + 1;
                }
                curent.put(s.charAt(wl), curent.get(s.charAt(wl)) - 1);
                System.out.println("更新了一次curent之后，curent = " + curent);
                wl++;
            }
            curIndex++;
        }

        return res == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }

    private boolean meet(HashMap<Character, Integer> curent, HashMap<Character, Integer> origin, int curIndex) {
        Iterator<Map.Entry<Character, Integer>> iter = origin.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Character, Integer> it = iter.next();
            if (curent.getOrDefault(it.getKey(), 0) < it.getValue()){
                return false;
            }
        }
        System.out.println("curIndex = " + curIndex + ", curent = " + curent);
        return true;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        MinimumWindowSubString mm = new MinimumWindowSubString();
        mm.minWindow(s, t);
    }
}
