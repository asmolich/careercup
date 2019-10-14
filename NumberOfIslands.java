/**
 * LeetCode. 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 * <p>
 * '1' - land, '0' - water, count the number of islands
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        System.out.println(numIslands(new char[][]{
                "".toCharArray()
        })); // 0
        System.out.println(numIslands(new char[][]{
                "11110".toCharArray(),
                "11010".toCharArray(),
                "11000".toCharArray(),
                "00000".toCharArray()
        })); // 1
        System.out.println(numIslands(new char[][]{
                "11000".toCharArray(),
                "11000".toCharArray(),
                "00100".toCharArray(),
                "00011".toCharArray()
        })); // 3
    }

    private static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') { // island
                    count++;
                    // start dfs here
                    sink(grid, i, j);
                }
            }
        }
        return count;
    }

    private static final int[][] dims = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static void sink(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        for (int[] dim : dims) {
            int x = i + dim[0];
            int y = j + dim[1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length) continue;

            if (grid[x][y] == '1') sink(grid, x, y);
        }
    }
}
// #DFS