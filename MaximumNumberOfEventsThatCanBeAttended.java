import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode
 * 1353. Maximum Number of Events That Can Be Attended
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 * #Medium #HashTable #Heap #PriorityQueue
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/discuss/1034150/Java-Priority-Queue
 */
public class MaximumNumberOfEventsThatCanBeAttended {
    public static void main(String[] args) {
        MaximumNumberOfEventsThatCanBeAttended sol = new MaximumNumberOfEventsThatCanBeAttended();
        System.out.println(sol.maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}})); // 3
        System.out.println(sol.maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 2}})); // 4
        System.out.println(sol.maxEvents(new int[][]{{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}})); // 4
        System.out.println(sol.maxEvents(new int[][]{{1, 4}, {4, 5}, {2, 2}, {3, 5}, {1, 1}})); // 5
        System.out.println(sol.maxEvents(new int[][]{{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}})); // 5
        System.out.println(sol.maxEvents(new int[][]{{1, 100000}})); // 1
        System.out.println(sol.maxEvents(new int[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}})); // 7
    }

    public int maxEvents(int[][] events) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int lastDay = Integer.MIN_VALUE;
        for (int[] event : events) {
            map.computeIfAbsent(event[0], k -> new ArrayList<>()).add(event[1]);
            lastDay = Math.max(lastDay, event[1]);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int day = 1;
        int count = 0;
        while (day <= lastDay) {
            if (map.containsKey(day)) pq.addAll(map.get(day));
            boolean attended = false;
            while (!pq.isEmpty()) {
                if (pq.poll() >= day) {
                    attended = true;
                    break;
                }
            }
            day++;
            if (attended) count++;
        }
        return count;
    }
}
