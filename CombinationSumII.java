import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode
 * 40. Combination Sum II
 * https://leetcode.com/problems/combination-sum-ii/
 * #Medium #Backtracking
 */
public class CombinationSumII {
    public static void main(String[] args) {
        CombinationSumII sol = new CombinationSumII();
        System.out.println(sol.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8)); // [[1,1,6],[1,2,5],[1,7],[2,6]]
        System.out.println(sol.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5)); // [[1,2,2],[5]]
        System.out.println(sol.combinationSum2(new int[]{2}, 1)); // []
        System.out.println(sol.combinationSum2(new int[]{1}, 1)); // [[1]]
        System.out.println(sol.combinationSum2(new int[]{1}, 2)); // [[]]
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return Collections.emptyList();

        Set<List<Integer>> res = new HashSet<>();
        dfs(res, candidates, 0, 0, target, new ArrayDeque<>());
        return new ArrayList<>(res);
    }

    private void dfs(Set<List<Integer>> res, int[] candidates, int index, int sum, int target, ArrayDeque<Integer> deq) {
        if (sum > target || index > candidates.length) return;
        if (sum == target) {
            ArrayList<Integer> list = new ArrayList<>(deq);
            Collections.sort(list);
            res.add(list);
        }

        for (int i = index; i < candidates.length; i++) {
            deq.addLast(candidates[i]);
            dfs(res, candidates, i + 1, sum + candidates[i], target, deq);
            deq.removeLast();
        }
    }
}
