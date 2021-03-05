import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Facebook Interview Preparation
 * <p>
 * Minimizing Permutations
 * <p>
 * In this problem, you are given an integer N, and a permutation, P of the integers from 1 to N, denoted as (a_1, a_2, ..., a_N). You want to rearrange the elements of the permutation into increasing order, repeatedly making the following operation:
 * <p>
 * Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
 * <p>
 * Your goal is to compute the minimum number of such operations required to return the permutation to increasing order.
 * <p>
 * Signature
 * int minOperations(int[] arr)
 * <p>
 * Input
 * Array arr is a permutation of all integers from 1 to N, N is between 1 and 8
 * <p>
 * Output
 * An integer denoting the minimum number of operations required to arrange the permutation in increasing order
 * <p>
 * Example
 * If N = 3, and P = (3, 1, 2), we can do the following operations:
 * 1. Select (1, 2) and reverse it: P = (3, 2, 1).
 * 2. Select (3, 2, 1) and reverse it: P = (1, 2, 3).
 * output = 2
 */
public class MinimizingPermutations {
    public static void main(String[] args) {
        MinimizingPermutations sol = new MinimizingPermutations();
        System.out.println(sol.minOperations(new int[]{3, 1, 2})); // 2
        System.out.println(sol.minOperations(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1})); // 1
        System.out.println(sol.minOperations(new int[]{9, 8, 7, 3, 5, 4, 6, 2, 1})); // 3
    }

    private static class MyArray {
        int[] a;

        private MyArray(int[] a) {
            this.a = a;
        }

        static MyArray of(int[] a) {
            return new MyArray(a);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyArray that = (MyArray) o;
            return Arrays.equals(a, that.a);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(a);
        }

        @Override
        public String toString() {
            return Arrays.toString(a);
        }
    }

    private int minOperations(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        if (isSorted(arr)) {
            return 0;
        }

        int n = arr.length;
        Set<MyArray> visited = new HashSet<>();
        Deque<int[]> deq = new ArrayDeque<>();
        deq.add(arr);
        visited.add(MyArray.of(arr));
        int count = 0;
        while (!deq.isEmpty() && count < 30) {
            int k = deq.size();
            // System.out.println("deq size = " + k);

            for (int d = 0; d < k; d++) {
                int[] a = deq.removeFirst(); // queue -> BFS
                // System.out.println(Arrays.toString(a));
                // System.out.println(visited);
                if (isSorted(a)) {
                    return count;
                }
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        int[] neigh = reversedCopy(a, i, j);
                        MyArray myArray = MyArray.of(neigh);
                        // System.out.println(" Neighbor " + myArray + " is " + (visited.contains(myArray) ? "visited" : "unvisited"));
                        if (!visited.contains(myArray)) {
                            deq.add(neigh);
                            visited.add(myArray);
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= arr[i - 1]) return false;
        }
        return true;
    }

    private int[] reversedCopy(int[] arr, int p, int q) {
        int n = arr.length;
        int[] copy = new int[n];
        if (p > 0) System.arraycopy(arr, 0, copy, 0, p);
        if (q < n - 1) System.arraycopy(arr, q + 1, copy, q + 1, n - 1 - q);

        while (p <= q) {
            copy[p] = arr[q];
            copy[q] = arr[p];
            p++;
            q--;
        }
        return copy;
    }
}
