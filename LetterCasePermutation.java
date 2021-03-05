import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode
 * 784. Letter Case Permutation
 * https://leetcode.com/problems/letter-case-permutation/
 * #Medium #Permutation
 */
public class LetterCasePermutation {
    public static void main(String[] args) {
        LetterCasePermutation sol = new LetterCasePermutation();
        System.out.println(sol.letterCasePermutation("a1b2")); // ["a1b2","a1B2","A1b2","A1B2"]
        System.out.println(sol.letterCasePermutation("3z4")); // ["3z4","3Z4"]
        System.out.println(sol.letterCasePermutation("12345")); // ["12345"]
        System.out.println(sol.letterCasePermutation("0")); // ["0"]
    }

    public List<String> letterCasePermutation(String s) {
        if (s == null || s.isEmpty()) return Collections.emptyList();

        char[] chars = s.toCharArray();
        List<String> result = new ArrayList<>();
        letterCasePermutation0(chars, 0, result);
        return result;
    }

    private void letterCasePermutation0(char[] chars, int idx, List<String> result) {
        for (int i = idx; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                char[] copy = Arrays.copyOf(chars, chars.length);
                copy[i] = Character.toUpperCase(chars[i]);
                letterCasePermutation0(copy, i + 1, result);
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                char[] copy = Arrays.copyOf(chars, chars.length);
                copy[i] = Character.toLowerCase(chars[i]);
                letterCasePermutation0(copy, i + 1, result);
            }
        }
        result.add(new String(chars));
    }
}
