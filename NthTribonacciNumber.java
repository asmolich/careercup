/**
 * LeetCode. 1137. N-th Tribonacci Number
 * https://leetcode.com/problems/n-th-tribonacci-number/
 */
public class NthTribonacciNumber {
    public static void main(String[] args) {
        for (int i = 0; i < 38; i++) {
            System.out.println(tribonacci(i));
        }
    }

    private static int tribonacci(int n) {
        if (n < 0) return -1;

        int t0 = 0, t1 = 1, t2 = 1;
        if (n == 0) return t0;
        if (n == 1) return t1;
        if (n == 2) return t2;

        int ti = 0;
        for (int i = 3; i <= n; i++) {

            ti = t0 + t1 + t2;

            t0 = t1;
            t1 = t2;
            t2 = ti;
        }
        return ti;
    }
}
