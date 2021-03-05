import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode
 * 216. Combination Sum III
 * https://leetcode.com/problems/combination-sum-iii/
 * #Medium #Backtracking
 */
public class CombinationSumIII {
    public static void main(String[] args) {
        CombinationSumIII sol = new CombinationSumIII();
        System.out.println(sol.combinationSum3(3, 7)); // [[1,2,4]]
        System.out.println(sol.combinationSum3(3, 9)); // [[1,2,6],[1,3,5],[2,3,4]]
        System.out.println(sol.combinationSum3(4, 1)); // []
        System.out.println(sol.combinationSum3(3, 2)); // []
        System.out.println(sol.combinationSum3(9, 45)); // [[1,2,3,4,5,6,7,8,9]]
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k < 1 || k > 9 || n > 45) return Collections.emptyList();

        List<List<Integer>> res = new ArrayList<>();
        dfs(res, 1, 0, k, n, new ArrayDeque<>());
        return res;
    }

    private void dfs(List<List<Integer>> res, int index, int sum, int size, int target, ArrayDeque<Integer> deq) {
        if (sum == target) {
            if (deq.size() == size) res.add(new ArrayList<>(deq));
            else return;
        }
        if (sum > target || index > 9) return;

        for (int i = index; i <= 9; i++) {
            deq.addLast(i);
            dfs(res, i + 1, sum + i, size, target, deq);
            deq.removeLast();
        }
    }
}
