import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode
 * 1036. Escape a Large Maze
 * https://leetcode.com/problems/escape-a-large-maze/
 * #Hard
 * <p>
 * Hint: 0 <= blocked.length <= 200
 */
public class EscapeALargeMaze {
    public static void main(String[] args) {
        EscapeALargeMaze sol = new EscapeALargeMaze();
        System.out.println(sol.isEscapePossible(new int[][]{}, new int[]{0, 0}, new int[]{999999, 999999})); // true
        System.out.println(sol.isEscapePossible(new int[][]{{0, 1}, {1, 0}}, new int[]{0, 0}, new int[]{0, 2})); // false
        System.out.println(sol.isEscapePossible(new int[][]{{0, 1}}, new int[]{0, 0}, new int[]{0, 5})); // true
        System.out.println(sol.isEscapePossible(new int[][]{{0, 1}}, new int[]{0, 0}, new int[]{999999, 999999})); // true
        System.out.println(sol.isEscapePossible(new int[][]{{0, 3}, {1, 0}, {1, 1}, {1, 2}, {1, 3}}, new int[]{0, 0}, new int[]{0, 2})); // true
        //    0  1  2  3
        // 0           x
        // 1  x  x  x  x
        // 2
        // 3
        System.out.println(sol.isEscapePossible(new int[][]{{10, 9}, {9, 10}, {10, 11}, {11, 10}}, new int[]{0, 0}, new int[]{10, 10})); // false
    }

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked == null || blocked.length == 0) return true;
        boolean result = true;
        boolean isSource = false;
        for (int[] point : new int[][]{source, target}) {
            isSource = !isSource;
            if (!result) break;
            Deque<int[]> q = new ArrayDeque<>();
            q.add(point);

            Map<Integer, Set<Integer>> map = new HashMap<>();
            for (int[] block : blocked) {
                Set<Integer> vals = map.get(block[0]);
                if (vals == null) {
                    vals = new HashSet<>();
                }
                vals.add(block[1]);
                map.put(block[0], vals);
            }

            Map<Integer, Set<Integer>> visited = new HashMap<>();
            Set<Integer> v = new HashSet<>();
            v.add(point[1]);
            visited.put(point[0], v);
            int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            int count = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] node = q.poll();
                    if (node == null) continue;
                    if (isSource && Arrays.equals(node, target)) {
                        return true;
                    }
                    for (int[] dir : dirs) {
                        int x = node[0] + dir[0];
                        int y = node[1] + dir[1];
                        boolean isBlocked = isBlocked(map, x, y);
                        if (x >= 0 && x < 1000000 && y >= 0 && y < 1000000 && !isBlocked) {
                            Set<Integer> vis = visited.get(x);
                            if (vis != null && vis.contains(y)) {
//                                System.out.println("(" + x + ", " + y + ") visited");
                                continue;
                            }
                            q.add(new int[]{x, y});
                            if (vis == null) vis = new HashSet<>();
                            vis.add(y);
                            visited.put(x, vis);
                        }
                    }
                }
                count++;
                if (count > 200) {
                    break;
                }
            }
            result = count > 200;
        }
        return result;
    }

    private boolean isBlocked(Map<Integer, Set<Integer>> blocked, int x, int y) {
        Set<Integer> set = blocked.get(x);
        return set != null && set.contains(y);
    }
}
