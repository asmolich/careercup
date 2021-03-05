import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 * #Medium
 */
public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule sol = new CourseSchedule();
        System.out.println(sol.canFinish(2, new int[][]{{1, 0}})); // true
        System.out.println(sol.canFinish(2, new int[][]{{1, 0}, {0, 1}})); // false
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pre : prerequisites) {
            List<Integer> list = graph.getOrDefault(pre[0], new ArrayList<>());
            list.add(pre[1]);
            graph.put(pre[0], list);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] checked = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (isCycle(graph, i, visited, checked)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCycle(Map<Integer, List<Integer>> graph, int i, boolean[] visited, boolean[] checked) {
        if (checked[i]) return true;
        if (visited[i]) return false;

        visited[i] = true;
        checked[i] = true;
        List<Integer> children = graph.get(i);
        if (children != null && !children.isEmpty()) {
            for (Integer c : children) {
                if (isCycle(graph, c, visited, checked)) return true;
            }
        }
        checked[i] = false;
        return false;
    }
}
