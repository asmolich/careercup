import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode. Easy
 * 219. Contains Duplicate II
 * https://leetcode.com/problems/contains-duplicate-ii/
 */
public class ContainsDuplicateII {
    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3)); // true
        System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1)); // true
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2)); // false
    }

    private static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;

        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer index = map.get(num);
            if (index != null && i - index <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }
}
