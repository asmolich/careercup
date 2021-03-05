import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 * https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 * #Hard #BFS
 */
public class ShortestPathInAGridWithObstaclesElimination {
    public static void main(String[] args) {
        ShortestPathInAGridWithObstaclesElimination sol = new ShortestPathInAGridWithObstaclesElimination();
        System.out.println(sol.shortestPath(new int[][]{
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}}, 1)); // 6
    }

    public int shortestPath(int[][] grid, int k) {
        if (grid == null || grid[0] == null) return 0;
        int m = grid.length, n = grid[0].length;

        int[][] seen = new int[m][n];
        for (int[] s : seen) Arrays.fill(s, Integer.MAX_VALUE);
        seen[0][0] = 0;

        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Deque<int[]> deq = new ArrayDeque<>();
        deq.add(new int[]{0, 0, 0});
        int steps = 0;
        while (!deq.isEmpty()) {
            int size = deq.size();
            for (int i = 0; i < size; i++) {
                int[] curr = deq.poll();
                if (curr == null) continue;
                int x = curr[0], y = curr[1];
                if (x == m - 1 && y == n - 1) return steps;

                for (int[] dir : dirs) {
                    int p = x + dir[0];
                    int q = y + dir[1];
                    if (p < 0 || q < 0 || p >= m || q >= n) continue;

                    int obs = curr[2] + grid[p][q];
                    if (obs > k || obs >= seen[p][q]) continue;
                    deq.addLast(new int[]{p, q, obs});
                    seen[p][q] = obs;
                }
            }
            steps++;
        }
        return -1;
    }
}
