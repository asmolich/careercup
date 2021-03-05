import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * 1306. Jump Game III
 * https://leetcode.com/problems/jump-game-iii/
 * #Medium
 */
public class JumpGameIII {
    public static void main(String[] args) {
        JumpGameIII sol = new JumpGameIII();
        System.out.println(sol.canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5)); // true
        System.out.println(sol.canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 0)); // true
        System.out.println(sol.canReach(new int[]{3, 0, 2, 1, 2}, 2)); // false
    }

    public boolean canReach(int[] nums, int start) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        Deque<Integer> deq = new ArrayDeque<>();
        deq.add(start);
        while (!deq.isEmpty()) {
            int idx = deq.poll();
            int num = nums[idx];
            if (num == 0) return true;
            if (visited[idx]) continue;
            visited[idx] = true;
            if (0 <= idx + num && idx + num < n) deq.add(idx + num);
            if (0 <= idx - num && idx - num < n) deq.add(idx - num);
        }
        return false;
    }
}
