import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 1091. Shortest Path in Binary Matrix
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * #Medium #BFS
 */
public class ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        ShortestPathInBinaryMatrix sol = new ShortestPathInBinaryMatrix();
        System.out.println(sol.shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}})); // 2
        System.out.println(sol.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}})); // 4
        System.out.println(sol.shortestPathBinaryMatrix(new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}})); // -1
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 || grid[0][0] == 1) return -1;

        int m = grid.length;
        int n = grid[0].length;

        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Deque<int[]> deq = new ArrayDeque<>();
        deq.add(new int[]{0, 0});
        int steps = 1;
        while (!deq.isEmpty()) {
            int size = deq.size();
            for (int i = 0; i < size; i++) {
                int[] c = deq.poll();
                if (c == null) continue;
                if (c[0] == m - 1 && c[1] == n - 1) return steps;
                for (int[] dir : dirs) {
                    int x = c[0] + dir[0];
                    int y = c[1] + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] > 0) continue;
                    deq.add(new int[]{x, y});
                    grid[x][y] = 2;
                }
            }
            steps++;
        }
        return -1;
    }
}
