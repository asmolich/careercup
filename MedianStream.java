import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Facebook Interview Preparation
 * <p>
 * Median Stream
 * <p>
 * You're given a list of n integers arr[0..(n-1)]. You must compute a list output[0..(n-1)] such that, for each index i (between 0 and n-1, inclusive), output[i] is equal to the median of the elements arr[0..i] (rounded down to the nearest integer).
 * <p>
 * The median of a list of integers is defined as follows. If the integers were to be sorted, then:
 * - If there are an odd number of integers, then the median is equal to the middle integer in the sorted order.
 * - Otherwise, if there are an even number of integers, then the median is equal to the average of the two middle-most integers in the sorted order.
 * <p>
 * Signature
 * int[] findMedian(int[] arr)
 * <p>
 * Input
 * n is in the range [1, 1,000,000].
 * Each value arr[i] is in the range [1, 1,000,000].
 * <p>
 * Output
 * Return a list of n integers output[0..(n-1)], as described above.
 * <p>
 * Example 1
 * n = 4
 * arr = [5, 15, 1, 3]
 * output = [5, 10, 5, 4]
 * <p>
 * The median of [5] is 5, the median of [5, 15] is (5 + 15) / 2 = 10, the median of [5, 15, 1] is 5, and the median of [5, 15, 1, 3] is (3 + 5) / 2 = 4.
 * <p>
 * Example 2
 * n = 2
 * arr = [1, 2]
 * output = [1, 1]
 * <p>
 * The median of [1] is 1, the median of [1, 2] is (1 + 2) / 2 = 1.5 (which should be rounded down to 1).
 */
public class MedianStream {
    public static void main(String[] args) {
        MedianStream sol = new MedianStream();
        System.out.println(Arrays.toString(sol.findMedian(new int[]{}))); // []
        System.out.println(Arrays.toString(sol.findMedian(new int[]{5, 15, 1, 3}))); // [5, 10, 5, 4]
        System.out.println(Arrays.toString(sol.findMedian(new int[]{1, 2}))); // [1, 1]
    }

    // Not sure why not doubles?!
    public int[] findMedian(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];

        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        int n = arr.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int bottom = maxQ.size() > 0 ? maxQ.peek() : 0;
            int top = minQ.size() > 0 ? minQ.peek() : 0;

            if (arr[i] < bottom) {
                if (maxQ.size() > minQ.size()) {
                    minQ.add(maxQ.poll());
                }
                maxQ.add(arr[i]);
            } else if (arr[i] > top) {
                if (minQ.size() > maxQ.size()) {
                    maxQ.add(minQ.poll());
                }
                minQ.add(arr[i]);
            } else {
                if (minQ.size() > maxQ.size()) {
                    maxQ.add(arr[i]);
                } else {
                    minQ.add(arr[i]);
                }
            }

            if (minQ.isEmpty()) {
                res[i] = maxQ.isEmpty() ? 0 : maxQ.peek();
                continue;
            }
            if (maxQ.isEmpty()) {
                res[i] = minQ.peek();
                continue;
            }

            if (minQ.size() == maxQ.size()) {
                res[i] = (minQ.peek() + maxQ.peek()) / 2;
            } else {
                res[i] = minQ.size() > maxQ.size() ? minQ.peek() : maxQ.peek();
            }
        }

        return res;
    }
}
