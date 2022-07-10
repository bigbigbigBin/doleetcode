package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationOfPhoneNumsWithLetter {
    /**
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同, 即九宫格）。
     * 注意：1 不对应任何字母。
     * 注意：7对应四个字母 pqrs
     *      9对应最后四个字母 wxyz，
     *      其他都对应三个字母。
     *
     * */


    String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }
        int n = digits.length();
        StringBuilder sb = new StringBuilder();
        backTracing(digits, n, sb, 0);
        return result;
    }

    private void backTracing(String digits, int n, StringBuilder path, int startIndex) {
        if (path.length() == n) {
            result.add(path.toString());
        }

        for (int i = startIndex; i < n; i++) {
            String curStr = map[digits.charAt(i) - '0'];
            for (int j = 0; j < curStr.length(); j++) {
                path.append(curStr.charAt(j));
                backTracing(digits, n, path, i + 1);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationOfPhoneNumsWithLetter cc = new CombinationOfPhoneNumsWithLetter();

//        String digits = "2";
//        cc.letterCombinations(digits);
    }



}
