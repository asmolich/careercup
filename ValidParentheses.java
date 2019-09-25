import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode. 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("()"));      // true
        System.out.println(isValid("()[]{}"));  // true
        System.out.println(isValid("(]"));      // false
        System.out.println(isValid("([)]"));    // false
        System.out.println(isValid("{[]}"));    // true
    }

    private static boolean isValid(String s) {
        if (s == null || s.isEmpty()) return true;

        Deque<Character> deq = new ArrayDeque<>();
        for (int i = 0, length = s.length(); i < length; i++) {
            char ch = s.charAt(i);
            if (ch == ')') {
                if (!deq.isEmpty() && deq.peekLast().equals('(')) deq.removeLast();
                else return false;
            }
            else if (ch == '}') {
                if (!deq.isEmpty() && deq.peekLast().equals('{')) deq.removeLast();
                else return false;
            }
            else if (ch == ']') {
                if (!deq.isEmpty() && deq.peekLast().equals('[')) deq.removeLast();
                else return false;
            }
            else {
                deq.add(ch);
            }
        }
        return deq.isEmpty();
    }
}
