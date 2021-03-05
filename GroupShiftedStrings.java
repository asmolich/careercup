import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * LeetCode
 * 249. Group Shifted Strings
 * https://leetcode.com/problems/group-shifted-strings/
 * https://tenderleo.gitbooks.io/leetcode-solutions-/content/GoogleEasy/249.html
 * #Medium #Hash #HashTable
 */
public class GroupShiftedStrings {
    public static void main(String[] args) {
        GroupShiftedStrings sol = new GroupShiftedStrings();
        System.out.println(sol.groupStrings(new String[]{})); // []
        System.out.println(sol.groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "xmn", "az", "ba", "a", "z"})); // [["abc","bcd","xyz"],["az","ba"],["acef"],["xmn"],["a","z"]]
    }

    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) return Collections.emptyList();

        Map<String, TreeSet<String>> map = new HashMap<>();
        for (String s : strings) {
            String hash = getHash(s);
            if (map.containsKey(hash)) {
                map.get(hash).add(s);
            } else {
                TreeSet<String> set = new TreeSet<>();
                set.add(s);
                map.put(hash, set);
            }
        }
        return map.values().stream().map(ArrayList::new).collect(Collectors.toList());
    }

    private String getHash(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append((s.charAt(i) - s.charAt(0) + 26) % 26);
            sb.append('.');
        }

        return sb.toString();
    }
}
