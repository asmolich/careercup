import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 71. Simplify Path
 * https://leetcode.com/problems/simplify-path/
 * #Medium #Stack
 */
public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath sol = new SimplifyPath();
        System.out.println(sol.simplifyPath("/../")); // "/"
        System.out.println(sol.simplifyPath("/home//foo/")); // "/home/foo"
        System.out.println(sol.simplifyPath("/a/./b/../../c/")); // "/c"
    }

    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) return "/";

        Deque<String> deq = new ArrayDeque<>();
        for (String p : path.split("/")) {
            if ("".equals(p) || ".".equals(p)) continue;
            if ("..".equals(p)) {
                if (!deq.isEmpty()) deq.removeLast();
            } else {
                deq.add(p);
            }
        }
        return "/" + String.join("/", deq);
    }
}
