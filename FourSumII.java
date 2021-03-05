import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 454. 4Sum II
 * https://leetcode.com/problems/4sum-ii/
 * #Medium #HashTable #MeetInTheMiddle
 */
public class FourSumII {
    public static void main(String[] args) {
        FourSumII sol = new FourSumII();
        System.out.println(sol.fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2})); // 2
    }

    public int fourSumCount(int[] a, int[] b, int[] c, int[] d) {
        if (a == null || a.length == 0 || b == null || b.length == 0 || c == null || c.length == 0 || d == null || d.length == 0)
            return 0;

        Map<Integer, Integer> counts = new HashMap<>();
        for (int i : a) {
            for (int j : b) {
                int sum = i + j;
                counts.put(sum, counts.getOrDefault(sum, 0) + 1);
            }
        }
        int count = 0;
        for (int i : c) {
            for (int j : d) {
                int sum = i + j;
                if (counts.containsKey(-sum)) {
                    count += counts.get(-sum);
                }
            }
        }
        return count;
    }
}
