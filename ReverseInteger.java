/**
 * LeetCode
 * 7. Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 * #Easy
 */
public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger sol = new ReverseInteger();
        System.out.println(sol.reverse(123)); // 321
        System.out.println(sol.reverse(-123)); // -321
        System.out.println(sol.reverse(120)); // 21
        System.out.println(sol.reverse(0)); // 0
        System.out.println("MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println(sol.reverse(Integer.MAX_VALUE)); // 0
        System.out.println("MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println(sol.reverse(Integer.MIN_VALUE)); // 0
    }

    public int reverse(int x) {
        if (x == 0) return 0;

        int sign = Integer.signum(x);

        long res = 0;
        while (x != 0) {
            res *= 10;
            res += Math.abs(x % 10);
            if (res > Integer.MAX_VALUE) return 0;
            x /= 10;
        }
        return sign * (int)res;
    }
}
