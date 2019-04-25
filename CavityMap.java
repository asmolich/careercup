import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Hacker Rank. Cavity Map. https://www.hackerrank.com/challenges/cavity-map/problem
 */
public class CavityMap {
    public static void main(String[] args) {
        System.out.println(
            Arrays.toString(
                cavityMap(new String[]{
                    "989",
                    "191",
                    "111"
                })));
    }

    static class GridElem {
        int x, y;
        int val;

        @Override
        public String toString() {
            return "GridElem{" +
                "x=" + x +
                ", y=" + y +
                ", val=" + val +
                "}\n";
        }
    }

    private static String[] cavityMap(String[] grid) {
        if (grid == null || grid.length < 3) return grid;

        final int[][] visited = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            visited[i] = new int[grid[i].length()];
        }

        PriorityQueue<GridElem> q = new PriorityQueue<>(
            Comparator.<GridElem>comparingInt(a -> a.val)
                .thenComparingInt(a -> visited[a.x][a.y]));
        GridElem elem;
        for (int i = 0; i < grid[0].length(); i++) {
            elem = new GridElem();
            elem.x = 0;
            elem.y = i;
            elem.val = grid[0].charAt(i) - '0';
            q.add(elem);
            visited[0][i] = 1;

            elem = new GridElem();
            elem.x = grid.length - 1;
            elem.y = i;
            elem.val = grid[grid.length - 1].charAt(i) - '0';
            q.add(elem);
            visited[grid.length - 1][i] = 1;
        }
        for (int i = 1; i < grid.length - 1; i++) {
            elem = new GridElem();
            elem.x = i;
            elem.y = 0;
            elem.val = grid[i].charAt(0) - '0';
            q.add(elem);
            visited[i][0] = 1;

            elem = new GridElem();
            elem.x = i;
            elem.y = grid.length - 1;
            elem.val = grid[i].charAt(grid.length - 1) - '0';
            q.add(elem);
            visited[i][grid.length - 1] = 1;
        }

        System.out.println(q);

        while (!q.isEmpty()) {
            System.out.println(q);
            elem = q.poll();

            if (isValidProcessedNode(elem, visited)) {
                char[] chars = grid[elem.x].toCharArray();
                chars[elem.y] = 'X';
                grid[elem.x] = String.valueOf(chars);
            }
            addNeighbors(elem, q, visited, grid);
        }

        return grid;
    }

    private static boolean isValidProcessedNode(GridElem elem, int[][] visited) {
        int x = elem.x;
        int y = elem.y;

        return x > 0 && x < visited.length - 1 &&
            y > 0 && y < visited[x].length - 1 &&
            visited[x - 1][y] == 2 &&
            visited[x + 1][y] == 2 &&
            visited[x][y - 1] == 2 &&
            visited[x][y + 1] == 2;
    }

    private static void addNeighbors(GridElem elem, PriorityQueue<GridElem> q, int[][] visited, String[] grid) {
        int x = elem.x;
        int y = elem.y;

        GridElem newElem;
        if (x > 0 && visited[x - 1][y] == 0) {
            newElem = new GridElem();
            newElem.x = x - 1;
            newElem.y = y;
            newElem.val = grid[x - 1].charAt(y) - '0';
            q.add(newElem);
            visited[x - 1][y] = 1;
        }

        if (x + 1 < visited.length && visited[x + 1][y] == 0) {
            newElem = new GridElem();
            newElem.x = x + 1;
            newElem.y = y;
            newElem.val = grid[x + 1].charAt(y) - '0';
            q.add(newElem);
            visited[x + 1][y] = 1;
        }

        if (y > 0 && visited[x][y - 1] == 0) {
            newElem = new GridElem();
            newElem.x = x;
            newElem.y = y - 1;
            newElem.val = grid[x].charAt(y - 1) - '0';
            q.add(newElem);
            visited[x][y - 1] = 1;
        }

        if (y + 1 < visited[x].length && visited[x][y + 1] == 0) {
            newElem = new GridElem();
            newElem.x = x;
            newElem.y = y + 1;
            newElem.val = grid[x].charAt(y + 1) - '0';
            q.add(newElem);
            visited[x][y + 1] = 1;
        }
        visited[x][y] = 2;
    }
}
