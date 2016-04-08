import java.util.*;
public class RedundantBraces {
    public static void main(String[] args) {
        RedundantBraces rb = new RedundantBraces();
        String ex1 = "((a+b))";
        String ex2 = "(a + (a + b))";
        System.out.println("\"" + ex1 + "\"" + (rb.hasRedundantBraces(ex1) ? " has " : " doesn't have ") + "redundant braces");
        System.out.println("\"" + ex2 + "\"" + (rb.hasRedundantBraces(ex2) ? " has " : " doesn't have ") + "redundant braces");
    }
    private static final Set<Character> validChars = new HashSet<Character>(5);
    static {
        validChars.add('(');
        validChars.add('+');
        validChars.add('*');
        validChars.add('-');
        validChars.add('/');
    }
    public boolean hasRedundantBraces(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (validChars.contains(ch)) {
                stack.addLast(ch);
            }
            else if (ch == ')') {
                Character c;
                boolean hasOp = false;
                while((c = stack.removeLast()) != '(') {
                    hasOp = true;
                }
                if (!hasOp) return true;
            }
        }
        return false;
    }
}
