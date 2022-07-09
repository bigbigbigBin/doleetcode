package special.stack_queue;

import java.util.Stack;

public class DecodeString {
    /**
     *
     *
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     *
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     *
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/decode-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        int i = 0;

        while(i < s.length()) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                String numStr = getDigest(s, i);
                stack.push(numStr);
                i = i + numStr.length();
            } else if (s.charAt(i) == '[' || (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')) {
                stack.push(String.valueOf(s.charAt(i)));
                i++;
            } else {    //  ] 遇到右括号
                StringBuilder sb = new StringBuilder();
                int repeatNum = 0;
                while(!stack.isEmpty()) {
                    String topStr = stack.peek();
                    if (topStr.equals("[") ) { // 指导遇到 [ ，停止。
                        stack.pop();
                        break;
                    }

                    sb.append(topStr);
                    stack.pop();
                }

                repeatNum = Integer.parseInt(stack.pop());
                // 添加新组成的字符串
                stack.push(getString(sb, repeatNum));
                i++;
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }

    private String getDigest(String s, int start) {
        StringBuilder numSb = new StringBuilder();
        for (int i = start; i< s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')  {
                numSb.append(s.charAt(i));
            } else {
                break;
            }
        }
        // return Integer.parse(numSb.toString());
        return numSb.toString();
    }

    private String getString(StringBuilder sb, int repeatNum) {
        StringBuilder res = new StringBuilder();
        while(--repeatNum >= 0) {
            res.append(sb);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        DecodeString ds = new DecodeString();
        System.out.println(ds.decodeString(s));
    }
}
