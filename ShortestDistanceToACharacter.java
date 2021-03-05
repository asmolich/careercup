import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode
 * 821. Shortest Distance to a Character
 * #Easy #BFS #MinArray
 */
public class ShortestDistanceToACharacter {
    public static void main(String[] args) {
        ShortestDistanceToACharacter sol = new ShortestDistanceToACharacter();
        System.out.println(Arrays.toString(sol.shortestToChar("loveleetcode", 'e'))); // [3,2,1,0,1,0,0,1,2,2,1,0]
        System.out.println(Arrays.toString(sol.shortestToChar("aaab", 'b'))); // [3,2,1,0]
    }

    // min array
    public int[] shortestToChar(String s, char c) {
        if (s == null || s.length() == 0) return new int[0];

        int n = s.length();
        int[] res = new int[n];

        int prev = Integer.MIN_VALUE / 2;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) prev = i;
            res[i] = i - prev;
        }
        System.out.println(Arrays.toString(res));
        prev = Integer.MAX_VALUE / 2;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) prev = i;
            res[i] = Math.min(res[i], prev - i);
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    @SuppressWarnings("unused")
    public int[] shortestToCharBFS(String s, char c) {
        if (s == null || s.length() == 0) return new int[0];

        int n = s.length();
        int[] res = new int[n];
        Deque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            res[i] = -1;
            if (s.charAt(i) == c) deq.add(i);
        }
        int count = 0;
        while (!deq.isEmpty()) {
            int size = deq.size();
            for (int i = 0; i < size; i++) {
                //noinspection ConstantConditions
                int idx = deq.poll();
                if (res[idx] == -1) res[idx] = count;
                if (idx - 1 >= 0 && res[idx - 1] == -1) deq.add(idx - 1);
                if (idx + 1 < n && res[idx + 1] == -1) deq.add(idx + 1);
            }
            count++;
        }
        return res;
    }
}
