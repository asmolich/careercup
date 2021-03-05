import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode
 * 15. 3Sum
 * https://leetcode.com/problems/3sum/
 * #Medium #KSum #TwoPointer #BinarySearch
 * https://skyxu.me/2018/08/05/a-generic-solution-to-k-sum-problems/
 */
public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum sol = new ThreeSum();
        System.out.println(sol.threeSum(new int[]{-1, 0, 1, 2, -1, -4})); // [[-1,-1,2],[-1,0,1]]
        System.out.println(sol.threeSum(new int[]{})); // []
        System.out.println(sol.threeSum(new int[]{0})); // []
        System.out.println(sol.threeSum(new int[]{0, 0, 0})); // [[0,0,0]]
        System.out.println(sol.threeSum(new int[]{-2, 0, 0, 2, 2})); // [[-2,0,2]]
    }

    @SuppressWarnings("unused")
    public List<List<Integer>> threeSumBS(int[] nums) {
        if (nums == null || nums.length < 3) return Collections.emptyList();
        // a + b + c = 0
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int c = nums[i] + nums[j];
                int idx = Arrays.binarySearch(nums, j + 1, n, -c);
                if (idx > j) {
                    res.add(Arrays.asList(nums[i], nums[j], -c));
                }
            }
        }
        return new ArrayList<>(res);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return Collections.emptyList();

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //twoSum(nums, i + 1, -nums[i], result);
            int lo = i + 1, hi = n - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                if (sum == -nums[i]) {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    lo++;
                    hi--;
                    while (lo < hi && nums[lo] == nums[lo - 1]) lo++;
                    while (lo < hi && nums[hi] == nums[hi + 1]) hi--;
                } else if (sum < -nums[i]) lo++;
                else hi--;
            }
        }

        return res;
    }

    @SuppressWarnings("unused")
    private void twoSum(int[] nums, int left, int target, List<List<Integer>> result) {
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                result.add(Arrays.asList(-target, nums[left], nums[right]));
                left++;
                right--;

                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            }
        }
    }
}
