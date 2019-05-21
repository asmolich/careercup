import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * InterviewBit. MAXSPPROD. https://www.interviewbit.com/problems/maxspprod/
 *
 */
public class MaxSpecialProduct {
    public static void main(String[] args) {
        System.out.println(maxSpecialProduct(List.of(
            5, 9, 6, 8, 6, 4, 6, 9, 5, 4, 9
            //7, 5, 7, 9, 8, 7
        )));
    }

    private static int maxSpecialProduct(List<Integer> a) {
        int n = a.size();
        // a[j]>a[i] (i>j)
        int[] leftSpecialValue = new int[n];
        // a[j]>a[i] (j>i)
        int[] rightSpecialValue = new int[n];

        Deque<Integer> deq = new ArrayDeque<>();
        deq.addLast(0);
        for (int i = 1; i < n; i++) {
            processSpecialValue(a, leftSpecialValue, deq, i);
        }
        deq.clear();
        deq.addLast(n - 1);

        for (int j = n - 2; j >= 0; j--) {
            processSpecialValue(a, rightSpecialValue, deq, j);
        }

        System.out.println(Arrays.toString(leftSpecialValue));
        System.out.println(Arrays.toString(rightSpecialValue));

        int max = 0;
        for (int i = 0; i < n; i++) {
            int p = ((leftSpecialValue[i] % 1000000007) * (rightSpecialValue[i] % 1000000007)) % 1000000007;
            if (max < p) max = p;
        }

        return max;
    }

    private static void processSpecialValue(List<Integer> a, int[] rightSpecialValue, Deque<Integer> deq, int j) {
        Integer aj = a.get(j);
        Integer aIdx = a.get(deq.getLast());
        if (aj >= aIdx) {
            while (!deq.isEmpty() && aj >= a.get(deq.getLast()))
                deq.removeLast();
            rightSpecialValue[j] = deq.isEmpty() ? 0 : deq.getLast();
        } else {
            rightSpecialValue[j] = deq.getLast();
        }
        deq.addLast(j);
    }
}
