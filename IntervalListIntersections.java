import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode
 * 986. Interval List Intersections
 * https://leetcode.com/problems/interval-list-intersections/
 * #Medium
 */
public class IntervalListIntersections {
    public static void main(String[] args) {
        IntervalListIntersections sol = new IntervalListIntersections();
        System.out.println(Arrays.deepToString(sol.intervalIntersection(
                new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}}
        ))); // [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
        System.out.println(Arrays.deepToString(sol.intervalIntersection(
                new int[][]{},
                new int[][]{{4, 8}, {10, 12}}
        ))); // []
        System.out.println(Arrays.deepToString(sol.intervalIntersection(
                new int[][]{{1, 3}, {5, 9}},
                new int[][]{}
        ))); // []
        System.out.println(Arrays.deepToString(sol.intervalIntersection(
                new int[][]{{1, 7}},
                new int[][]{{3, 10}}
        ))); // [[3,7]]
    }

    public int[][] intervalIntersection(int[][] f, int[][] s) {
        if (f == null || f.length == 0 || s == null || s.length == 0) return new int[0][0];

        int m = f.length, n = s.length;
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        int[] curr;
        if (f[i][0] <= s[j][0]) curr = f[i++];
        else curr = s[j++];

        while (i < m || j < n) {
            if (j >= n || (i < m && f[i][0] <= s[j][0])) curr = intersect(curr, f[i++], res);
            else curr = intersect(curr, s[j++], res);
            // System.out.println(Arrays.toString(curr));
        }
        return res.toArray(new int[0][0]);
    }

    private int[] intersect(int[] i1, int[] i2, List<int[]> res) {
        if (i2[0] > i1[1] || i1[0] > i2[1]) {
            return new int[]{Math.max(i1[0], i2[0]), Math.max(i1[1], i2[1])};
        } else {
            res.add(new int[]{Math.max(i1[0], i2[0]), Math.min(i1[1], i2[1])});
            return new int[]{Math.min(i1[1], i2[1]), Math.max(i1[1], i2[1])};
        }
    }
}
