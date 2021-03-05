import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 3. Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * #Medium #HashTable #SlidingWindow
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters sol = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println(sol.lengthOfLongestSubstring("")); // 0
        System.out.println(sol.lengthOfLongestSubstring("a")); // 1
        System.out.println(sol.lengthOfLongestSubstring("abcd")); // 4
        System.out.println(sol.lengthOfLongestSubstring("aabcd")); // 4
        System.out.println(sol.lengthOfLongestSubstring("aab")); // 2
        System.out.println(sol.lengthOfLongestSubstring("cdd")); // 2
        System.out.println(sol.lengthOfLongestSubstring("abba")); // 2
        System.out.println(sol.lengthOfLongestSubstring("aabaab!bb")); // 3
        System.out.println(sol.lengthOfLongestSubstring("tmmzuxt")); // 5
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        int n = s.length();
        int start = 0;
        int len = 1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                if (map.get(ch) >= start) start = map.get(ch) + 1;
            }
            len = Math.max(len, i - start + 1);
            map.put(ch, i);
            //System.out.println(Map.of("ch", ch, "len", len, "map", map, "substring", s.substring(start, i + 1)));
        }
        return map.size() == n ? n : len;
    }
}
