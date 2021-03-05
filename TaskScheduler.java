/**
 * LeetCode
 * 621. Task Scheduler
 * https://leetcode.com/problems/task-scheduler/
 * #Mediun #Greedy #Queue
 */
public class TaskScheduler {
    public static void main(String[] args) {
        TaskScheduler sol = new TaskScheduler();
        System.out.println(sol.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2)); // 8
        System.out.println(sol.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0)); // 6
        System.out.println(sol.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2)); // 16
    }

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;

        int[] taskCounts = new int[26];
        int max = 0;
        for (char ch : tasks) {
            taskCounts[ch - 'A']++;
            max = Math.max(max, taskCounts[ch - 'A']);
        }

        // last stretch of tasks that are not covered by the cooldown from the "max" task
        int maxCount = 0;
        for (int taskCount : taskCounts) {
            if (max == taskCount) maxCount++;
        }
//        System.out.println(Arrays.toString(taskCounts));
//        System.out.println("max = " + maxCount);
//        System.out.println("maxCount = " + maxCount);

        int idle = n * (max - 1);
        int result = max + idle + (maxCount - 1);
        return Math.max(result, tasks.length);
    }
}
