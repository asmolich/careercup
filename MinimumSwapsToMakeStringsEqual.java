/**
 * LeetCode
 * 1247. Minimum Swaps to Make Strings Equal
 * https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/
 * #Medium #Greedy
 */
public class MinimumSwapsToMakeStringsEqual {
    public static void main(String[] args) {
        MinimumSwapsToMakeStringsEqual sol = new MinimumSwapsToMakeStringsEqual();
        System.out.println(sol.minimumSwap("xx", "yy")); // 1
        System.out.println(sol.minimumSwap("xy", "yx")); // 2
        System.out.println(sol.minimumSwap("xx", "xy")); // -1
        System.out.println(sol.minimumSwap("xxyyxyxyxx", "xyyxyxxxyx")); // 4
    }

    public int minimumSwap(String s1, String s2) {
        if (s1 == null || s1.isEmpty()) return s2 == null || s2.isEmpty() ? 0 : -1;
        if (s1.length() != s2.length()) return -1;
        if (s1.equals(s2)) return 0;

        int n = s1.length();
        int xy = 0;
        int yx = 0;
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == c2) continue;
            if (c1 == 'x' && c2 == 'y') xy++;
            else if (c1 == 'y' && c2 == 'x') yx++;
        }
        if (((xy + yx) & 1) == 1) return -1;

        return xy / 2 + yx / 2 + xy % 2 + yx % 2;
    }
}
