/**
 * LeetCode
 * 1646. Get Maximum in Generated Array
 * https://leetcode.com/problems/get-maximum-in-generated-array/
 * #Easy
 */
public class GetMaximumIInGeneratedArray {
    public static void main(String[] args) {
        GetMaximumIInGeneratedArray sol = new GetMaximumIInGeneratedArray();
        for (int i = 0; i <= 100; i++) {
            System.out.println(sol.getMaximumGenerated(i));
        }
    }

    //nums[0] = 0
    //nums[1] = 1
    //nums[2 * i] = nums[i] when 2 <= 2 * i <= n
    //nums[2 * i + 1] = nums[i] + nums[i + 1] when 2 <= 2 * i + 1 <= n
    public int getMaximumGenerated(int n) {
        //  nums[0] = 0
        //  nums[1] = 1
        //  nums[(1 * 2) = 2] = nums[1] = 1
        //  nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
        //  nums[(2 * 2) = 4] = nums[2] = 1
        //  nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
        //  nums[(3 * 2) = 6] = nums[3] = 2
        //  nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
        if (n < 2) return n;
        int[] nums = new int[n + 1];
        nums[1] = 1;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (2 * i <= n) nums[2 * i] = nums[i];
            if (2 * i + 1 <= n) nums[2 * i + 1] = nums[i] + nums[i + 1];
            max = Math.max(max, nums[i]);
        }
        //System.out.println(Arrays.toString(nums));
        return max;
    }
}
