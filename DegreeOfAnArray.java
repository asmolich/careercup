import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 697. Degree of an Array
 * https://leetcode.com/problems/degree-of-an-array/
 * #Easy
 */
public class DegreeOfAnArray {
    public static void main(String[] args) {
        DegreeOfAnArray sol = new DegreeOfAnArray();
        System.out.println(sol.findShortestSubArray(new int[]{1, 2, 2, 3, 1})); // 2
        System.out.println(sol.findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 2})); // 6
        System.out.println(sol.findShortestSubArray(new int[]{2, 1, 1, 2, 1, 3, 3, 3, 1, 3, 1, 3, 2})); // 7
    }

    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        for (int num : nums) {
            int value = freq.getOrDefault(num, 0) + 1;
            if (maxFreq < value) {
                maxFreq = value;
            }
            freq.put(num, value);
        }
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == maxFreq) {
                Integer degree = entry.getKey();
                int left = 0;
                int right = n - 1;
                while (left <= right && nums[left] != degree) {
                    left++;
                }
                while (left <= right && nums[right] != degree) {
                    right--;
                }
                min = Math.min(min, right - left + 1);
            }
        }
        return min;
    }
}
