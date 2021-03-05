/**
 * LeetCode
 * 953. Verifying an Alien Dictionary
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 * #Easy #HashTable
 */
public class VerifyingAnAlienDictionary {
    public static void main(String[] args) {
        VerifyingAnAlienDictionary sol = new VerifyingAnAlienDictionary();
        System.out.println(sol.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz")); // true
        System.out.println(sol.isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz")); // false
        System.out.println(sol.isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz")); // false
    }

    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            index[order.charAt(i) - 'a'] = i;
        }

        search:
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            int lim = Math.min(word1.length(), word2.length());
            // String.compareTo
            for (int k = 0; k < lim; k++) {
                if (word1.charAt(k) != word2.charAt(k)) {
                    if (index[word1.charAt(k) - 'a'] > index[word2.charAt(k) - 'a'])
                        return false;
                    continue search;
                }
            }

            // If we didn't find a first difference, the
            // words are like ("app", "apple").
            if (word1.length() > word2.length())
                return false;
        }

        return true;
    }
}
