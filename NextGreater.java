import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * InterviewBit. Next Greater
 * https://www.interviewbit.com/problems/nextgreater/
 * #Medium #Stack
 */
public class NextGreater {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreater(new int[]{4, 5, 2, 10}))); // [5, 10, 10, -1]
        System.out.println(Arrays.toString(nextGreater(new int[]{3, 4, 1, 6, 2}))); // [4, 6, 6, -1, -1]
    }

    private static int[] nextGreater(int[] a) {
        if (a == null || a.length == 0) return a;

        int n = a.length;
        int[] res = new int[n];
        Deque<Integer> deq = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            res[i] = -1;
            if (deq.isEmpty() || a[deq.getLast()] > a[i]) deq.add(i);
            else {
                while (!deq.isEmpty() && a[deq.getLast()] <= a[i]) {
                    res[deq.removeLast()] = a[i];
                }
                i--;
            }
        }
        return res;
    }
}

