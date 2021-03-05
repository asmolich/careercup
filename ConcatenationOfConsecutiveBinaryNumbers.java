/**
 * LeetCode
 * 1680. Concatenation of Consecutive Binary Numbers
 * https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/
 * #Medium
 */
public class ConcatenationOfConsecutiveBinaryNumbers {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        ConcatenationOfConsecutiveBinaryNumbers sol = new ConcatenationOfConsecutiveBinaryNumbers();
        System.out.println(sol.concatenatedBinary(1)); // 1
        System.out.println(sol.concatenatedBinary(3)); // 27
        System.out.println(sol.concatenatedBinary(12)); //
        System.out.println(sol.concatenatedBinary(11)); //
        System.out.println(sol.concatenatedBinary(100_000)); //
    }

    public int concatenatedBinary(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res <<= 32 - Integer.numberOfLeadingZeros(i);
            res %= MOD;
            res += i;
        }
        res %= MOD;
        return (int) res;
    }
}
