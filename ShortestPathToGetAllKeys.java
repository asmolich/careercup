import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode
 * 864. Shortest Path to Get All Keys
 * https://leetcode.com/problems/shortest-path-to-get-all-keys/
 * #Hard #BFS
 */
public class ShortestPathToGetAllKeys {
    public static void main(String[] args) {
        System.out.println(shortestPathAllKeys(new String[]{
                "@.a.#",
                "###.#",
                "b.A.B"
        }));
        System.out.println(shortestPathAllKeys(new String[]{
                "@..aA",
                "..B#.",
                "....b"
        }));
        System.out.println(shortestPathAllKeys(new String[]{
                "@...a",
                ".###A",
                "b.BCc"
        }));
        System.out.println(shortestPathAllKeys(new String[]{
                ".#.b.",
                "A.#aB",
                "#d...",
                "@.cC.",
                "D...#"
        }));
    }

    @SuppressWarnings("unused")
    private enum Key {
        a, b, c, d, e, f
    }

    private static class Node {
        int x, y, steps;
        int keys;

        static Node of(int x, int y, int steps, int keys) {
            Node node = new Node();
            node.x = x;
            node.y = y;
            node.steps = steps;
            node.keys = keys;
            return node;
        }
    }

    private static int shortestPathAllKeys(String[] grid) {
        if (grid == null) return -1;

        int n = grid.length;
        int m = grid[0].length();

        int allKeys = 0;
        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = grid[i].charAt(j);
                if (ch == '@') {
                    queue.add(Node.of(i, j, 0, 0));
                } else if (ch >= 'a' && ch <= 'f') {
                    allKeys |= (1 << Key.valueOf(Character.toString(ch)).ordinal());
                }
            }
        }

        boolean[][][] visited = new boolean[n][m][allKeys];
        int[][] dims = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.keys == allKeys) return node.steps;

            if (visited[node.x][node.y][node.keys]) continue;
            visited[node.x][node.y][node.keys] = true;

            for (int[] dim : dims) {
                int x = node.x + dim[0];
                int y = node.y + dim[1];
                int keys = node.keys;
                if (x < 0 || x >= n || y < 0 || y >= m) continue;

                char c = grid[x].charAt(y);
                if (c == '#') continue;
                if (c >= 'A' && c <= 'F' && hasKey(keys, c)) continue;
                if (c >= 'a' && c <= 'f') {
                    keys |= (1 << Key.valueOf(Character.toString(c)).ordinal());
                }
                queue.add(Node.of(x, y, node.steps + 1, keys));
            }
        }
        return -1;
    }

    private static boolean hasKey(int keys, char c) {
        return (keys & (1 << Key.valueOf(Character.toString(Character.toLowerCase(c))).ordinal())) == 0;
    }
}
