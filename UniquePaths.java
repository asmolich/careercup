/**
 * LeetCode
 * 62. Unique Paths
 * https://leetcode.com/problems/unique-paths/
 * #Medium #DP #
 */
public class UniquePaths {
    public static void main(String[] args) {
        UniquePaths sol = new UniquePaths();
        System.out.println(sol.uniquePaths(3, 7)); // 28
        System.out.println(sol.uniquePaths(10, 10)); // 48620
    }

    public int uniquePaths(int m, int n) {
        int min = Math.min(m - 1, n - 1);
        int max = Math.max(m - 1, n - 1);
        // (a + b)! / a! * b!
        // (b+1)...(b+a) / 1*2*...*a
        long result = 1L;
        for (int i = 1; i <= min; i++) {
            result *= (max + i);
        }
        for (int i = 1; i <= min; i++) {
            result /= i;
        }
        return (int) result;
    }
}
