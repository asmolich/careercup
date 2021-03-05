/**
 * LeetCode
 * 785. Is Graph Bipartite?
 * https://leetcode.com/problems/is-graph-bipartite/
 * #Medium #DFS
 */
public class IsGraphBipartite {
    public static void main(String[] args) {
        IsGraphBipartite sol = new IsGraphBipartite();
        System.out.println(sol.isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}})); // true
        System.out.println(sol.isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}})); // false
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] groups = new int[n];
        for (int i = 0; i < n; i++) {
            if (groups[i] == 0 && dfs(graph, groups, i, 1)) {
                return false;
            }
        }
        return true;
    }

    // check all neighbors are in opposite group of me
    private boolean dfs(int[][] graph, int[] group, int index, int expectedGroup) {
        if (group[index] != 0) {
            return group[index] != expectedGroup;
        }
        group[index] = expectedGroup;
        for (int neighbor : graph[index]) {
            if (dfs(graph, group, neighbor, -expectedGroup)) {
                return true;
            }
        }
        return false;
    }
}
