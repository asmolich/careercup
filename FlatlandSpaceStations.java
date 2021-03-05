import java.util.Arrays;

/**
 * Hacker Rank
 * Flatland Space Stations
 * https://www.hackerrank.com/challenges/flatland-space-stations/problem
 */
public class FlatlandSpaceStations {
    public static void main(String[] args) {
        System.out.println(flatlandSpaceStations(5, new int[]{0, 4})); // 2
        System.out.println(flatlandSpaceStations(10, new int[]{1, 8})); // 3
        System.out.println(flatlandSpaceStations(13, new int[]{1, 8})); // 4
        // 0 (1) 2 3 4 5 6 7 (8) 9 10 11 12
    }

    private static int flatlandSpaceStations(int n, int[] c) {

        if (n == 0 || c == null || c.length == 0) return Integer.MAX_VALUE;

        if (c.length >= n) return 0;

        int[] stations = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int value : c) stations[value] = 1;

        int leftStation = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (stations[i] == 1) leftStation = i;
            left[i] = leftStation == Integer.MAX_VALUE ? Integer.MAX_VALUE : i - leftStation;
        }

        System.out.println(Arrays.toString(left));

        int rightStation = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (stations[i] == 1) rightStation = i;
            right[i] = rightStation == Integer.MAX_VALUE ? Integer.MAX_VALUE : rightStation - i;
        }

        System.out.println(Arrays.toString(right));

        int max = Math.min(left[0], right[0]);
        for (int i = 1; i < n; i++) {
            int dist = Math.min(left[i], right[i]);
            if (max < dist) max = dist;
        }
        return max;
    }
}
