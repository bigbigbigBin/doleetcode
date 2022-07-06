package string;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<List<Character>> result = new ArrayList<>();
        for (int k = 0; k < numRows; k++) {
            result.add(new ArrayList());
        }

        int i = 0;
        int j = 0;

        int curIndex = 0;

        while(curIndex < s.length()) {
            if (j == 0) {
                result.get(i).add(s.charAt(curIndex));
                i++;
                if (i == numRows) {
                    j = 1;
                    i = i-2;
                }
            } else {
                result.get(i).add(s.charAt(curIndex));
                i--;
                if (i == 0) {
                    j =  0;
                } else if (i == -1) {
                    i = 1;
                    j = 0;
                }

            }
            curIndex++;
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < result.size(); k++) {
            for (int m= 0; m < result.get(k).size(); m++) {
                sb.append(result.get(k).get(m));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        String ss = "PAYPALISHIRING";
//        int num = 3;
        String ss = "ABCD";
        int num = 2;
        ZigZagConversion zz =new ZigZagConversion();
        zz.convert(ss, num);
    }
}
