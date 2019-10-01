import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LeetCode. 542. 01 Matrix
 * https://leetcode.com/problems/01-matrix/
 */
public class ZeroOneMatrix {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(updateMatrix(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        })));
        System.out.println(Arrays.deepToString(updateMatrix(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        })));
        System.out.println(Arrays.deepToString(updateMatrix(new int[][]{
                {1, 0, 1, 1, 0, 0, 1, 0, 0, 1},
                {0, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                {0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 1, 1},
                {1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
                {1, 1, 1, 1, 0, 1, 0, 0, 1, 1}
        })));
    }

    private static class Node {
        int i, j, value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return i == node.i &&
                    j == node.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }

        @Override
        public String toString() {
            return "{" +
                    "i=" + i +
                    ", j=" + j +
                    ", v=" + value +
                    '}';
        }
    }

    private static int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return matrix;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
        Set<Node> visited = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    List<Node> neighbors = getOneNeighbors(matrix, i, j, 0, visited);
                    pq.addAll(neighbors);
                    visited.addAll(neighbors);
                }
            }
        }
        if (pq.isEmpty()) return matrix;

        visited.clear();
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited.contains(node)) continue;

            List<Node> neighbors = getOneNeighbors(matrix, node.i, node.j, node.value, visited);
            pq.addAll(neighbors);

            matrix[node.i][node.j] = node.value;

            visited.add(node);
        }
        return matrix;
    }

    private static List<Node> getOneNeighbors(int[][] matrix, int i, int j, int value, Set<Node> visited) {
        List<Node> nodes = new ArrayList<>(4);
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) continue;

            if (matrix[x][y] == 1) {
                Node node = new Node();
                node.i = x;
                node.j = y;
                node.value = 1 + value;
                if (!visited.contains(node))
                    nodes.add(node);
            }
        }
        return nodes;
    }
}
