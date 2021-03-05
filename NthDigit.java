import java.util.Map;

/**
 * LeetCode
 * 400. Nth Digit
 * https://leetcode.com/problems/nth-digit/
 * #Medium
 */
public class NthDigit {

    // Technically, it's upper bound of String.valueOf(n).length()
    private static final int MAX_DIGITS_IN_INT = 11;
    private static final long[] POWERS_OF_10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L};

    public static void main(String[] args) {
        NthDigit sol = new NthDigit();
        System.out.println(sol.findNthDigit(1000000000)); // 1
        System.out.println(sol.findNthDigit(Integer.MAX_VALUE)); // 2
        for (int i = 0; i < 1000; i++) {
            System.out.println(sol.findNthDigit(i));
        }
    }

    //      1,  2,  3,  4,  5,  6,  7,  8,  9  => 9 * 10^0 = 9
    // 10, 11, 12, 13, 14, 15, 16, 17, 18, 19
    // 20, 21, 22, 23, 24, 25, 26, 27, 28, 29
    // ...                                     => 9 * 10^1 = 90
    // 90, 91, 92, 93, 94, 95, 96, 97, 98, 99
    // 100 .. 999                              => 9 * 10^2 = 900


    public int findNthDigit(int n) {
        if (n <= 9) return n;
        n -= 9;
        int digits = 2;
        while (digits < MAX_DIGITS_IN_INT) {
            long bucket = 9 * POWERS_OF_10[digits - 1] * digits;
            if (n - bucket > 0) {
                n -= bucket;
            } else {
                break;
            }
            digits++;
        }

        int wholeNumbers = n / digits;
        int remainder = n % digits;

        System.out.println(Map.of("n", n, "digits", digits, "wholeNumbers", wholeNumbers, "remainder", remainder));

        long number = POWERS_OF_10[digits - 1] + wholeNumbers;
        if (remainder == 0) return (int) ((number - 1) % 10);

        // number "number" has "digits" digits
        // and we need to take "remainder" digit from left
        // "digits - remainder" from fight

        return (int) (number / POWERS_OF_10[digits - remainder] % 10);
    }
}
