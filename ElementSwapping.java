import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Facebook Interview Preparation
 * <p>
 * Element Swapping
 * <p>
 * Given a sequence of n integers arr, determine the lexicographically smallest sequence which may be obtained from it after performing at most k element swaps, each involving a pair of consecutive elements in the sequence.
 * <p>
 * Note: A list x is lexicographically smaller than a different equal-length list y if and only if, for the earliest index at which the two lists differ, x's element at that index is smaller than y's element at that index.
 * <p>
 * Signature
 * int[] findMinArray(int[] arr, int k)
 * <p>
 * Input
 * n is in the range [1, 1000].
 * Each element of arr is in the range [1, 1,000,000].
 * k is in the range [1, 1000].
 * <p>
 * Output
 * Return an array of n integers output, the lexicographically smallest sequence achievable after at most k swaps.
 * <p>
 * Example 1
 * n = 3
 * k = 2
 * arr = [5, 3, 1]
 * output = [1, 5, 3]
 * We can swap the 2nd and 3rd elements, followed by the 1st and 2nd elements, to end up with the sequence [1, 5, 3]. This is the lexicographically smallest sequence achievable after at most 2 swaps.
 * <p>
 * Example 2
 * n = 5
 * k = 3
 * arr = [8, 9, 11, 2, 1]
 * output = [2, 8, 9, 11, 1]
 * We can swap [11, 2], followed by [9, 2], then [8, 2].
 */
public class ElementSwapping {
    public static void main(String[] args) {
        ElementSwapping sol = new ElementSwapping();
        System.out.println(Arrays.toString(sol.findMinArray(new int[]{5, 3, 1}, 2))); // [1, 5, 3]
        System.out.println(Arrays.toString(sol.findMinArray(new int[]{8, 9, 11, 2, 1}, 3))); // [2, 8, 9, 11, 1]
    }

    public int[] findMinArray(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) return new int[0];

        int n = arr.length;
        int[] res = Arrays.copyOf(arr, n);
        PriorityQueue<Integer> minQ = new PriorityQueue<>(Comparator.comparingInt(a -> arr[a]));
        int kn = Math.min(k + 1, n);
        for (int i = 0; i < kn; i++) minQ.add(i);

        int pos = 0;
        while (k > 0 && !minQ.isEmpty()) {
            int idx = minQ.poll();
            if (idx == pos) {
                pos++;
                if (kn < n) minQ.add(kn++);
                continue;
            }
            shift(res, pos, idx);
            k -= idx - pos;
//            System.out.println("k = " + k);
            pos++;
            if (kn < n) minQ.add(kn++);
        }
        return res;
    }

    private void shift(int[] res, int pos, int idx) {
//        System.out.println(Arrays.toString(res));
//        System.out.println("pos = " + pos + ", idx = " + idx);
        int temp = res[idx];
        System.arraycopy(res, pos, res, pos + 1, idx - pos);
        res[pos] = temp;
//        System.out.println(Arrays.toString(res));
    }
}
