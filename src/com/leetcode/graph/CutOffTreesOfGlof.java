package graph;

import java.util.*;

public class CutOffTreesOfGlof {

    public int cutOffTree(List<List<Integer>> forest) {
        // 将（树高、树的x坐标、树的y坐标）
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{forest.get(i).get(j), i, j});
                }
            }
        }
        // 将树按照高度进行排序
        trees.sort((o1, o2) -> o1[0] - o2[0]);

        int stepNum = 0;
        int startX = 0;
        int startY = 0;

        for (int i = 0; i < trees.size(); i++) {
            boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
            int curStep = bfs(forest, startX, startY, trees.get(i)[1], trees.get(i)[2]);
            if (curStep == -1) {
                return -1;
            }
            stepNum += curStep;
            startX = trees.get(i)[1];
            startY = trees.get(i)[2];
        }
        return stepNum;
    }

    private int bfs(List<List<Integer>> forest, int startX, int startY, int targetX, int targetY) {
        System.out.println("startX = " + startX + ", startY = " + startY + ", targetX=" + targetX + ", targetY=" + targetY);

        // 初始点就相等
        if (startX == targetX && startY == targetY) {
            return 0;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startX, startY});
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        visited[startX][startY] = true;

        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            step++;
            while(size > 0) {
                int[] start = queue.poll();
                int sx = start[0];
                int sy = start[1];
                System.out.println("出站 = sx = " + sx + ", sy = " + sy + ", step = " + step);
                size--;

                // 判断上面的位置
                if (sx - 1 >= 0 && forest.get(sx - 1).get(sy) > 0 && !visited[sx-1][sy]) {
                    if (sx - 1 == targetX && sy == targetY) {
                        System.out.println("1step = " + step);
                        return step;
                    }
                    queue.add(new int[]{sx-1, sy});
                    visited[sx-1][sy] = true;
                }
                // 判断下面的位置
                if (sx + 1 < forest.size() && forest.get(sx + 1).get(sy) > 0 && !visited[sx+1][sy]) {
                    if (sx + 1 == targetX && sy == targetY) {
                        System.out.println("2step = " + step);
                        return step;
                    }
                    queue.add(new int[]{sx+1, sy});
                    visited[sx+1][sy] = true;
                }
                if (sy-1 >= 0 && forest.get(sx).get(sy-1) > 0 && !visited[sx][sy-1]) {
                    if (sx == targetX && sy-1 == targetY) {
                        System.out.println("3step = " + step);
                        return step;
                    }
                    queue.add(new int[]{sx, sy-1});
                    visited[sx][sy-1] = true;
                }
                if (sy+1 < forest.get(sx).size() && forest.get(sx).get(sy+1) > 0 && !visited[sx][sy+1]) {
                    if (sx == targetX && sy+1 == targetY) {
                        System.out.println("4step = " + step);
                        return step;
                    }
                    queue.add(new int[]{sx, sy+1});
                    visited[sx][sy+1] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(new Integer[]{1,2,3}));
        forest.add(Arrays.asList(new Integer[]{0,0,4}));
        forest.add(Arrays.asList(new Integer[]{7,6,5}));
        CutOffTreesOfGlof cc = new CutOffTreesOfGlof();

        cc.cutOffTree(forest);
    }
}
