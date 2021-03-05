/**
 * LeetCode
 * 509. Fibonacci Number
 * https://leetcode.com/problems/fibonacci-number/
 */
public class FibonacciNumber {
    public static void main(String[] args) {
        for (int i = 0; i < 31; i++) {
            System.out.println(fib(i));
        }
    }

    private static int fib(int n) {
        int f0 = 0, f1 = 1;
        if (n == 0) return f0;
        if (n == 1) return f1;
        int fi = 0;
        for (int i = 2; i <= n; i++) {
            fi = f0 + f1;

            f0 = f1;
            f1 = fi;
        }
        return fi;
    }
}
