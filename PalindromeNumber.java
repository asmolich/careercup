/**
 * LeetCode
 * 9. Palindrome Number
 * https://leetcode.com/problems/palindrome-number/
 * #Easy
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        PalindromeNumber sol = new PalindromeNumber();
        System.out.println(sol.isPalindrome(121)); // true
        System.out.println(sol.isPalindrome(-121)); // false
        System.out.println(sol.isPalindrome(10)); // false
        System.out.println(sol.isPalindrome(101)); // true
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;

        int res = 0;
        int t = x;
        while (t != 0) {
            res *= 10;
            res += Math.abs(t % 10);
            t /= 10;
        }
        return res == x;
    }
}
