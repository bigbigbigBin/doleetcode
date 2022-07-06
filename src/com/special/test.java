package special;

import java.util.*;

public class test {

    int visited[] = new int[1 << 21] ;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // 博弈论
        // 相当于等差数列求和
        if ((1+maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }

        int state = 0;

        return dfs(0, 0, maxChoosableInteger, desiredTotal);

    }

    public boolean dfs(int sum, int state, int maxChoosableInteger, int desiredTotal) {
        if (visited[state] == 1) {
            return true;
        }
        if (visited[state] == 2) {
            return false;
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            // 运算符的优先级，& 要用括号括起来
            if ((state & (1 << i)) > 0) {  // 说明使用过
                continue;
            }

            if (sum + i >= desiredTotal) {
                visited[state] = 1;
                return true;
            }
            // 本题目，因为已经做了前置判断，一定会找出来和大于desiredTotal， 不大于的场景已经在一开始筛掉了

            if (!dfs(sum + i, state | (1 << i), maxChoosableInteger, desiredTotal)) {
                visited[state] = 1;
                return true;
            }

        }
        visited[state] = 2;
        return false;

    }


    public static void main(String[] args) {
        test tt = new test();
        tt.canIWin(20, 210);

        List<Integer> ll = new ArrayList<>();
        ll.sort((o1, o2) -> o1 - o2 > 0 ? 1 : o1 - o2 == 0 ? 0 : -1);
    }


}
