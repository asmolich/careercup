/**
 * LeetCode
 * Squirrel Simulation
 * https://code.dennyzhang.com/squirrel-simulation
 */
public class SquirrelSimulation {
    public static void main(String[] args) {
        SquirrelSimulation sol = new SquirrelSimulation();
        System.out.println(sol.minDistance(5, 7, new int[]{2, 2}, new int[]{4, 4}, new int[][]{{3, 0}, {2, 5}})); // 12
    }

    @SuppressWarnings("unused")
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int min = 0;
        for (int[] nut : nuts) {
            min += 2 * dist(nut, tree);
        }
        for (int[] nut : nuts) {
            min = Math.min(min, min + dist(nut, squirrel) - dist(nut, tree));
        }
        return min;
    }

    private int dist(int[] nut, int[] tree) {
        return Math.abs(nut[0] - tree[0]) + Math.abs(nut[1] - tree[1]);
    }
}