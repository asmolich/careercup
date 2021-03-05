import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode
 * 1761. Minimum Degree of a Connected Trio in a Graph
 * https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/
 * #Hard #Graph
 */
public class MinimumDegreeOfAConnectedTrioInAGraph {
    public static void main(String[] args) {
        MinimumDegreeOfAConnectedTrioInAGraph sol = new MinimumDegreeOfAConnectedTrioInAGraph();
        System.out.println(sol.minTrioDegree(6, new int[][]{{1, 2}, {1, 3}, {3, 2}, {4, 1}, {5, 2}, {3, 6}})); // 3
        System.out.println(sol.minTrioDegree(7, new int[][]{{1, 3}, {4, 1}, {4, 3}, {2, 5}, {5, 6}, {6, 7}, {7, 5}, {2, 6}})); // 0
        System.out.println(sol.minTrioDegree(5, new int[][]{{5, 2}, {4, 1}, {4, 2}, {1, 5}})); // -1
    }

    public int minTrioDegree(int n, int[][] edges) {
        if (n < 1 || edges == null || edges.length == 0) return -1;

        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] degree = new int[n];
        boolean[][] isEdge = new boolean[n][n];
        for (int[] edge : edges) {
            edge[0]--;
            edge[1]--;
            degree[edge[0]]++;
            degree[edge[1]]++;
            isEdge[edge[0]][edge[1]] = true;
            isEdge[edge[1]][edge[0]] = true;
        }
        int min = Integer.MAX_VALUE;
        for (int[] edge : edges) {
            for (int i = 0; i < n; i++) {
                if (isEdge[i][edge[0]] && isEdge[i][edge[1]]) {
                    int d = degree[i] +
                            degree[edge[0]] +
                            degree[edge[1]] - 6;
                    min = Math.min(min, d);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
