package hashtable;

import java.util.*;

public class FindCommonChars {

    /**
     * 题目说明，只有小写字母，所以可以用26个空间的数组存放统计结果；
     * 由于输入的是一个字符数组，所以使用二维数组，二维数组的一行代表一个字符串中字符的出现情况。
     * 最后统计二维数组的每一列，每一列的最小值，就是这个字符在所有字符串中出现的公共次数
      */
    public List<String> commonChars(String[] words) {
        int[][] countArray = new int[words.length][26];
        for (int i =0; i< words.length; i++) {
            for (char c : words[i].toCharArray()) {
                countArray[i][c - 'a'] += 1;
            }
        }
        List<String> list = new ArrayList<>();

        for (int i = 0; i < countArray[0].length; i++) {
            int repeatCount = Integer.MAX_VALUE;
            for ( int j = 0; j < countArray.length; j++) {
                repeatCount = Math.min(repeatCount, countArray[j][i]);
            }
            while (repeatCount >0 ) {
                list.add(String.valueOf((char)('a' + i)));
                repeatCount--;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String[] words = {"bella","label","roller"};
//        String[] words = {"cool","lock","cook"};
        FindCommonChars commonChars = new FindCommonChars();
        System.out.println(commonChars.commonChars(words));
    }

}
