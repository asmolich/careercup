import java.util.Arrays;

/**
 * LeetCode. 134. Gas Station
 * https://leetcode.com/problems/gas-station/
 */
public class GasStation {
    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(
                new int[]{1, 2, 3, 4, 5},
                new int[]{3, 4, 5, 1, 2}
        )); // 3
        System.out.println(canCompleteCircuit(
                new int[]{2, 3, 4},
                new int[]{3, 4, 3}
        )); // -1
        System.out.println(canCompleteCircuit(
                new int[]{3, 3, 4},
                new int[]{3, 4, 4}
        )); // -1
        System.out.println(canCompleteCircuit(
                new int[]{5, 1, 2, 3, 4},
                new int[]{4, 4, 1, 5, 1}
        )); // 4
    }

    private static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) return -1;

        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int g = gas[i];
            for (int j = 1; j <= n; j++) {
                int idx = (i + j) % n;
                g -= cost[(idx - 1 + n) % n];
                if (g < 0) {
                    break;
                }
                g += gas[idx];
            }
            if (g >= 0) return i;
        }
        return -1;
    }
}
