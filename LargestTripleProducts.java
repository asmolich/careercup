import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Facebook Interview Preparation
 * <p>
 * Largest Triple Products
 * <p>
 * You're given a list of n integers arr[0..(n-1)]. You must compute a list output[0..(n-1)] such that, for each index i (between 0 and n-1, inclusive), output[i] is equal to the product of the three largest elements out of arr[0..i] (or equal to -1 if i < 2, as arr[0..i] then includes fewer than three elements).
 * <p>
 * Note that the three largest elements used to form any product may have the same values as one another, but they must be at different indices in arr.
 * <p>
 * Signature
 * int[] findMaxProduct(int[] arr)
 * <p>
 * Input
 * n is in the range [1, 100,000].
 * Each value arr[i] is in the range [1, 1,000].
 * <p>
 * Output
 * Return a list of n integers output[0..(n-1)], as described above.
 * <p>
 * Example 1
 * n = 5
 * arr = [1, 2, 3, 4, 5]
 * output = [-1, -1, 6, 24, 60]
 * The 3rd element of output is 3*2*1 = 6, the 4th is 4*3*2 = 24, and the 5th is 5*4*3 = 60.
 * <p>
 * Example 2
 * n = 5
 * arr = [2, 1, 2, 1, 2]
 * output = [-1, -1, 4, 4, 8]
 * The 3rd element of output is 2*2*1 = 4, the 4th is 2*2*1 = 4, and the 5th is 2*2*2 = 8.
 */
public class LargestTripleProducts {
    public static void main(String[] args) {
        LargestTripleProducts sol = new LargestTripleProducts();
        System.out.println(Arrays.toString(sol.findMaxProduct(new int[]{1, 2, 3, 4, 5}))); // [-1, -1, 6, 24, 60]
        System.out.println(Arrays.toString(sol.findMaxProduct(new int[]{2, 1, 2, 1, 2}))); // [-1, -1, 4, 4, 8]
    }

    private static class MyPriorityQueue extends PriorityQueue<Integer> {
        public MyPriorityQueue(Comparator<? super Integer> comparator) {
            super(comparator);
        }

        @Override
        public boolean add(Integer integer) {
            return super.add(integer);
        }

        public Integer[] peek3() {
            if (size() < 3) {
                return new Integer[size()];
            }
            Integer[] res = new Integer[3];
            res[0] = this.poll();
            res[1] = this.poll();
            res[2] = this.peek();
            this.add(res[0]);
            this.add(res[1]);
            return res;
        }
    }

    public int[] findMaxProduct(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];

        int n = arr.length;
        int[] res = new int[n];
        MyPriorityQueue maxQ = new MyPriorityQueue((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < n; i++) {
            maxQ.add(arr[i]);
            if (maxQ.size() < 3) {
                res[i] = -1;
            } else {
                Integer[] max3 = maxQ.peek3();
                // System.out.println(Arrays.toString(max3));
                res[i] = max3[0] * max3[1] * max3[2];
            }
        }
        return res;
    }
}
