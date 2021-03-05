import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

/**
 * LeetCode
 * 480. Sliding Window Median
 * https://leetcode.com/problems/sliding-window-median/
 * #Hard #SlidingWindow #Heap
 * #Incomplete
 */
public class SlidingWindowMedian {
    public static void main(String[] args) {
        SlidingWindowMedian sol = new SlidingWindowMedian();
        System.out.println(Arrays.toString(sol.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))); // [1,-1,-1,3,5,6]
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new double[0];

        int n = nums.length;
        double[] res = new double[n - k + 1];
        MedianSolver med = new MedianSolver(k);
        for (int i = 0; i < k - 1; i++) {
            med.add(nums[i]);
        }
        for (int i = k - 1; i < n; i++) {
            med.add(nums[i]);
            res[i - k + 1] = med.getMedian();
        }
        return res;
    }

    private static class MyPriorityQueue extends TreeSet<Integer> {
        public MyPriorityQueue(int size) {
        }

        public MyPriorityQueue(int size, Comparator<? super Integer> comparator) {
            super(comparator);
        }

        public boolean add(Integer integer, int size) {
            if (size < 0) return false;

            boolean result = super.add(integer);
            if (size() > size) {
                pollLast();
            }
            return result;
        }

        public Integer poll() {
            return pollFirst();
        }

        public Integer peek() {
            return this.first();
        }
    }

    private static class MedianSolver {
        private final MyPriorityQueue minQ;
        private final MyPriorityQueue maxQ;
        private int size = 0;

        public MedianSolver(int k) {
            minQ = new MyPriorityQueue(k);
            maxQ = new MyPriorityQueue(k, (a, b) -> Integer.compare(b, a));
        }

        public int size() {
            return size;
        }

        @SuppressWarnings("UnusedReturnValue")
        boolean add(int a) {
            System.out.println(Map.of("minQ", minQ, "maxQ", maxQ, "a", a, "median", getMedian()));
            int minVal = minQ.size() > 0 ? minQ.peek() : Integer.MAX_VALUE;
            int maxVal = maxQ.size() > 0 ? maxQ.peek() : Integer.MIN_VALUE;
            boolean result;
            if (minQ.size() == maxQ.size()) {
                // ...-1 1...
                // a = 0
                if (a >= maxVal && a <= minVal) result = minQ.add(a, size - maxQ.size() - 1);
                else if (a > minVal) {
                    result = minQ.add(a, size - maxQ.size() - 1);
                } else {
                    result = maxQ.add(a, size - minQ.size() - 1);
                }
            } else if (minQ.size() > maxQ.size()) {
                if (a > minVal) {
                    Integer poll = minQ.poll();
                    maxQ.add(poll, size - minQ.size() - 1);
                    result = minQ.add(a, size - maxQ.size() - 1);
                } else {
                    result = maxQ.add(a, size - minQ.size() - 1);
                }
            } else {
                if (a > minVal) {
                    result = minQ.add(a, size - maxQ.size() - 1);
                } else {
                    Integer poll = maxQ.poll();
                    minQ.add(poll, size - maxQ.size() - 1);
                    result = maxQ.add(a, size - minQ.size() - 1);
                }
            }
            size++;
            return result;
        }

        double getMedian() {
            int minSize = minQ.size();
            int maxSize = maxQ.size();

            if (minSize > maxSize) return minQ.peek();
            else if (maxSize > minSize) return maxQ.peek();
            else return ((minSize > 0 ? minQ.peek() : 0) + (maxSize > 0 ? maxQ.peek() : 0)) / 2.0;
        }
    }
}
