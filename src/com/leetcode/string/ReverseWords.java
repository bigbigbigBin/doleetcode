package string;

public class ReverseWords {
    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     *
     * 示例 1：
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     *
     * 示例 2：
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     *
     * 示例 3：
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int last = s.length() - 1;
        int pre = s.length() - 1;
        while (pre >= 0) {
            while (pre >=0 && s.charAt(pre) != ' ')
                pre--;
            if (sb.length() == 0) {
                sb.append(s, pre + 1, last+1);
            } else {
                sb.append(" ");
                sb.append(s, pre + 1, last+1);
            }

            last = pre;
            while (pre >=0 && s.charAt(pre) == ' ') {
                pre--;
                last--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        String s = "the sky is blue";
//        String s = "hello world";
        String s = "a good   example";
        ReverseWords rw = new ReverseWords();
        System.out.println(rw.reverseWords(s));
    }
}
