/**
 * LeetCode
 * 672. Bulb Switcher II
 * https://leetcode.com/problems/bulb-switcher-ii/
 *
 * <p>
 * There is a room with n lights which are turned on initially and 4 buttons on the wall.
 * After performing exactly m unknown operations towards buttons, you need to return how many different kinds
 * of status of the n lights could be.
 * <p>
 * Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
 * <p>
 * Flip all the lights.
 * Flip lights with even numbers.
 * Flip lights with odd numbers.
 * Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
 * </p>
 * <pre>
 * Example 1:
 *
 * Input: n = 1, m = 1.
 * Output: 2
 * Explanation: Status can be: [on], [off]
 *
 *
 * Example 2:
 *
 * Input: n = 2, m = 1.
 * Output: 3
 * Explanation: Status can be: [on, off], [off, on], [off, off]
 *
 *
 * Example 3:
 *
 * Input: n = 3, m = 1.
 * Output: 4
 * Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
 *
 *
 * Note: n and m both fit in range [0, 1000].
 * </pre>
 * <p>
 * #Medium #Math
 */
public class BulbSwitcherII {
    public static void main(String[] args) {
        for (int n = 1; n < 10; n++) {
            for (int m = 1; m < 10; m++) {
                System.out.printf("%2d - %d%n", n, flipLights(n, m));
            }
        }
    }

    private static int flipLights(int n, int m) {
        if (n == 0 || m == 0) return 1;
        if (n == 1) return 2;
        if (n == 2) return m == 1 ? 3 : 4;
        if (m == 1) return 4;
        return m == 2 ? 7 : 8;
    }
}
