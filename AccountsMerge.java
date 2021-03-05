import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode
 * 721. Accounts Merge
 * https://leetcode.com/problems/accounts-merge/
 * #Medium
 */
public class AccountsMerge {
    public static void main(String[] args) {
        AccountsMerge sol = new AccountsMerge();
        System.out.println(sol.accountsMerge(Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("Mary", "mary@mail.com")
        ))); // [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["John","johnnybravo@mail.com"],["Mary","mary@mail.com"]]
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.isEmpty()) return Collections.emptyList();

        Map<String, String> emailToName = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> account : accounts) {
            String name = null;
            for (String email : account) {
                if (name == null) {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x -> new ArrayList<>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x -> new ArrayList<>()).add(email);
                emailToName.put(email, name);
            }
        }
        Set<String> seen = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        for (String email : graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Deque<String> stack = new ArrayDeque<>();
                stack.add(email);
                List<String> component = new ArrayList<>();
                while (!stack.isEmpty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei : graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                res.add(component);
            }
        }
        return res;
    }
}
