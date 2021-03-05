import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 581. Shortest Unsorted Continuous Subarray
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 * #Medium
 */
public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        ShortestUnsortedContinuousSubarray sol = new ShortestUnsortedContinuousSubarray();
        System.out.println(sol.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15})); // 5
        System.out.println(sol.findUnsortedSubarray(new int[]{1, 2, 3, 4})); // 0
        System.out.println(sol.findUnsortedSubarray(new int[]{1})); // 0
        System.out.println(sol.findUnsortedSubarray(new int[]{4, 3, 2, 1})); // 4
        System.out.println(sol.findUnsortedSubarray(new int[]{2, 6, 4, 18, 10, 19, 25})); // 4
        System.out.println(sol.findUnsortedSubarray(new int[]{2, 3, 4, 3, 18, 19, 25})); // 2
    }

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        // Input: nums = [2,6,4,8,10,9,15]
        // Output: 5
        // Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order
        // to make the whole array sorted in ascending order.

        int n = nums.length;
        Deque<Integer> deq = new ArrayDeque<>();
        int start = n - 1;
        int end = 0;
        for (int i = 0; i < n; i++) {
            while (!deq.isEmpty() && nums[deq.getLast()] > nums[i]) {
                int last = deq.removeLast();
                start = Math.min(start, last);
            }
            deq.add(i);
        }
        deq.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!deq.isEmpty() && nums[deq.getLast()] < nums[i]) {
                int r = deq.removeLast();
                end = Math.max(end, r);
            }
            deq.add(i);
        }
        return end - start > 0 ? end - start + 1 : 0;
    }

    @SuppressWarnings("unused")
    public int findUnsortedSubarray2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int n = nums.length;

        int i = 0, j = n - 1;
        while (i + 1 < n && nums[i] <= nums[i + 1]) i++;
        if (i + 1 == n) return 0;

        while (j > i && nums[j - 1] <= nums[j]) j--;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            min = Math.min(min, nums[k]);
            max = Math.max(max, nums[k]);
        }

        while (i > 0 && nums[i - 1] > min) i--;
        while (j < n - 1 && nums[j + 1] < max) j++;

        return j - i + 1;
    }
}
