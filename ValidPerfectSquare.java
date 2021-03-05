/**
 * LeetCode
 * 367. Valid Perfect Square
 * https://leetcode.com/problems/valid-perfect-square/
 * #Easy
 */
public class ValidPerfectSquare {
    public static void main(String[] args) {
        ValidPerfectSquare sol = new ValidPerfectSquare();
        for (int i = 0; i < 101; i++) {
            System.out.println(i + ": " + sol.isPerfectSquare(i));
        }
        System.out.println(sol.isPerfectSquare(2147483647));
    }

    public boolean isPerfectSquare(int num) {
        if (num < 1) return false;
        int lo = 1;
        int hi = num;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            long sq = (long) mid * mid;
            if (sq == num) return true;
            if (sq < num) lo = mid + 1;
            else hi = mid - 1;
        }
        return false;
    }
}
