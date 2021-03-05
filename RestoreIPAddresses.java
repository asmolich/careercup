import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode
 * 93. Restore IP Addresses
 * https://leetcode.com/problems/restore-ip-addresses/
 * #Medium #Backtracking
 */
public class RestoreIPAddresses {
    public static void main(String[] args) {
        RestoreIPAddresses sol = new RestoreIPAddresses();
        System.out.println(sol.restoreIpAddresses("25525511135")); // ["255.255.11.135","255.255.111.35"]
        System.out.println(sol.restoreIpAddresses("0000")); // ["0.0.0.0"]
        System.out.println(sol.restoreIpAddresses("1111")); // ["1.1.1.1"]
        System.out.println(sol.restoreIpAddresses("010010")); // ["0.10.0.10","0.100.1.0"]
        System.out.println(sol.restoreIpAddresses("101023")); // ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
    }

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.isEmpty()) return Collections.emptyList();
        List<String> res = new ArrayList<>();
        dfs(s, new ArrayList<>(), res);
        return res;
    }

    private void dfs(String s, List<String> components, List<String> res) {
        if (components.size() == 4 || s.length() == 0) {
            if (components.size() == 4 && s.length() == 0)
                res.add(String.join(".", components));
            return;
        }
        List<String> newComponents = new ArrayList<>(components);
        newComponents.add(s.substring(0, 1));
        dfs(s.substring(1), newComponents, res);
        if (s.length() > 1 && s.charAt(0) != '0') {
            newComponents.set(newComponents.size() - 1, s.substring(0, 2));
            dfs(s.substring(2), newComponents, res);
            if (s.length() > 2 && Integer.parseInt(s.substring(0, 3)) <= 255) {
                newComponents.set(newComponents.size() - 1, s.substring(0, 3));
                dfs(s.substring(3), newComponents, res);
            }
        }
    }
}
