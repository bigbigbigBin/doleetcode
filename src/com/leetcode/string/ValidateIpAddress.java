package string;

import java.util.ArrayList;
import java.util.List;

public class ValidateIpAddress {

    public String validIPAddress(String queryIP) {
        if (isIPV4(queryIP)) {
            return "IPv4";
        } else if (isIPV6(queryIP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    private boolean isIPV4(String queryIP) {
        String[] splited = mySplit(queryIP,  '.');
        if (splited.length != 4) {
            return false;
        }
        for (int i = 0; i < splited.length; i++) {
            if (!isAllValidNum(splited[i]))
                return false;
        }
        return true;
    }

    private boolean isAllValidNum(String str1) {
        if ((str1.length() > 1 && str1.charAt(0) == '0') || str1.length() < 1)
            return false;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) >= '0' && str1.charAt(i) <= '9') {
                continue;
            } else {
                return false;
            }
        }
        return Integer.parseInt(str1) >=0 && Integer.parseInt(str1) <= 255;
    }

    private boolean isIPV6(String queryIP) {
        String[] splited = mySplit(queryIP,  ':');
        if (splited.length != 8) {
            return false;
        }

        for (int i = 0; i < splited.length; i++) {
            if (!isAllValidIPV6Num(splited[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllValidIPV6Num(String str1) {
        if (str1.length() < 1 || str1.length() > 4) {
            return false;
        }

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) >= '0' && str1.charAt(i) <= '9') {
                continue;
            }

            if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'f') {
                continue;
            }

            if (str1.charAt(i) >= 'A' && str1.charAt(i) <= 'F') {
                continue;
            }
            return false;
        }
        return true;
    }

    private String[] mySplit(String target, char split){
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == split) {
                index.add(i);
            }
        }
        if (index.isEmpty()) {
            return new String[0];
        }
        String[] result = new String[index.size() + 1];
        int pre = 0;
        for (int i = 0; i < result.length; i++) {
            if (i == index.size()) {
                result[i] = target.substring(pre, target.length());
            } else {
                result[i] = target.substring(pre, index.get(i));
                pre = index.get(i) + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        String ip = "172.16.254.1";
//        String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
//        String ip = "1.0.1.";
        String ip = "121312321312";
        ValidateIpAddress vip = new ValidateIpAddress();
        System.out.println(vip.validIPAddress(ip));


//        String ip3 = ":";
//        String[] ttt = vip.mySplit(ip3, ':');
//        System.out.println(ttt);
    }
}
