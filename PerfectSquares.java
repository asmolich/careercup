import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode
 * 279. Perfect Squares
 * https://leetcode.com/problems/perfect-squares/
 * #Medium #BinarySearch
 * https://leetcode.com/problems/perfect-squares/discuss/1066159/JAVA-With-comments-Similar-to-coin-change-problem-DP-bottom-up
 */
public class PerfectSquares {
    public static void main(String[] args) {
        PerfectSquares sol = new PerfectSquares();
        System.out.println(sol.numSquares(10)); // 2
        System.out.println(sol.numSquares(100)); // 1
        System.out.println(sol.numSquares(65)); // 2
        System.out.println(sol.numSquares(12)); // 3
        System.out.println(sol.numSquares(13)); // 2
    }

    public int numSquares(int n) {
        if (n < 1) return 0;
        if (n < 4) return n;

        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }

        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }

        for (int sqrt : squares) {
            for (int i = sqrt; i <= n; i++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - sqrt]);
            }
        }

        return dp[n];
    }
}
