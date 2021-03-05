import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 525. Contiguous Array
 * https://leetcode.com/problems/contiguous-array/
 * #Medium #HashTable
 */
public class ContiguousArray {
    public static void main(String[] args) {
        ContiguousArray sol = new ContiguousArray();
        System.out.println(sol.findMaxLength(new int[]{0, 1})); // 2
        System.out.println(sol.findMaxLength(new int[]{0, 1, 0})); // 2
        System.out.println(sol.findMaxLength(new int[]{1, 1, 1, 0, 1, 0, 0, 0, 1, 0})); // 10
        System.out.println(sol.findMaxLength(new int[]{1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1})); // 10
        System.out.println(sol.findMaxLength(new int[]{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0})); // 10
    }

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) count++;
            else count--;
            if (map.containsKey(count)) {
                len = Math.max(len, i - map.get(count));
            } else map.put(count, i);
        }
        return len;
    }
}
