import java.util.ArrayList;

/**
 * Find Needle in Haystack
 * https://leetcode.com/discuss/interview-question/429980/Discovery-Technical-Phone-Screen-Find-Needle-in-Haystack-(modified)
 * #Hard #Discovery #KMP
 * <p>
 * How to find a Needle in a Haystack in Java without using any library functions of String class.
 * String class is modified so that we can only use charAt(index) which returns '$' if index is out of bound.
 * We are not even allowed to use .length etc . What would be the brute force and optimal way to solve this problem.
 * <p>
 * public int findNeedle(String haystack, String needle){
 * }
 * <p>
 * For Example:
 * Haystack: "applepieorange"
 * Needle: "apple"
 * should return 0
 */
public class FindNeedleInHaystack {
    public static void main(String[] args) {
        FindNeedleInHaystack sol = new FindNeedleInHaystack();
        System.out.println(sol.findNeedle("applepieorange$", "apple$"));
        System.out.println(sol.findNeedle("applepieorange$", "pie$"));
        System.out.println(sol.findNeedle("applepieorange$", "orange$"));
        System.out.println(sol.findNeedle("penapplepineapplepen$", "applepen$"));
    }

    // Knuth-Morris-Pratt
    public int findNeedle(String s, String p) {
        int i = 0, j = 0;
        ArrayList<Integer> next = getNext(p);
        // System.out.println(next);
        while (j == -1 || s.charAt(i) != '$' && p.charAt(j) != '$') {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next.get(j);
            }
        }
        if (p.charAt(j) == '$')
            return i - j;
        return -1;
    }

    private ArrayList<Integer> getNext(String p) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(-1);
        int i = 0, j = -1;
        while (p.charAt(i) != '$') {
            if (j == -1 || p.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                res.add(j);
            } else {
                j = res.get(j);
            }
        }
        return res;
    }
}
