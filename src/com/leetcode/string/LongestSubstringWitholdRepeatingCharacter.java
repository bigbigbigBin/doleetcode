package string;

import java.util.HashMap;

public class LongestSubstringWitholdRepeatingCharacter {
    // 无重复字符最长子串的长度

    public int lengthOfLongestSubstring(String s) {
        // 滑动窗口
        HashMap<Character, Integer> windowMap = new HashMap<>();
        int maxWindow = 0;

        int begin = 0;

        for (int i = 0; i < s.length(); i++) {
            if (windowMap.containsKey(s.charAt(i))) {

                int preIndex = windowMap.get(s.charAt(i));
                System.out.println("-------- i = " + i + ", preIndex" + preIndex);
                for (int j = begin; j <= preIndex; j++) {
                    windowMap.remove(s.charAt(j));
                }
                windowMap.put(s.charAt(i), i);
                begin = preIndex + 1;
                maxWindow = Math.max(maxWindow, i - begin + 1);
            } else {
                windowMap.put(s.charAt(i), i);
                maxWindow = Math.max(maxWindow, i - begin + 1);
            }
        }
        return maxWindow;
    }

    public static void main(String[] args) {
        LongestSubstringWitholdRepeatingCharacter ll = new LongestSubstringWitholdRepeatingCharacter();
        ll.lengthOfLongestSubstring("abcabcbb");
    }
}
