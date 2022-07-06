import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartitionLables {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/partition-labels
     *
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     * 返回一个表示每个字符串片段的长度的列表。
     *
     * 示例：
     *
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8] 解释：划分结果为 "ababcbaca", "defegde", "hijhklij"。每个字母最多出现在一个片段中。像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     * 提示：
     *
     * S的长度在[1, 500]之间。
     * S只包含小写字母 'a' 到 'z' 。
     */

    public List<Integer> partitionLabels2(String s) {
        int[][] indexArray = new int[26][2];  // 0位置存第一次出现的地方，1位置存最后一次出现的地方
        for (int i = 0; i < indexArray.length; i++) {
            indexArray[i][0] = -1;
            indexArray[i][1] = -1;
        }

        int count= 0;
        for (int i = 0; i < s.length(); i++) {
            int charPos = s.charAt(i) - 'a';
            if (indexArray[charPos][0] == -1) { // s.charAt(i)第一次出现的地方
                indexArray[charPos][0] = i;
                count++;
            }
            if (i > indexArray[charPos][1]) { // s.charAt(i)最后一次出现的地方
                indexArray[charPos][1] = i;
            }
        }

        int[][] occurIndexArray = new int[count][2];
        int occurCount = 0;
        for (int i = 0; i < indexArray.length && count > 0; i++) {
            if (indexArray[i][0] > -1) {
                occurIndexArray[occurCount] = indexArray[i];
                occurCount++;
            }

        }
        Arrays.sort(occurIndexArray, (o1, o2) -> o1[0] < o2[0] ? -1 : o1[0] == o2[0] ? 0 : 1);


        int firstIndex = occurIndexArray[0][0];
        int lastIndex = occurIndexArray[0][1];
        List<Integer> res = new ArrayList<>();
        res.add(lastIndex - firstIndex + 1);

        for (int i = 1; i < occurIndexArray.length; i++) {
            if (occurIndexArray[i][0] < lastIndex) { // 说明出现的位置处于上一个字符重复出现的区间
                lastIndex = Math.max(lastIndex, occurIndexArray[i][1]);
                firstIndex = Math.min(firstIndex, occurIndexArray[i][0]);
                if (res.get(res.size() - 1) < (lastIndex - firstIndex) + 1)
                    res.set(res.size() - 1, lastIndex - firstIndex + 1);
            } else {
                firstIndex = occurIndexArray[i][0];
                lastIndex = occurIndexArray[i][1];
                res.add(lastIndex - firstIndex + 1);
            }
        }
        return res;
    }


    public List<Integer> partitionLabels(String s) {
        int[] hash = new int[26];

        // 遍历一遍字符串，将每个元素出现的位置，记录到数组中。
        // 这样同一个字符，后面出现的位置会覆盖前面出现的位置，
        // 便可保持数组中保存的都是每个字母最后出现的位置
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();

        // 再次遍历字符串，假设初始时，最远位置为第一个字符出现的最远位置，在走到这个最远位置之前，
        // 只要发现了其他字符有更加远的位置，那么这个更加远的位置，便是最后的子字符串的终止节点位置。
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right, hash[s.charAt(i) - 'a']);
            if (i == right) {
                res.add(right - left + 1);
                left = right + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        String S = "ababcbacadefegdehijhklij";
        String S = "qvmwtmzzse";
        PartitionLables pp = new PartitionLables();
        System.out.println(pp.partitionLabels2(S));
        System.out.println(pp.partitionLabels(S));
    }
}
