/**
 * LeetCode
 * 921. Minimum Add to Make Parentheses Valid
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 * #Medium
 */
public class MinimumAddToMakeParenthesesValid {
    public static void main(String[] args) {
        MinimumAddToMakeParenthesesValid sol = new MinimumAddToMakeParenthesesValid();
        System.out.println(sol.minAddToMakeValid("())")); // 1
        System.out.println(sol.minAddToMakeValid("(((")); // 3
        System.out.println(sol.minAddToMakeValid("()")); // 0
        System.out.println(sol.minAddToMakeValid("()))((")); // 4
    }

    public int minAddToMakeValid(String s) {
        if (s == null || s.isEmpty()) return 0;
        int invalid = 0;
        int balance = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                if (balance > 0) balance--;
                else invalid++;
            }
        }
        return invalid + balance;
    }
}
