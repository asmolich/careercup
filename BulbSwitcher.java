/**
 * LeetCode
 * 319. Bulb Switcher
 * https://leetcode.com/problems/bulb-switcher/
 *
 * <p>
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb.
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb.
 * Find how many bulbs are on after n rounds.
 * </p>
 * <pre>
 * Example:
 *
 * Input: 3
 * Output: 1
 * Explanation:
 *  At first, the three bulbs are <code>[off, off, off]</code>.
 *  After first round, the three bulbs are <code>[on, on, on]</code>.
 *  After second round, the three bulbs are <code>[on, off, on]</code>.
 *  After third round, the three bulbs are <code>[on, off, off]</code>.
 *
 * So you should return 1, because there is only one bulb is on.
 * </pre>
 */
public class BulbSwitcher {
    public static void main(String[] args) {
        for (int n = 1; n < 1000; n++) {
            System.out.printf("%2d - %d - %d%n", n, (int) (Math.sqrt(n)), bulbSwitch(n));
        }
    }

    // Naive
    private static int bulbSwitch(int n) {
        boolean[] bulbs = new boolean[n];
        for (int k = 1; k <= n; k++) {
            for (int i = k - 1; i < n; i += k) {
                bulbs[i] = !bulbs[i];
            }
        }

        //if (n < 20) System.out.println(Arrays.toString(bulbs));

        int count = 0;
        for (int i = 0; i < n; i++) {
            count += bulbs[i] ? 1 : 0;
        }

        return count;
    }
}
