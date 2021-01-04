import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 301. Remove Invalid Parentheses
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * #Hard
 */
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        RemoveInvalidParentheses sol = new RemoveInvalidParentheses();
        System.out.println(sol.removeInvalidParentheses("")); // [""]
        System.out.println(sol.removeInvalidParentheses("()())()")); // ["()()()", "(())()"]
        System.out.println(sol.removeInvalidParentheses("(a)())()")); // ["(a)()()", "(a())()"]
        System.out.println(sol.removeInvalidParentheses(")(")); // [""]
        System.out.println(sol.removeInvalidParentheses("((((")); // [""]
        System.out.println(sol.removeInvalidParentheses("x(")); // ["x"]
    }

    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.isEmpty()) return Collections.singletonList("");

        Set<String> res = new HashSet<>();
        removeInvalidParentheses0(s, 0, 0, res);
        return new ArrayList<>(res);
    }

    private void removeInvalidParentheses0(String s, int left, int right, Set<String> res) {
        int count = 0;
        int n = s.length();
        for (; right < n; right++) {
            char ch = s.charAt(right);
            if (ch == '(') count++;
            else if (ch == ')') count--;

            if (count < 0) break;
        }
        if (count < 0) {
            for (; left < right; left++) {
                char ch = s.charAt(left);
                if (ch != ')') continue;
                //if (left > 1 && s.charAt(left - 1) == ')') continue;

                removeInvalidParentheses0(s.substring(0, left) + s.substring(left + 1), left, right, res);
            }
        } else if (count > 0) {
            //if (right == n) right--;
            for (; left < right; left++) {
                char ch = s.charAt(left);
                System.out.println("left = " + left + ", right = " + right + ", ch = " + ch + ", count = " + count);
                if (ch != '(') continue;
                // if (right < n - 1 && s.charAt(right + 1) == '(') continue;

                removeInvalidParentheses0(s.substring(0, left) + s.substring(left + 1), left, right, res);
            }
        } else {
            res.add(s);
        }
    }
}
