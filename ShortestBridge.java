import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 934. Shortest Bridge
 * https://leetcode.com/problems/shortest-bridge/
 * #Medium
 */
public class ShortestBridge {
    public static void main(String[] args) {
        ShortestBridge sol = new ShortestBridge();
        System.out.println(sol.shortestBridge(new int[][]{{0, 1}, {1, 0}})); // 1
        System.out.println(sol.shortestBridge(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}})); // 2
        System.out.println(sol.shortestBridge(new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}})); // 1
    }

    public int shortestBridge(int[][] a) {
        if (a == null || a.length == 0) return -1;

        int m = a.length;
        int n = a[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    return bfs(a, i, j);
                }
            }
        }
        return -1;
    }

    private int bfs(int[][] a, int i, int j) {
        int m = a.length;
        int n = a[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j});
        a[i][j] = -1;
        boolean phase2 = false;
        int count = 0;
        outer:
        while (!q.isEmpty()) {
            int size = 1;
            if (phase2) {
                size = q.size();
            }
            for (int k = 0; k < size; k++) {
                int[] coords = q.poll();
                if (coords == null) continue;
                if (!phase2 && a[coords[0]][coords[1]] == -2) {
                    phase2 = true;
                    q.addFirst(coords);
                    continue outer;
                }
                if (phase2 && a[coords[0]][coords[1]] == -1) return count;

                for (int[] dir : dirs) {
                    int x = coords[0] + dir[0];
                    int y = coords[1] + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || a[x][y] < 0) continue;
                    if (a[x][y] == 1) {
                        if (phase2) q.addLast(new int[]{x, y});
                        else q.addFirst(new int[]{x, y});
                        a[x][y] = -1;
                    } else {
                        q.addLast(new int[]{x, y});
                        a[x][y] = -2;
                    }
                }
            }
            if (phase2) count++;
        }
        return Math.max(1, count);
    }
}
