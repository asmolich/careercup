import java.util.PriorityQueue;

/**
 * LeetCode
 * 703. Kth Largest Element in a Stream
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * #Easy #PriorityQueue
 */
public class KthLargestElementInAStream {
    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));   // return 4
        System.out.println(kthLargest.add(5));   // return 5
        System.out.println(kthLargest.add(10));  // return 5
        System.out.println(kthLargest.add(9));   // return 8
        System.out.println(kthLargest.add(4));   // return 8
    }

    private static class KthLargest {
        private final PriorityQueue<Integer> minQ;
        private final int k;

        public KthLargest(int k, int[] nums) {
            minQ = new PriorityQueue<>(k + 1);
            this.k = k;
            for (int num : nums) {
                minQ.add(num);
                if (minQ.size() > k) minQ.poll();
            }
        }

        public int add(int val) {
            minQ.add(val);
            if (minQ.size() > k) minQ.poll();
            //noinspection ConstantConditions
            return minQ.peek();
        }
    }
}
