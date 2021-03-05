import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;

/**
 * Facebook Interview Preparation
 * <p>
 * Queue Removals
 * <p>
 * You're given a list of n integers arr, which represent elements in a queue (in order from front to back). You're also given an integer x, and must perform x iterations of the following 3-step process:
 * 1. Pop x elements from the front of queue (or, if it contains fewer than x elements, pop all of them)
 * 2. Of the elements that were popped, find the one with the largest value (if there are multiple such elements, take the one which had been popped the earliest), and remove it
 * 3. For each one of the remaining elements that were popped (in the order they had been popped), decrement its value by 1 if it's positive (otherwise, if its value is 0, then it's left unchanged), and then add it back to the queue
 * <p>
 * Compute a list of x integers output, the ith of which is the 1-based index in the original array of the element which had been removed in step 2 during the ith iteration.
 * <p>
 * Signature
 * <code>int[] findPositions(int[] arr, int x)</code>
 * <p>
 * Input
 * x is in the range [1, 316].
 * n is in the range [x, x*x].
 * Each value arr[i] is in the range [1, x].
 * <p>
 * Output
 * Return a list of x integers output, as described above.
 * <p>
 * Example
 * n = 6
 * arr = [1, 2, 2, 3, 4, 5]
 * x = 5
 * output = [5, 6, 4, 1, 2]
 * <p>
 * The initial queue is [1, 2, 2, 3, 4, 5] (from front to back).
 * <p>
 * In the first iteration, the first 5 elements are popped off the queue, leaving just [5]. Of the popped elements, the largest one is the 4, which was at index 5 in the original array. The remaining elements are then decremented and added back onto the queue, whose contents are then [5, 0, 1, 1, 2].
 * In the second iteration, all 5 elements are popped off the queue. The largest one is the 5, which was at index 6 in the original array. The remaining elements are then decremented (aside from the 0) and added back onto the queue, whose contents are then [0, 0, 0, 1].
 * In the third iteration, all 4 elements are popped off the queue. The largest one is the 1, which had the initial value of 3 at index 4 in the original array. The remaining elements are added back onto the queue, whose contents are then [0, 0, 0].
 * In the fourth iteration, all 3 elements are popped off the queue. Since they all have an equal value, we remove the one that was popped first, which had the initial value of 1 at index 1 in the original array. The remaining elements are added back onto the queue, whose contents are then [0, 0].
 * In the final iteration, both elements are popped off the queue. We remove the one that was popped first, which had the initial value of 2 at index 2 in the original array.
 */
public class QueueRemovals {
    public static void main(String[] args) {
        QueueRemovals sol = new QueueRemovals();
        System.out.println(Arrays.toString(sol.findPositions(new int[]{1, 2, 2, 3, 4, 5}, 5))); // [5, 6, 4, 1, 2]
        System.out.println(Arrays.toString(sol.findPositions(new int[]{1, 2, 3, 4, 5, 6}, 4))); // [4, 6, 5, 3]
        System.out.println(Arrays.toString(sol.findPositions(new int[]{2, 4, 2, 4, 3, 1, 2, 2, 3, 4, 3, 4, 4}, 4))); // [2, 5, 10, 13]
    }

    private static class QPos {
        int idx;
        int val;

        @Override
        public String toString() {
            return "(" + idx + ", " + val + ')';
        }
    }

    public int[] findPositions(int[] arr, int x) {
        // arr = [1, 2, 2, 3, 4, 5]
        // counts = new int[x]
        // counts[arr[i]]++

        if (arr == null || arr.length == 0) return new int[0];
        if (arr.length == 1) return new int[]{1};

        final int n = arr.length;
        int[] res = new int[x];
        Queue<QPos> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            QPos qPos = new QPos();
            qPos.idx = i;
            qPos.val = arr[i];
            q.add(qPos);
        }

        int count = x;
        while (count > 0) {
            int k = Math.min(x, q.size());
            QPos max = null;
            int idx = 0;
            for (Iterator<QPos> it = q.iterator(); idx < x && it.hasNext(); idx++) {
                QPos qp = it.next();
                if (max == null || max.val < qp.val) max = qp;
            }
            res[x - count] = max == null ? 0 : max.idx + 1;
            for (int i = 0; i < k; i++) {
                QPos qp = q.poll();
                if (qp != null && qp != max) {
                    QPos newQp = new QPos();
                    newQp.idx = qp.idx;
                    newQp.val = Math.max(0, qp.val - 1);
                    q.add(newQp);
                }
            }
            count--;
        }
        return res;
    }
}
