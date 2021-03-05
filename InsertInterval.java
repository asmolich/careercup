import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode
 * 57. Insert Interval
 * https://leetcode.com/problems/insert-interval/
 * #Medium
 */
public class InsertInterval {
    public static void main(String[] args) {
        InsertInterval sol = new InsertInterval();
        System.out.println(Arrays.deepToString(sol.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}))); // [[1,5],[6,9]]
        System.out.println(Arrays.deepToString(sol.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8}))); // [[1,2],[3,10],[12,16]]
        System.out.println(Arrays.deepToString(sol.insert(new int[][]{{}}, new int[]{5, 7}))); // [[5,7]]
        System.out.println(Arrays.deepToString(sol.insert(new int[][]{{1, 5}}, new int[]{2, 3}))); // [[1,5]]
        System.out.println(Arrays.deepToString(sol.insert(new int[][]{{1, 5}}, new int[]{2, 7}))); // [[1,7]]
        System.out.println(Arrays.deepToString(sol.insert(new int[][]{{1, 5}}, new int[]{6, 8}))); // [[1,5],[6,8]]
        System.out.println(Arrays.deepToString(sol.insert(new int[][]{{4, 5}}, new int[]{1, 2}))); // [[1,2],[4,5]]
        System.out.println(Arrays.deepToString(sol.insert(new int[][]{{3, 5}, {12, 15}}, new int[]{6, 6}))); // [[3,5],[6,6],[12,15]]
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) return new int[][]{newInterval};
        int n = intervals.length;

        if (newInterval[1] < intervals[0][0]) {
            int[][] res = new int[n + 1][2];
            res[0] = newInterval;
            System.arraycopy(intervals, 0, res, 1, n);
            return res;
        }
        if (newInterval[0] > intervals[n - 1][1]) {
            int[][] res = new int[n + 1][2];
            res[n] = newInterval;
            System.arraycopy(intervals, 0, res, 0, n);
            return res;
        }

//        int left = Arrays.binarySearch(intervals, newInterval, Comparator.comparingInt(a -> a[0]));
//        if (left < 0) left = ~left;
//        int right = Arrays.binarySearch(intervals, newInterval, Comparator.comparingInt(a -> a[1]));
//        if (right < 0) right = ~right;
//        System.out.println("left = " + left + ", right = " + right);
        List<int[]> res = new ArrayList<>(n + 1);
        boolean merge = false;
        boolean newAdded = false;
        for (int[] interval : intervals) {
            if (interval[0] < newInterval[0] && interval[1] < newInterval[0]) res.add(interval);
            else if (interval[0] > newInterval[1] && interval[1] > newInterval[1]) {
                if (!newAdded || merge) {
                    res.add(newInterval);
                    merge = false;
                    newAdded = true;
                }
                res.add(interval);
            } else {
                // merge
                newInterval = new int[]{Math.min(newInterval[0], interval[0]), Math.max(newInterval[1], interval[1])};
                merge = true;
            }
        }
        if (!newAdded || merge) res.add(newInterval);
        return res.toArray(new int[0][0]);
    }
}
