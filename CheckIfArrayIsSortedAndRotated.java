/**
 * LeetCode
 * 1752. Check if Array Is Sorted and Rotated
 * https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
 * #Easy #Rotated
 */
public class CheckIfArrayIsSortedAndRotated {
    public static void main(String[] args) {
        CheckIfArrayIsSortedAndRotated sol = new CheckIfArrayIsSortedAndRotated();
        System.out.println(sol.check(new int[]{3, 4, 5, 1, 2})); // true
        System.out.println(sol.check(new int[]{2, 1, 3, 4})); // false
        System.out.println(sol.check(new int[]{1, 2, 3})); // true
        System.out.println(sol.check(new int[]{1, 1, 1})); // true
        System.out.println(sol.check(new int[]{2, 1})); // true
    }

    public boolean check(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (nums[max] < nums[i]) max = i;
        }
        while (max < n - 1 && nums[max] == nums[max + 1]) max++;
        for (int i = 1; i < n; i++) {
            int idx2 = (max + 1 + i) % n;
            int idx1 = (idx2 - 1 + n) % n;
            if (nums[idx1] > nums[idx2]) return false;
        }
        return true;
    }
}
