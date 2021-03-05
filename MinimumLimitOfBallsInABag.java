/**
 * LeetCode
 * 1760. Minimum Limit of Balls in a Bag
 * https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/
 * #Medium
 * https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/discuss/1064548/JavaC%2B%2BPython-Binary-Search
 */
public class MinimumLimitOfBallsInABag {
    public static void main(String[] args) {
        MinimumLimitOfBallsInABag sol = new MinimumLimitOfBallsInABag();
        System.out.println(sol.minimumSize(new int[]{
                431, 922, 158, 60, 192, 14, 788, 146, 788, 775, 772, 792, 68, 143, 376, 375, 877, 516, 595, 82, 56, 704, 160, 403, 713, 504, 67, 332, 26
        }, 80));
    }

    public int minimumSize(int[] nums, int k) {
        int lo = 1, hi = 1;
        for (int i : nums) hi = Math.max(hi, i);
        while (lo < hi) {
            int mid = (hi + lo) >>> 1;
            int sum = 0;
            for (int i : nums) {
                sum += (i - 1) / mid;
            }
            if (sum > k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return hi;
    }
}
