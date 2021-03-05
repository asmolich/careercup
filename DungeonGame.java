import java.util.Arrays;

/**
 * LeetCode
 * 174. Dungeon Game
 * https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame {
    public static void main(String[] args) {
        DungeonGame sol = new DungeonGame();
        System.out.println(sol.calculateMinimumHP(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        })); // 7
        System.out.println(sol.calculateMinimumHP(new int[][]{{-200}})); // 201
    }

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) return -1;

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int needed = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = Math.max(needed, 1);
            }
        }
        return dp[0][0];
    }
}
