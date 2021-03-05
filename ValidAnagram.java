/**
 * LeetCode
 * 242. Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 * #Easy #HashTable #Sort
 */
public class ValidAnagram {
    public static void main(String[] args) {
        ValidAnagram sol = new ValidAnagram();
        System.out.println(sol.isAnagram("anagram", "nagaram")); // true
        System.out.println(sol.isAnagram("rat", "car")); // false
    }

    public boolean isAnagram(String s, String t) {
        if (s == null) return t == null;
        if (s.isEmpty()) return t.isEmpty();
        if (s.length() != t.length()) return false;

        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
