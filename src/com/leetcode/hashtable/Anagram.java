package hashtable;

import java.util.HashMap;
import java.util.Map;

public class Anagram {

//    给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//    注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
//    示例 1:
//    输入: s = "anagram", t = "nagaram"
//    输出: true
//
//    示例 2:
//    输入: s = "rat", t = "car"
//    输出: false
//
//    说明:
//    你可以假设字符串只包含小写字母。

    public boolean isAnagram(String s, String t) {
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a'] -= 1;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        String s = "anagram", t = "nagaram";
        String s = "rat", t = "car";
        Anagram an = new Anagram();
        System.out.println(an.isAnagram(s, t));
    }
}
