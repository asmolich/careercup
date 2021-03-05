import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode
 * 18. 4Sum
 * https://leetcode.com/problems/4sum/
 * #Medium #KSum #TwoPointer
 * https://skyxu.me/2018/08/05/a-generic-solution-to-k-sum-problems/
 * Tip: Evaluate #MeetInTheMiddle approach https://www.youtube.com/watch?v=57SUNQL4JFA
 * https://www.geeksforgeeks.org/find-four-elements-that-sum-to-a-given-value-set-2/
 * https://leetcode.com/problems/4sum/discuss/183289/Java-O(n2)-meet-in-the-middle
 */
public class FourSum {
    public static void main(String[] args) {
        FourSum sol = new FourSum();
        System.out.println(sol.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println(sol.fourSumMITM(new int[]{1, 0, -1, 0, -2, 2}, 0)); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println(sol.fourSum(new int[]{}, 0)); // []
        System.out.println(sol.fourSumMITM(new int[]{}, 0)); // []
        System.out.println(sol.fourSum(new int[]{0, 0, 0, 0}, 0)); // [[0,0,0,0]]
        System.out.println(sol.fourSumMITM(new int[]{0, 0, 0, 0}, 0)); // [[0,0,0,0]]
        System.out.println(sol.fourSum(new int[]{0, 0, 0, 0, 0}, 0)); // [[0,0,0,0]]
        System.out.println(sol.fourSumMITM(new int[]{0, 0, 0, 0, 0}, 0)); // [[0,0,0,0]]
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) return Collections.emptyList();
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                int s = target - nums[i] - nums[j];
                int lo = j + 1, hi = n - 1;
                while (lo < hi) {
                    int sum = nums[lo] + nums[hi];
                    if (sum == s) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        lo++;
                        hi--;
                    } else if (sum < s) lo++;
                    else hi--;
                }
            }
        }
        return new ArrayList<>(res);
    }

    // Meet in the Middle
    public List<List<Integer>> fourSumMITM(int[] nums, int target) {
        if (nums == null || nums.length < 4) return Collections.emptyList();

        int n = nums.length;
        Map<Integer, List<int[]>> pairSum = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                pairSum.computeIfAbsent(sum, k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                if (pairSum.containsKey(target - sum)) {
                    List<int[]> pairs = pairSum.get(target - sum);
                    for (int[] p : pairs) {
                        if (p[0] != i && p[0] != j && p[1] != i && p[1] != j) {
                            List<Integer> list = Arrays.asList(nums[i], nums[j], nums[p[0]], nums[p[1]]);
                            Collections.sort(list);
                            result.add(list);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }
}
