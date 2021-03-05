import java.util.Arrays;

/**
 * LeetCode
 * 1042. Flower Planting With No Adjacent
 * https://leetcode.com/problems/flower-planting-with-no-adjacent/
 * #Medium #TopologicalSort
 */
public class FlowerPlantingWithNoAdjacent {
    public static void main(String[] args) {
        FlowerPlantingWithNoAdjacent sol = new FlowerPlantingWithNoAdjacent();
        System.out.println(Arrays.toString(sol.gardenNoAdj(3, new int[][]{{1, 2}, {2, 3}, {3, 1}}))); // [1,2,3]
        System.out.println(Arrays.toString(sol.gardenNoAdj(4, new int[][]{{1, 2}, {3, 4}}))); // [1,2,1,2]
        System.out.println(Arrays.toString(sol.gardenNoAdj(4, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 3}, {2, 4}}))); // [1,2,3,4]
        System.out.println(Arrays.toString(sol.gardenNoAdj(5, new int[][]{{4, 1}, {4, 2}, {4, 3}, {2, 5}, {1, 2}, {1, 5}}))); // [1,2,1,3,3]
        System.out.println(Arrays.toString(sol.gardenNoAdj(6, new int[][]{{4, 2}, {6, 2}, {6, 3}, {2, 3}, {5, 3}, {6, 5}, {5, 4}, {4, 1}}))); // [1,1,2,2,1,3]
    }

    public int[] gardenNoAdj(int n, int[][] paths) {
        if (paths == null || paths.length == 0) {
            int[] res = new int[n];
            Arrays.fill(res, 1);
            return res;
        }

        int[] colors = new int[n];
        boolean[][] graph = new boolean[n][n];
        for (int[] path : paths) {
            int from = path[0] - 1;
            int to = path[1] - 1;
            graph[from][to] = true;
            graph[to][from] = true;
        }

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) dfs(graph, i, colors);
        }

        return colors;
    }

    private void dfs(boolean[][] graph, int idx, int[] colors) {
        boolean[] avail = new boolean[5];
        Arrays.fill(avail, true);
        for (int i = 0; i < graph[idx].length; i++) {
            if (graph[idx][i]) avail[colors[i]] = false;
        }
        for (int i = 1; i < avail.length; i++) {
            if (avail[i]) {
                colors[idx] = i;
                break;
            }
        }
    }
}
