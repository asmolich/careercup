import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * LeetCode
 * 1631. Path With Minimum Effort
 * https://leetcode.com/problems/path-with-minimum-effort/
 * #Medium
 */
public class PathWithMinimumEffort {
    public static void main(String[] args) {
        PathWithMinimumEffort sol = new PathWithMinimumEffort();
        System.out.println(sol.minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}})); // 2
        System.out.println(sol.minimumEffortPath(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}})); // 1
        System.out.println(sol.minimumEffortPath(new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}})); // 0
    }

    static class Node {
        int x, y;
        int effort;
        int minEffort;

        public Node(int x, int y, int effort, int min) {
            this.x = x;
            this.y = y;
            this.effort = effort;
            minEffort = min;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ", eff=" + effort + ", min=" + minEffort + ')';
        }
    }

    public int minimumEffortPath(int[][] heights) {
        if (heights == null || heights.length == 0) return -1;

        int m = heights.length;
        int n = heights[0].length;

        Comparator<Node> c = Comparator
                .comparingInt((Node a) -> a.effort)
                .thenComparingInt(a -> -a.minEffort);
        PriorityQueue<Node> minQ = new PriorityQueue<>(c);
        Node node = new Node(0, 0, 0, 0);
        minQ.add(node);
        Map<Node, Node> visited = new HashMap<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int min = Integer.MAX_VALUE;
        while (!minQ.isEmpty()) {
            System.out.println(node);
            node = minQ.poll();
            if (node == null) continue;
            if (node.x == m - 1 && node.y == n - 1) return node.minEffort;
            for (int[] dir : dirs) {
                int x = node.x + dir[0];
                int y = node.y + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                int effort = Math.abs(heights[x][y] - heights[node.x][node.y]);
                int minEffort = Math.max(effort, node.minEffort);
                Node newNode = new Node(x, y, effort, minEffort);
                if (visited.containsKey(newNode)) {
                    Node value = visited.get(newNode);
                    if (minEffort < value.minEffort) minQ.add(newNode);
                } else minQ.add(newNode);
                visited.put(newNode, newNode);
            }
        }
        return min;
    }
}
