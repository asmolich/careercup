import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode
 * 1249. Minimum Remove to Make Valid Parentheses
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * #Medium #Stack
 */
public class MinimumRemoveToMakeValidParentheses {
    public static void main(String[] args) {
        MinimumRemoveToMakeValidParentheses sol = new MinimumRemoveToMakeValidParentheses();
        System.out.println(sol.minRemoveToMakeValid("lee(t(c)o)de)")); // "lee(t(c)o)de"
        System.out.println(sol.minRemoveToMakeValid("a)b(c)d")); // "ab(c)d"
        System.out.println(sol.minRemoveToMakeValid("))((")); // ""
        System.out.println(sol.minRemoveToMakeValid("(a(b(c)d)")); // "a(b(c)d)"
    }

    public String minRemoveToMakeValid(String s) {
        if (s == null || s.isEmpty()) return "";

        int n = s.length();
        Deque<Integer> deq = new ArrayDeque<>();
        Set<Integer> extra = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') deq.add(i);
            else if (ch == ')') {
                if (deq.isEmpty()) {
                    extra.add(i);
                } else deq.removeLast();
            }
        }
        extra.addAll(deq);
        char[] res = new char[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (!extra.contains(i)) res[j++] = s.charAt(i);
        }
        return new String(res, 0, j);
    }
}
