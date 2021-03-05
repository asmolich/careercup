/**
 * LeetCode
 * 849. Maximize Distance to Closest Person
 * https://leetcode.com/problems/maximize-distance-to-closest-person/
 * #Medium
 */
public class MaximizeDistanceToClosestPerson {
    public static void main(String[] args) {
        MaximizeDistanceToClosestPerson sol = new MaximizeDistanceToClosestPerson();
        System.out.println(sol.maxDistToClosest(new int[]{1, 0, 0, 0, 1, 0, 1})); // 2
        System.out.println(sol.maxDistToClosest(new int[]{1, 0, 0, 0})); // 3
        System.out.println(sol.maxDistToClosest(new int[]{0, 0, 0, 1})); // 3
        System.out.println(sol.maxDistToClosest(new int[]{0, 1})); // 1
    }

    public int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length == 0) return -1;

        int n = seats.length;
        int left = -1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 0) continue;
            max = Math.max(max, left < 0 ? i : (i - left) / 2);
            left = i;
        }
        if (seats[n - 1] == 0) {
            max = Math.max(max, n - 1 - left);
        }
        return max;
    }
}
