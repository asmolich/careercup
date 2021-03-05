/**
 * LeetCode
 * 134. Gas Station
 * https://leetcode.com/problems/gas-station/
 */
public class GasStation {
    public static void main(String[] args) {
        GasStation sol = new GasStation();
        System.out.println(sol.canCompleteCircuit(
                new int[]{1, 2, 3, 4, 5},
                new int[]{3, 4, 5, 1, 2}
        )); // 3
        System.out.println(sol.canCompleteCircuit(
                new int[]{2, 3, 4},
                new int[]{3, 4, 3}
        )); // -1
        System.out.println(sol.canCompleteCircuit(
                new int[]{3, 3, 4},
                new int[]{3, 4, 4}
        )); // -1
        System.out.println(sol.canCompleteCircuit(
                new int[]{5, 1, 2, 3, 4},
                new int[]{4, 4, 1, 5, 1}
        )); // 4
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) return -1;

        int n = gas.length;
        int sum = 0, start = 0, tank = 0;
        for (int i = 0; i < n; i++) {
            int diff = gas[i] - cost[i];
            sum += diff;
            tank += diff;
            if (tank < 0) {
                start = (i + 1) % n;
                tank = 0;
            }
        }
        return sum < 0 ? -1 : start;
    }
}
