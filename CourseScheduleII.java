import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode
 * 210. Course Schedule II
 * https://leetcode.com/problems/course-schedule-ii/
 * https://en.wikipedia.org/wiki/Topological_sorting#Algorithms
 * #Medium #TopologicalSort
 */
public class CourseScheduleII {
    public static void main(String[] args) {
        CourseScheduleII sol = new CourseScheduleII();
        System.out.println(Arrays.toString(sol.findOrder(1, new int[][]{}))); // [0]
        System.out.println(Arrays.toString(sol.findOrder(2, new int[][]{{1, 0}}))); // [0,1]
        System.out.println(Arrays.toString(sol.findOrder(2, new int[][]{{0, 1}, {1, 0}}))); // []
        System.out.println(Arrays.toString(sol.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}))); // [0,2,1,3]
        System.out.println(Arrays.toString(sol.findOrder(12, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}))); // [0,2,1,3]
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pre : prerequisites) {
            if (pre.length == 0) continue;
            List<Integer> list = graph.getOrDefault(pre[0], new ArrayList<>());
            list.add(pre[1]);
            graph.put(pre[0], list);
        }

        // Topological sort:
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] checked = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (isCycle(graph, i, stack, visited, checked)){
                return new int[0];
            }
        }
        return stack.stream().mapToInt(i -> i).toArray();
    }

    private boolean isCycle(Map<Integer, List<Integer>> graph, int i, Deque<Integer> stack, boolean[] visited, boolean[] checked) {
        if (checked[i]) return true;
        if (visited[i]) return false;

        visited[i] = true;
        checked[i] = true;
        List<Integer> children = graph.get(i);
        if (children != null && !children.isEmpty()) {
            for (Integer c : children) {
                if (isCycle(graph, c, stack, visited, checked)) return true;
            }
        }
        stack.add(i);
        checked[i] = false;
        return false;
    }
}
