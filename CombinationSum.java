import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode
 * 39. Combination Sum
 * https://leetcode.com/problems/combination-sum/
 * #Medium #Backtracking
 */
public class CombinationSum {
    public static void main(String[] args) {
        CombinationSum sol = new CombinationSum();
        System.out.println(sol.combinationSum(new int[]{2, 3, 6, 7}, 7)); // [[2,2,3],[7]]
        System.out.println(sol.combinationSum(new int[]{2, 3, 5}, 8)); // [[2,2,2,2],[2,3,3],[3,5]]
        System.out.println(sol.combinationSum(new int[]{2}, 1)); // []
        System.out.println(sol.combinationSum(new int[]{1}, 1)); // [[1]]
        System.out.println(sol.combinationSum(new int[]{1}, 2)); // [[1,1]]
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return Collections.emptyList();

        List<List<Integer>> res = new ArrayList<>();
        dfs(res, candidates, 0, 0, target, new ArrayDeque<>());
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] candidates, int index, int sum, int target, ArrayDeque<Integer> deq) {
        if (sum > target || index > candidates.length) return;
        if (sum == target) res.add(new ArrayList<>(deq));

        for (int i = index; i < candidates.length; i++) {
            deq.addLast(candidates[i]);
            dfs(res, candidates, i, sum + candidates[i], target, deq);
            deq.removeLast();
        }
    }
}
