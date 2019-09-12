import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PowerSet. LeetCode.
 * 78. Subsets https://leetcode.com/problems/subsets/
 */
public class Subsets {
    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
    }

    private static List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();

        int length = nums.length;
        if (length > 31) {
            System.out.println("too much elements");
            return Collections.emptyList();
        }

        int size = 1 << length;
        List<List<Integer>> result = new ArrayList<>(size);
        result.add(Collections.emptyList());
        for (int i = 1; i < size; i++) {
            List<Integer> current = new ArrayList<>();
            int bitmask = i;
            while (bitmask != 0) {
                int index = Integer.numberOfTrailingZeros(bitmask);
                current.add(nums[index]);
                bitmask &= ~(1 << index);
            }
            result.add(current);
        }
        return result;
    }

    private static List<List<Integer>> subsetsWithDup(int[] nums) {
        return Collections.emptyList();
    }
}
