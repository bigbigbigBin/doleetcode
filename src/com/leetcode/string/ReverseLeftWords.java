package string;

public class ReverseLeftWords {

    /**
     *
     */

    // 部分反转 + 整体反转
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0, j = n -1; i < j; i++, j--) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
        for (int i = n, j = s.length() -1; i < j; i++, j--) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
        return sb.reverse().toString();
    }




    // O(n)的空间复杂度
    //
    public String reverseLeftWords2(String s, int n) {
        StringBuffer sb = new StringBuffer();
        sb.append(s, n, s.length());
        sb.append(s, 0, n);
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "lrloseumgh";
        int k = 6;
//        String s = "abcdefg";
//        int k = 2;
        ReverseLeftWords rw = new ReverseLeftWords();
        System.out.println(rw.reverseLeftWords(s, k));
    }

}
