import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode
 * 694. Number of Distinct Islands
 * https://ttzztt.gitbooks.io/lc/content/number-of-distinct-islands.html
 */
public class NumberOfDistinctIslands {
    public static void main(String[] args) {
        NumberOfDistinctIslands sol = new NumberOfDistinctIslands();
        System.out.println(sol.numDistinctIslands(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        })); // 1
        System.out.println(sol.numDistinctIslands(new int[][]{
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        })); // 3
        System.out.println(sol.numDistinctIslands(new int[][]{
                {1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        })); // 4
    }

    private static final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        Set<List<List<Integer>>> distinctIslands = new HashSet<>();
        Deque<int[]> deq = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    List<List<Integer>> island = getIsland(grid, i, j, m, n, deq);
                    distinctIslands.add(island);
                }
            }
        }
        return distinctIslands.size();
    }

    private List<List<Integer>> getIsland(int[][] grid, int i, int j, int m, int n, Deque<int[]> deq) {
        List<List<Integer>> island = new ArrayList<>();
        deq.add(new int[]{i, j});
//        island.add(Arrays.asList(0, 0));
        grid[i][j] = -1;
        while (!deq.isEmpty()) {
            int[] point = deq.poll();
            for (int[] dir : dirs) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] < 1) continue;
                deq.add(new int[]{x, y});
                island.add(Arrays.asList(x - i, y - j));
                grid[x][y] = -1;
            }
        }
        return island;
    }
}
