package special.hashtable;

public class RansomNote {

    /**
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     *
     * 如果可以，返回 true ；否则返回 false 。
     *
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];

        for (Character c : magazine.toCharArray()) {
            count[c - 'a'] += 1;
        }
        for (Character c : ransomNote.toCharArray()) {
            if (count[c - 'a'] == 0) {
                return false;
            }
            count[c - 'a'] -= 1;
        }
        return true;
    }



    // 使用map，耗费的空间，较大。。本题目已经说明只是小写字母组成的字符串，所以可以直接使用数组
//    public boolean canConstruct(String ransomNote, String magazine) {
//        HashMap<Character, Integer> dataMap = new HashMap<>();
//        for (Character c : magazine.toCharArray()) {
//            dataMap.put(c, dataMap.getOrDefault(c, 0) + 1);
//        }
//        for (Character c : ransomNote.toCharArray()) {
//            int oldCount = dataMap.getOrDefault(c, 0);
//            if (oldCount == 0)
//                return false;
//            dataMap.put(c, oldCount - 1);
//        }
//        return true;
//    }

    public static void main(String[] args) {
        String ransomNote = "a", magazine = "b";
//        String ransomNote = "aa", magazine = "aab";
        RansomNote ransomNoteObj = new RansomNote();
        System.out.println(ransomNoteObj.canConstruct(ransomNote, magazine));
    }

}
