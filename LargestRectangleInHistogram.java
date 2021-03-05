import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 84. Largest Rectangle in Histogram
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * #Hard #Stack
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        LargestRectangleInHistogram sol = new LargestRectangleInHistogram();
        System.out.println(sol.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3})); // 10
        System.out.println(sol.largestRectangleArea(new int[]{2, 4})); // 4
        System.out.println(sol.largestRectangleArea(new int[]{2, 0, 2, 1, 1})); // 3
        System.out.println(sol.largestRectangleArea(new int[]{0, 0, 1})); // 1
    }

    public int largestRectangleArea(int[] a) {
        if (a == null || a.length == 0) return 0;

        int n = a.length;
        Deque<Integer> deq = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            //System.out.println(deq);
            while (!deq.isEmpty() && a[i] < a[deq.getLast()]) {
                int idx = deq.removeLast();
                int w = deq.isEmpty() ? i : i - deq.getLast() - 1;
                max = Math.max(max, w * a[idx]);
                //System.out.println("idx = " + idx + ", max = " + max);
            }
            deq.addLast(i);
        }
        while (!deq.isEmpty()) {
            int idx = deq.removeLast();
            int w = deq.isEmpty() ? n : n - deq.getLast() - 1;
            max = Math.max(max, w * a[idx]);
            //System.out.println("idx = " + idx + ", max = " + max);
        }
        return max;
    }
}
