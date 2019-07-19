import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class RedundantBraces {
    public static void main(String[] args) {
        RedundantBraces rb = new RedundantBraces();
        String ex1 = "((a+b))";
        String ex2 = "(a + (a + b))";
        System.out.println("\"" + ex1 + "\"" + (rb.hasRedundantBraces(ex1) ? " has " : " doesn't have ") + "redundant braces");
        System.out.println("\"" + ex2 + "\"" + (rb.hasRedundantBraces(ex2) ? " has " : " doesn't have ") + "redundant braces");
    }

    private static final Set<Character> validChars = new HashSet<>(5);

    static {
        validChars.add('(');
        validChars.add('+');
        validChars.add('*');
        validChars.add('-');
        validChars.add('/');
    }

    private boolean hasRedundantBraces(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (validChars.contains(ch)) {
                stack.addLast(ch);
            } else if (ch == ')') {
                boolean hasOp = false;
                while (stack.removeLast() != '(') {
                    hasOp = true;
                }
                if (!hasOp) return true;
            }
        }
        return false;
    }
}
