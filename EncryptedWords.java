/**
 * Facebook Interview Preparation
 * <p>
 * Encrypted Words
 * <p>
 * You've devised a simple encryption method for alphabetic strings that shuffles the characters in such a way that the resulting string is hard to quickly read, but is easy to convert back into the original string.
 * <p>
 * When you encrypt a string S, you start with an initially-empty resulting string R and append characters to it as follows:
 * - Append the middle character of S (if S has even length, then we define the middle character as the left-most of the two central characters)
 * - Append the encrypted version of the substring of S that's to the left of the middle character (if non-empty)
 * - Append the encrypted version of the substring of S that's to the right of the middle character (if non-empty)
 * <p>
 * For example, to encrypt the string "abc", we first take "b", and then append the encrypted version of "a" (which is just "a") and the encrypted version of "c" (which is just "c") to get "bac".
 * <p>
 * If we encrypt "abcxcba" we'll get "xbacbca". That is, we take "x" and then append the encrypted version "abc" and then append the encrypted version of "cba".
 * <p>
 * Input
 * S contains only lower-case alphabetic characters
 * 1 <= |S| <= 10,000
 * <p>
 * Output
 * Return string R, the encrypted version of S.
 * <p>
 * Example 1
 * S = "abc"
 * R = "bac"
 * <p>
 * Example 2
 * S = "abcd"
 * R = "bacd"
 * <p>
 * Example 3
 * S = "abcxcba"
 * R = "xbacbca"
 * <p>
 * Example 4
 * S = "facebook"
 * R = "eafcobok"
 */
public class EncryptedWords {
    public static void main(String[] args) {
        EncryptedWords sol = new EncryptedWords();
        System.out.println(sol.findEncryptedWord("abc")); // bac
        System.out.println(sol.findEncryptedWord("abcd")); // bacd
        System.out.println(sol.findEncryptedWord("abcxcba")); // xbacbca
        System.out.println(sol.findEncryptedWord("facebook")); // eafcobok
    }

    public String findEncryptedWord(String s) {
        return findEncryptedWord0(s, 0, s.length() - 1);
    }

    private String findEncryptedWord0(String s, int begin, int end) {
        if (begin > end) return "";
        int mid = begin + (end - begin) / 2;
        return s.charAt(mid) +
                findEncryptedWord0(s, begin, mid - 1) +
                findEncryptedWord0(s, mid + 1, end);
    }
}
