import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode
 * 336. Palindrome Pairs
 * https://leetcode.com/problems/palindrome-pairs/
 * #Hard #HashTable #Trie
 * https://www.youtube.com/watch?v=gHKHhjX6S8A
 */
public class PalindromePairs {
    public static void main(String[] args) {
        PalindromePairs sol = new PalindromePairs();
        System.out.println(sol.palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"})); // [[0,1],[1,0],[3,2],[2,4]]
        System.out.println(sol.palindromePairs(new String[]{"bat", "tab", "cat"})); // [[0,1],[1,0]]
        System.out.println(sol.palindromePairs(new String[]{"a", ""})); // [[0,1],[1,0]]
    }

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int wordIndex = -1;
        final List<Integer> suffixIsPalindrome = new ArrayList<>();
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) return Collections.emptyList();

        TrieNode root = new TrieNode();
        int n = words.length;
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            add(words[i], i, root);
        }

        for (int i = 0; i < n; i++) {
            search(words[i], i, root, res);
        }

        return res;
    }

    private void search(String word, int idx, TrieNode root, List<List<Integer>> res) {
        TrieNode node = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char ch = word.charAt(i);
            int j = ch - 'a';
            if (node.wordIndex != -1 && isPalindrome(word, i, n - 1)) {
                res.add(Arrays.asList(idx, node.wordIndex));
            }
            if (node.children[j] == null) return;
            node = node.children[j];
        }

        // aaaa
        if (node.wordIndex != -1 && node.wordIndex != idx) {
            res.add(Arrays.asList(idx, node.wordIndex));
        }

        for (int j : node.suffixIsPalindrome) {
            res.add(Arrays.asList(idx, j));
        }
    }

    private void add(String word, int idx, TrieNode root) {
        TrieNode node = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char ch = word.charAt(n - 1 - i);
            int j = ch - 'a';
            if (isPalindrome(word, 0, i)) {
                node.suffixIsPalindrome.add(idx);
            }
            if (node.children[j] == null) node.children[j] = new TrieNode();
            node = node.children[j];
        }
        node.wordIndex = idx;
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }
        return true;
    }
}
