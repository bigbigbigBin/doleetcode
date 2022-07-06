package dp;

public class UniquePaths2 {
    /**
     * 力扣题目链接：https://leetcode-cn.com/problems/unique-paths-ii
     *
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2 解释：
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 向右 -> 向右 -> 向下 -> 向下
     * 向下 -> 向下 -> 向右 -> 向右
     */


    //1、 dp[i][j] 表示到达(i,j)能够可能的不同路径
    //2、 dp[i][j] = dp[i-1][j] + dp[i][j-1]
    //3、初始化， dp[i][0] = 1 dp[0][j] = 1
    //4、遍历顺序，从左到右，从上到下。
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            if (obstacleGrid[i][0] != 1) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }

        for (int i = 0; i < obstacleGrid[0].length; i++) {
            if(obstacleGrid[0][i] != 1) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }

        // print(dp);

        for (int i  =1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
            // print(dp);
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length -1];
    }

    // private void print(int [][]dp) {
    //     for (int i = 0 ; i< dp.length; i++) {
    //         System.out.println(Arrays.toString(dp[i]));

    //     }
    //     System.out.println("-----------------");
    // }
}
