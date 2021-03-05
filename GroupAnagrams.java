import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode
 * 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 * #Medium #HashTable
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams sol = new GroupAnagrams();
        System.out.println(sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})); // [["bat"],["nat","tan"],["ate","eat","tea"]]
        System.out.println(sol.groupAnagrams(new String[]{""})); // [[""]]
        System.out.println(sol.groupAnagrams(new String[]{"a"})); // [["a"]]
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return Collections.emptyList();

        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String s = new String(array);
            map.computeIfAbsent(s, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
