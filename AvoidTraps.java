import java.util.List;

/**
 * Hacker Earth. Avoid traps.
 * <p>
 * There is a cave of N cells where each cell has a trap or is safe to land.
 * From a cell <code><i>i</i></code>, a person can jump to cell <code><i>i</i> + 1</code> or <code><i>i</i> + 2</code>.
 * Also, if the number <code><i>i</i></code> is special, he can also jump from cell <code><i>i</i></code> to cell
 * <code><i>i</i> + A</code> where A = <code>number of primes in [1, i]</code>. The number <code><i>i</i></code>
 * can be special in case (A/i) >= (r1/r2).
 * Given the details of cave, r1, r2, N, find the minimum number of steps to reach Nth cell.
 * Initially you are at cell 1.
 * </p>
 * <p>
 * Note: 0 means empty cell, 1 means trapped cell.
 * </p>
 * <br/>
 * <p>
 * Output format:<br/>
 * For each test case print the minimum time to get out of the cave. If it is not possible, print -1.
 * </p>
 * <pre>
 * Example 1:
 * r1 = 1, r2 = 2
 * cave = 00000000
 * result = 3
 *
 * Example 2:
 * r1 = 1, r2 = 5
 * cave = 01110
 * result = -1
 *
 * 1 <= r1,r2 <= 10000
 * 1 <= N <= 100000
 * </pre>
 */
public class AvoidTraps {

    private static final List<Integer> primes = PrimeGenerator.primes(100000);
    private static final int[] primesCount = new int[100001];

    static {
        // pre-processing
        for (Integer prime : primes) {
            primesCount[prime]++;
        }

        int currentCount = 0;
        for (int i = 2; i < 100001; i++) {
            if (primesCount[i] == 1) currentCount += 1;
            primesCount[i] = currentCount;
        }
    }

    public static void main(String[] args) {
        System.out.println(minSteps(1, 2, "00000000")); // 3
        System.out.println(minSteps(1, 5, "01110")); // -1
    }

    private static boolean isSpecial(int r1, int r2, int i) {
        return Double.compare((double) primesCount[i] / i, (double) r1 / r2) >= 0;
    }

    private static int minSteps(int r1, int r2, String cave) {
        if (r1 < 1 || r2 < 1 || r1 > 10000 || r2 > 10000 || cave == null || cave.isEmpty()) return -1;

        int length = cave.length();
        if (cave.charAt(0) != '0' || cave.charAt(length - 1) != '0') return -1; // no start/end point
        if (length <= 3) return 1;

        int[] steps = new int[length];
        for (int i = 1; i < length; i++) {
            char ch = cave.charAt(i);
            if (ch == '0') { // ok
                steps[i] = Math.min(getSteps(steps, i - 1, cave), getSteps(steps, i - 2, cave));
                int a = primesCount[i];
                if (a > 2 && i > a && isSpecial(r1, r2, i - a)) {
                    steps[i] = Math.min(steps[i], getSteps(steps, i - a, cave));
                }
                steps[i] = steps[i] == Integer.MAX_VALUE ? Integer.MAX_VALUE : steps[i] + 1;
            }
        }

        return steps[length - 1] == Integer.MAX_VALUE ? -1 : steps[length - 1];
    }

    private static int getSteps(int[] steps, int i, String cave) {
        if (i < 0 || cave.charAt(i) != '0') return Integer.MAX_VALUE;
        return steps[i];
    }
}
// #DP #window #house-robber
