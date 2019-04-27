import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InterviewBit. Substring Concatenation. https://www.interviewbit.com/problems/substring-concatenation/
 */
public class SubstringConcatenation {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(substringConcatenation(
//            "barfoothefoobarman",
//            new String[]{"foo", "bar"}
//        )));
//        System.out.println(Arrays.toString(substringConcatenation(
//            "cbbc",
//            new String[]{"b", "b", "b", "a", "c"}
//        )));
        System.out.println(Arrays.toString(substringConcatenation(
            "bcabbcaabbccacacbabccacaababcbb",
            new String[]{"c", "b", "a", "c", "a", "a", "a", "b", "c"}
        )));
//        System.out.println(Arrays.toString(substringConcatenation(
//            "bcababcabbaccbaabcccccbccbabbcc",
//            new String[]{"babccbcaba", "acacbaabba", "ccaccbbbab", "bccabacbba", "cbabccabbb", "babbcaaaab", "bbccccbcaa", "aacaabcbcc"}
//        )));
//        System.out.println(Arrays.toString(substringConcatenation(
//            "cacbbcabbacccacacaacacbbaccaabcbcbbcabbacc",
//            new String[]{"b", "a", "b", "a", "c", "a", "b"}
//        )));
//        System.out.println(Arrays.toString(substringConcatenation(
//            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//            new String[]{"aaa", "aaa", "aaa", "aaa", "aaa"}
//        )));
    }

    private static int[] substringConcatenation(String s, String[] l) {
        if (s == null || l == null || l.length == 0)
            return new int[0];

        final int length = l[0].length();
        final int count = l.length;
        if (count * length > s.length()) return new int[0];

        boolean duplicates = false;
        int hashSum = 0;
        final Map<String, Integer> map = new HashMap<>();
        for (String value : l) {
            if (!duplicates && map.containsKey(value)) duplicates = true;
            map.put(value, map.getOrDefault(value, 0) + 1);
            hashSum += hash(value.toCharArray(), length, 0);
        }

        System.out.println("hashSum = " + hashSum);

        List<Integer> result = new ArrayList<>();
        int aHash = 0;
        final char[] chars = s.toCharArray();
        for (int i = 0; i <= chars.length - length * count; i++) {
            for (int j = 0; j < count; j++) {
                aHash += hash(chars, length, i + j * length);
            }
            System.out.println("aHash = " + aHash);
            Map<String, Integer> m = map;
            if (duplicates) {
                m = new HashMap<>(map);
            }

            if (aHash == hashSum) { // equals
                boolean containsAll = true;
                for (int j = 0; j < count; j++) {
                    final String test = new String(chars, i + j * length, length);
                    System.out.println("test = " + test);
                    if (duplicates) {
                        final int value = m.getOrDefault(test, 0) - 1;
                        m.put(test, value);
                        if (value < 0) {
                            containsAll = false;
                            break;
                        }
                    } else {
                        if (!m.containsKey(test)) {
                            containsAll = false;
                            break;
                        }
                    }
                }
                if (containsAll) {
                    result.add(i);
                }
            }
            aHash = 0;
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private static int hash(char[] a, int length, int start) {
        if (a.length - start < length) return -1;

        int res = 0;
        for (int i = 0; i < length; i++) {
            res += (a[start + length - 1 - i] - 'a') * Math.pow(26, i);
        }
        return res;
    }
}
