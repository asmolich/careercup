import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 * #Easy #KSum #HashTable
 * https://skyxu.me/2018/08/05/a-generic-solution-to-k-sum-problems/
 */
public class TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{}, 1))); // []
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [0, 1]
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6))); // [1, 2]
        System.out.println(Arrays.toString(twoSum(new int[]{3, 3}, 6))); // [0, 1]
    }

    private static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        Map<Integer, Integer> ind = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            Integer idx = ind.get(target - nums[i]);
            if (idx != null) return new int[]{idx, i};
            ind.put(nums[i], i);
        }
        return new int[0];
    }
}
