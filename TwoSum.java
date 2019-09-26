import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode. 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{}, 1)));
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [0, 1]
    }

    private static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];

        int length = nums.length;
        Map<Integer, Integer> ind = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            Integer idx = ind.get(target - nums[i]);
            if (idx != null) return new int[]{idx, i};
            ind.put(nums[i], i);
        }
        return new int[0];
    }
}
