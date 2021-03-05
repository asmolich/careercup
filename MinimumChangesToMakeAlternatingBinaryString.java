/**
 * LeetCode
 * 1758. Minimum Changes To Make Alternating Binary String
 * https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/
 * #Easy #Greedy
 */
public class MinimumChangesToMakeAlternatingBinaryString {
    public static void main(String[] args) {
        MinimumChangesToMakeAlternatingBinaryString sol = new MinimumChangesToMakeAlternatingBinaryString();
        System.out.println(sol.minOperations("0100")); // 1
        System.out.println(sol.minOperations("10")); // 0
        System.out.println(sol.minOperations("1111")); // 2
    }

    public int minOperations(String s) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) - '0' != (i & 1)) count++;
        }
        return Math.min(count, n - count);
    }
}
