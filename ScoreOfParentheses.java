import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 856. Score of Parentheses
 * https://leetcode.com/problems/score-of-parentheses/
 * #Medium #Stack
 */
public class ScoreOfParentheses {
    public static void main(String[] args) {
        ScoreOfParentheses sol = new ScoreOfParentheses();
        System.out.println(sol.scoreOfParentheses("")); // 0
        System.out.println(sol.scoreOfParentheses(")(")); // 0
        System.out.println(sol.scoreOfParentheses("()")); // 1
        System.out.println(sol.scoreOfParentheses("(())")); // 2
        System.out.println(sol.scoreOfParentheses("()()")); // 2
        System.out.println(sol.scoreOfParentheses("(()(()))")); // 6
        System.out.println(sol.scoreOfParentheses2("")); // 0
        System.out.println(sol.scoreOfParentheses2(")(")); // 0
        System.out.println(sol.scoreOfParentheses2("()")); // 1
        System.out.println(sol.scoreOfParentheses2("(())")); // 2
        System.out.println(sol.scoreOfParentheses2("()()")); // 2
        System.out.println(sol.scoreOfParentheses2("(()(()))")); // 6
    }

    public int scoreOfParentheses(String s) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        Deque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') deq.add(i);
            else if (ch == ')') {
                if (deq.isEmpty()) return 0;
                int last = deq.removeLast();
                if (last < 0) {
                    int beforeLast;
                    while (!deq.isEmpty() && (beforeLast = deq.removeLast()) < 0) last += beforeLast;
                    deq.add(last * 2);
                } else deq.add(-1);
            }
        }
        int res = 0;
        while (!deq.isEmpty()) res -= deq.removeLast();
        return res;
    }

    public int scoreOfParentheses2(String s) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        Deque<Integer> deq = new ArrayDeque<>();
        deq.add(0);

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') deq.add(0);
            else if (ch == ')') {
                if (deq.size() < 2) return 0;
                int last = deq.removeLast();
                int beforeLast = deq.removeLast();
                int result = beforeLast + Math.max(2 * last, 1);
                deq.add(result);
            }
        }
        return deq.removeLast();
    }
}
