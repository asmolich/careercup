import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given an array of integers a, return a new array b using the following guidelines:
 *
 * For each index i in b, the val of b[i] is the index of the a[j] nearest to a[i] and is also greater than a[i].
 * If there are two options for b[i], put the leftmost one in b[i].
 * If there are no options for b[i], put -1 in b[i].
 *
 * Example
 *
 * For a = [1, 4, 2, 1, 7, 6], the output should be
 * nearestGreater(a) = [1, 4, 1, 2, -1, 4].
 */
public class NearestGreater {

    public static int[] nearestGreater(int[] a) {
        // b[i] = min(j | a[j] = nearest(a[i]), a[j] > a[i]) or -1

        if (a == null || a.length == 0) return a;

        int[] b = new int[a.length];
        Deque<Integer> stack = new ArrayDeque<>(a.length);
        stack.addLast(0);
        //b[0] = -1;
        //b[b.length - 1] = -1;
        int max = 0;
        for (int i = 1; i < a.length; i++) {
            System.out.println("max = " + max);
            Integer top = stack.getLast();
            if (a[i] == a[max]) { b[i] = -1; }
            if (a[i] == a[top]) {
                b[i] = b[top];
                System.out.println("i=" + i + ", a[i]=" + a[i] + ", top=" + top + ", a[top]=" + a[top]);
            } else if (a[i] > a[top]) {
                top = stack.removeLast();
                if (stack.isEmpty()) {
                    b[top] = i;
                } else {
                    while (!stack.isEmpty() && a[i] > a[stack.getLast()]) { top = stack.removeLast(); }
                    if (stack.isEmpty()) {
                        b[top] = i;
                        b[i] = -1;
                        max = i;
                    } else {
                        top = stack.getLast();
                        b[i] = top;
                    }
                }
            } else {
                b[i] = top;
            }
            stack.addLast(i);
        }
        return b;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 4, 2, 1, 7, 6};
        System.out.println("Input  = " + Arrays.toString(input));
        int[] output = nearestGreater(input);
        System.out.println("Output = " + Arrays.toString(output));
        System.out.println("nearestGreater([1]) = " + Arrays.toString(nearestGreater(new int[]{1})));
        System.out.println("nearestGreater([1, 1, 1]) = " + Arrays.toString(nearestGreater(new int[]{1, 1, 1})));
        System.out.println("nearestGreater([1, 2]) = " + Arrays.toString(nearestGreater(new int[]{1, 2})));
        System.out.println("nearestGreater([1, 2, 3, 4, 5]) = " + Arrays.toString(nearestGreater(new int[]{1, 2, 3, 4, 5})));
        System.out.println("nearestGreater([2, 1]) = " + Arrays.toString(nearestGreater(new int[]{2, 1})));
        System.out.println("nearestGreater([5, 4, 3, 2, 1]) = " + Arrays.toString(nearestGreater(new int[]{5, 4, 3, 2, 1})));
        System.out.println("nearestGreater([1, 4, 2, 1, 3, 7, 6, 10]) = " + Arrays.toString(nearestGreater(new int[]{1, 4, 2, 1, 3, 7, 6, 10})));
        System.out.println("nearestGreater([7, 4, 7, 2, 7, 6]) = " + Arrays.toString(nearestGreater(new int[]{7, 4, 7, 2, 7, 6})));
    }
}
