/**
 * LeetCode. 70. Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        for (int i = 0; i < 21; i++) {
            System.out.println(climbStairs(i));
        }
    }

    private static int climbStairs(int n) {
        if (n < 0) return -1;
        if (n == 0) return 0;
        int c0 = 0, c1 = 1;
        int ci = 0;
        for (int i = 1; i <= n; i++) {
            ci = c1 + c0;

            c0 = c1;
            c1 = ci;
        }
        return ci;
    }
}
