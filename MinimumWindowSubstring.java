import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 76. Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 * #Hard #SlidingWindow
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring sol = new MinimumWindowSubstring();
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC")); // BANC
        System.out.println(sol.minWindow("a", "a")); // a
    }

    public String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();

        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = t.charAt(i);
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);
        }

        String minStr = null;
        int left = 0;
        int right = 0;
        int count = counts.size();
        while (right < m) {
            // move right
            char ch = s.charAt(right);
            if (counts.containsKey(ch)) {
                counts.put(ch, counts.get(ch) - 1);
                if (counts.get(ch) == 0) {
                    count--;
                }
            }

            // move left
            while (count <= 0) {
                ch = s.charAt(left);
                if (counts.containsKey(ch)) {
                    counts.put(ch, counts.get(ch) + 1);
                    if (counts.get(ch) >= 1) {
                        count++;
                    }
                }
                // Get the min window
                if (minStr == null || right - left + 1 < minStr.length()) {
                    minStr = s.substring(left, right + 1);
                }
                left++;
            }
            right++;
        }
        return minStr == null ? "" : minStr;
    }
}
