package stackQueue;

import java.util.Stack;

public class ValidSymbol {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */

    public boolean isValid(String s) {
        if (s.trim().equals("")) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
                continue;
            } else if (s.charAt(i) == ')' && !stack.isEmpty() && '(' == stack.peek()) {
                stack.pop();
                continue;
            } else if (s.charAt(i) == '}' && !stack.isEmpty() && '{' == stack.peek()) {
                stack.pop();
                continue;
            } else if (s.charAt(i) == ']' && !stack.isEmpty() && '[' == stack.peek()) {
                stack.pop();
                continue;
            }
            return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidSymbol vv = new ValidSymbol();
//        String s = "()";
//        String s = "()[]{}";
//        String s = "(]";
//        String s = "([)]";
//        String s = "{[]}";
        String s = "]";
        System.out.println(vv.isValid(s));
    }

}
