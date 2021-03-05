import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LeetCode
 * 407. Trapping Rain Water II
 * https://leetcode.com/problems/trapping-rain-water-ii/
 */
public class TrappingRainWaterII {
    public static void main(String[] args) {
        int[][] input = {
                {12, 13, 1, 12},
                {13, 4, 13, 12},
                {13, 8, 10, 12},
                {12, 13, 12, 12},
                {13, 13, 13, 13}
        };

        int[][] input2 = {
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        };

        System.out.println(trapRainWater(input));
        System.out.println(trapRainWater(input2));
    }

    private static class Node implements Comparable<Node> {
        int x, y;
        int value;

        static Node of(int x, int y, int value) {
            Node node = new Node();
            node.x = x;
            node.y = y;
            node.value = value;
            return node;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(value, o.value);
        }

        List<Node> getNeighbours(int[][] heightMap, Set<Node> visited) {
            List<Node> nodes = new ArrayList<>(4);
            if (x > 1) {
                Node node = Node.of(x - 1, y, heightMap[x - 1][y]);
                if (!visited.contains(node)) nodes.add(node);
            }
            if (y > 1) {
                Node node = Node.of(x, y - 1, heightMap[x][y - 1]);
                if (!visited.contains(node)) nodes.add(node);
            }
            if (x < heightMap.length - 1) {
                Node node = Node.of(x + 1, y, heightMap[x + 1][y]);
                if (!visited.contains(node)) nodes.add(node);
            }
            if (x < heightMap[x].length - 1) {
                Node node = Node.of(x, y + 1, heightMap[x][y + 1]);
                if (!visited.contains(node)) nodes.add(node);
            }
            return nodes;
        }
    }

    private static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) return 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Node> visited = new HashSet<>();
        for (int i = 0; i < heightMap.length; i++) {
            Node node = Node.of(i, 0, heightMap[i][0]);
            pq.add(node);
            visited.add(node);
            node = Node.of(i, heightMap.length - 1, heightMap[i][heightMap.length - 1]);
            pq.add(node);
            visited.add(node);
        }
        for (int i = 1; i < heightMap[0].length - 1; i++) {
            Node node = Node.of(0, i, heightMap[0][i]);
            pq.add(node);
            visited.add(node);
            node = Node.of(heightMap.length - 1, i, heightMap[heightMap.length - 1][i]);
            pq.add(node);
            visited.add(node);
        }

        // 3  3  3
        // 3  1 [2]
        // 3  3  3
        int water = 0;
        int lower = Integer.MIN_VALUE;
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            lower = Math.max(lower, current.value);

            current.getNeighbours(heightMap, visited);

        }

        return water;
    }
}
