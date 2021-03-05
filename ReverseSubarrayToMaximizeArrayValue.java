/**
 * LeetCode
 * 1330. Reverse Subarray To Maximize Array Value
 * https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/
 * #Hard #Greedy
 */
public class ReverseSubarrayToMaximizeArrayValue {
    public static void main(String[] args) {
        ReverseSubarrayToMaximizeArrayValue sol = new ReverseSubarrayToMaximizeArrayValue();
        System.out.println(sol.maxValueAfterReverse(new int[]{2, 3, 1, 5, 4})); // 10
        System.out.println(sol.maxValueAfterReverse(new int[]{2, 4, 9, 24, 2, 1, 10})); // 68
    }

    public int maxValueAfterReverse(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int sum = 0;
        int gain = 0;
        int hi = Integer.MIN_VALUE;
        int lo = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            int n1 = nums[i];
            int n2 = nums[i + 1];
            sum += Math.abs(n1 - n2);
            gain = Math.max(gain, Math.max(
                    Math.abs(nums[0] - n2) - Math.abs(n1 - n2),
                    Math.abs(nums[n - 1] - n1) - Math.abs(n1 - n2)));
            hi = Math.max(hi, Math.min(n1, n2));
            lo = Math.min(lo, Math.max(n1, n2));
        }
        return sum + Math.max(gain, (hi - lo) * 2);
    }
}
