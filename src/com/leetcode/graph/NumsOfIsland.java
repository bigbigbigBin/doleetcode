package graph;

public class NumsOfIsland {

    /**
     * https://leetcode.cn/problems/number-of-islands/
     *
     * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     *   ]
     * 输出：1
     *
     */


    int[][] steps = new int[][]{{1,0}, {-1,0}, {0,-1}, {0,1}};

    public int numIslands(char[][] grid) {
        int nums = 0;
        for (int i = 0; i < grid.length; i++) {
            System.out.println(grid[i]);
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    nums++;
                    dfsSetGrid(grid, i, j);
                }
            }
        }
        return nums;
    }

    private void dfsSetGrid(char[][] grid, int startX, int startY) {
        int maxXEnd = grid.length;
        int maxYEnd = grid[0].length;

        // 终止条件
        if (grid[startX][startY] == '0') {
            return ;
        }

        // 标记为当前岛屿
        grid[startX][startY] = '0';

        // 上下左右遍历是否属于当前岛屿面积
        for (int i = 0; i < steps.length; i++) {
            int[] curStep = steps[i];
            int newX = startX + curStep[0];
            int newY = startY + curStep[1];
            if (newX >=0 && newX < maxXEnd && newY >=0 && newY < maxYEnd) {
                if (grid[newX][newY] == 1) {
                    dfsSetGrid(grid, newX, newY);
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        NumsOfIsland mm = new NumsOfIsland();
        mm.numIslands(grid);
    }
}
