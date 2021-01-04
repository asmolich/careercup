import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 973. K Closest Points to Origin
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * #Medium #Heap
 */
public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        KClosestPointsToOrigin sol = new KClosestPointsToOrigin();
        System.out.println(Arrays.deepToString(sol.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1)));// [[-2,2]]
        System.out.println(Arrays.deepToString(sol.kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2)));// [[3,3], [-2,4]]
    }

    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || k <= 0) return new int[0][0];

        PriorityQueue<int[]> maxQueue = new PriorityQueue<>(
                // a = (x0, y0)
                // b = (x1, y1)
                // 0a = sqrt(x0**2 + y0**2)
                // 0b = sqrt(x1**2 + y1**2)
                (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );

        for (int[] p : points) {
            maxQueue.add(p);
            if (maxQueue.size() > k) {
                maxQueue.poll();
            }
        }

        return maxQueue.toArray(new int[0][0]);
    }
}
