import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 32. Longest Valid Parentheses
 * https://leetcode.com/problems/longest-valid-parentheses/
 * #Hard #Stack #DP
 */
public class LongestValidParentheses {
    private static class IndexPair {
        int i;
        int lastRead;
        int length;

        @Override
        public String toString() {
            return "IndexPair{" +
                    "i=" + i +
                    ", lastRead=" + lastRead +
                    ", length=" + length +
                    '}';
        }
    }

    public static void main(String[] args) {
        LongestValidParentheses sol = new LongestValidParentheses();
        System.out.println(sol.longestValidParentheses("(()())()((()(())))")); // 18
        System.out.println(sol.longestValidParentheses("()(())")); // 6
        System.out.println(sol.longestValidParentheses("(()")); // 2
        System.out.println(sol.longestValidParentheses(")()())")); // 4
        System.out.println(sol.longestValidParentheses("")); // 0
        System.out.println(sol.longestValidParentheses("()(()")); // 2
    }

    @SuppressWarnings("unused")
    private int longestValidParenthesesStack(String s) {
        if (s == null || s.length() < 2) return 0;

        int length = 0;
        int maxLength = 0;
        int lastRead = -1;
        Deque<IndexPair> deq = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            //System.out.println(deq);
            if (s.charAt(i) == ')') {
                if (deq.isEmpty()) {
                    length = 0;
                    continue;
                }
                IndexPair idx = deq.removeLast();
                //System.out.println("idx = " + idx + ", lastRead = " + lastRead);
                if (idx.lastRead == idx.i - 1)
                    length = idx.length + i - idx.i + 1;
                else
                    length = i - idx.i + 1;
                lastRead = i;
                if (maxLength < length) maxLength = length;
                //System.out.println("length = " + length + ", maxLength = " + maxLength);
            } else {
                IndexPair idx = new IndexPair();
                idx.i = i;
                idx.lastRead = lastRead;
                idx.length = length;
                deq.addLast(idx);
            }
        }
        return maxLength;
    }

    // T: O(N)
    // S: O(N)
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;

        int n = s.length();
        int[] dp = new int[n];
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '(') continue;
            // search for the longest valid parentheses ending at index i
            if (s.charAt(i - 1) == '(') {
                dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
            } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
