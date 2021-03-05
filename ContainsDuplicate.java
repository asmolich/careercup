import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode
 * 217. Contains Duplicate
 * https://leetcode.com/problems/contains-duplicate/
 * #Easy
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 4})); // false
        System.out.println(containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2})); // true
    }

    private static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            if (set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }
}
