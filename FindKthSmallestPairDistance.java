import java.util.Arrays;

/**
 * LeetCode
 * 719. Find K-th Smallest Pair Distance
 * https://leetcode.com/problems/find-k-th-smallest-pair-distance/
 * #Hard #BinarySearch #SlidingWindow
 */
public class FindKthSmallestPairDistance {
    public static void main(String[] args) {
        FindKthSmallestPairDistance sol = new FindKthSmallestPairDistance();
        System.out.println(sol.smallestDistancePair(new int[]{1, 3, 1}, 1)); // 0
        System.out.println(sol.smallestDistancePair(new int[]{1, 3, 1}, 2)); // 2
        System.out.println(sol.smallestDistancePair(new int[]{1, 3, 1}, 3)); // 2
    }

    public int smallestDistancePair(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;

        Arrays.sort(nums);

        int n = nums.length;
        int lo = 0;
        int hi = nums[n - 1] - nums[0];
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            int count = 0;
            int left = 0;
            for (int right = 0; right < n; right++) {
                while (nums[right] - nums[left] > mid) left++;
                count += right - left;
            }
            //count = number of pairs with distance <= mid
            if (count >= k) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
