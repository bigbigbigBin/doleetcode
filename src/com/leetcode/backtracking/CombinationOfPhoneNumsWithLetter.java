package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationOfPhoneNumsWithLetter {
    /**
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同, 即九宫格）。注意 1 不对应任何字母。
     *

     * */

    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        traversal(digits, 0, new ArrayList<>());
        return result;
    }

    public void traversal(String digits, int dStartIndex , List<Character> path) {
        // 终止条件
        if (path.size() == digits.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
            }
            result.add(sb.toString());
            return;
        }

        // 真正的字符串
        String realStr = getRealStr(digits.charAt(dStartIndex));

        // 单层递归
        for (int i = 0; i < realStr.length(); i++) {
            path.add(realStr.charAt(i));
            traversal(digits, dStartIndex + 1, path);
            path.remove(path.size() - 1);
        }
    }


    private String getRealStr(Character c) {
        switch (c) {
            case '0':
            case '1':
                return "";
            case '2':
                return "abc";
            case '3':
                return "def";
            case '4':
                return "ghi";
            case '5':
                return "jkl";
            case '6':
                return "mno";
            case '7':
                return "pqrs";
            case '8':
                return "tuv";
            case '9':
                return "wxyz";
        }
        return "";
    }

    public static void main(String[] args) {
        CombinationOfPhoneNumsWithLetter cc = new CombinationOfPhoneNumsWithLetter();
        System.out.println(cc.getRealStr('7'));
//        String digits = "2";
//        cc.letterCombinations(digits);
    }



}
