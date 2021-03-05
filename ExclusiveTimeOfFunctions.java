import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode
 * 636. Exclusive Time of Functions
 * https://leetcode.com/problems/exclusive-time-of-functions/
 * #Medium
 */
public class ExclusiveTimeOfFunctions {
    public static void main(String[] args) {
        ExclusiveTimeOfFunctions sol = new ExclusiveTimeOfFunctions();
        System.out.println(Arrays.toString(sol.exclusiveTime(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6")))); // [3,4]
        System.out.println(Arrays.toString(sol.exclusiveTime(1, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7")))); // [8]
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        if (logs == null || logs.isEmpty()) return new int[0];

        int[] res = new int[n];
        Deque<int[]> deq = new ArrayDeque<>();
        for (String log : logs) {
            String[] split = log.split(":");
            int id = Integer.parseInt(split[0]);
            int time = Integer.parseInt(split[2]);

            if (split[1].equals("start")) {
                deq.add(new int[]{time, 0});
            } else {
                int[] it = deq.removeLast();
                res[id] += time - it[0] + 1 - it[1];
                if (!deq.isEmpty()) deq.getLast()[1] += time - it[0] + 1;
            }
        }
        return res;
    }
}
