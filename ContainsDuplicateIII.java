import java.util.TreeSet;

/**
 * LeetCode. Medium
 * 220. Contains Duplicate III
 * https://leetcode.com/problems/contains-duplicate-iii/
 */
public class ContainsDuplicateIII {
    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 2}, 10, 0)); // true
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0)); // true
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2)); // true
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3)); // false
        System.out.println(containsNearbyAlmostDuplicate(new int[]{-1, 2147483647}, 1, 2147483647)); // false
        System.out.println(containsNearbyAlmostDuplicate(new int[]{2147483647, -2147483647}, 1, 2147483647)); // false
    }

    private static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k < 0 || t < 0) return false;

        int n = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Integer num = nums[i];
            if (set.contains(num)) return true;
            Integer lower = set.lower(num);
            if (lower != null && (long) num - lower <= t) return true;
            Integer higher = set.higher(num);
            if (higher != null && (long) higher - num <= t) return true;

            set.add(num);
            if (set.size() >= k && i - k >= 0) {
                set.remove(nums[i - k]);
            }
        }

        return false;
    }
}
