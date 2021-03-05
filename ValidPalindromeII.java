/**
 * LeetCode
 * 680. Valid Palindrome II
 * https://leetcode.com/problems/valid-palindrome-ii/
 * #Easy
 */
public class ValidPalindromeII {
    public static void main(String[] args) {
        ValidPalindromeII sol = new ValidPalindromeII();
        System.out.println(sol.validPalindrome("aba")); // true
        System.out.println(sol.validPalindrome("abca")); // true
        System.out.println(sol.validPalindrome("ttttttttttttaa")); // false
        System.out.println(sol.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga")); // true
    }

    public boolean validPalindrome(String s) {
        if (s == null || s.isEmpty()) return false;
        int n = s.length();

        int lo = 0;
        int hi = n - 1;
        while (lo < hi) {
            char ch1 = s.charAt(lo);
            char ch2 = s.charAt(hi);
            if (ch1 == ch2) {
                lo++;
                hi--;
            } else {
                return isPalindrome(s, lo + 1, hi) || isPalindrome(s, lo, hi - 1);
            }
        }

        return true;
    }

    private boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi))
                return false;
            lo++;
            hi--;
        }
        return true;
    }
}
