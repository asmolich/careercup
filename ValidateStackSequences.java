import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 946. Validate Stack Sequences
 * https://leetcode.com/problems/validate-stack-sequences/
 * #Medium #Greedy #Stack
 */
public class ValidateStackSequences {
    public static void main(String[] args) {
        ValidateStackSequences sol = new ValidateStackSequences();
        System.out.println(sol.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1})); // true
        System.out.println(sol.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4,3,5,1,2})); // false
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int m = pushed.length, n = popped.length;
        if (n > m) return false;

        Deque<Integer> deq = new ArrayDeque<>();
        int j = 0;
        for (int a: pushed) {
            deq.push(a);
            while (!deq.isEmpty() && j < m && deq.peek() == popped[j]) {
                deq.pop();
                j++;
            }
        }

        return j == m;
    }

    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int n = pushed.length;
        // Pointer to point at the top
        int i = 0, j = 0;
        for (int k = 0; k < n; k++) {
            int top = pushed[k];
            // point the latest element to the top
            pushed[i] = top;
            while (i >= 0 && pushed[i] == popped[j]) {
                i--;
                j++;
            }
            i++;
        }
        return i == 0;
    }
}
