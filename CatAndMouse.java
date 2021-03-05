/**
 * LeetCode
 * 913. Cat and Mouse
 * https://leetcode.com/problems/cat-and-mouse/
 * #Hard #Minimax
 * #incomplete
 */
public class CatAndMouse {
    public static void main(String[] args) {

    }

    public int catMouseGame(int[][] graph) {
        if (graph == null || graph.length == 0) return 0;

        int m = graph.length;
        int n = graph[0].length;
        for (int i = 0; i < n; i++) {
            if (graph[1][i] == 0) return 1;
        }

        return 0;
    }
}
