import java.util.Arrays;

public class IslandMap {

    private final int[][] map;

    public IslandMap(int[][] map) {
        if (map == null) throw new IllegalArgumentException("map cannot be null");
        this.map = map;
    }

    public void print() {
        System.out.println("[");
        for (int[] isle : map) {
            for (int elem : isle) {
                System.out.print(elem + ", ");
            }
            System.out.println();
        }
        System.out.println("]");
    }

    private int[][] copy() {
        int[][] copy = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            copy[i] = Arrays.copyOf(map[i], map[i].length);
        }
        return copy;
    }

    /**
     * leetcode 694. Number of Distinct Islands
     */
    public int findNumberOfIslands() {
        int count = 0;
        int[][] copy = copy();
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                if (copy[i][j] == 1) {
                    count++;
                    sinkLand(copy, i, j);
                }
            }
        }
        return count;
    }

    /**
     * leetcode 463. Island Perimeter
     */
    public int perimeterOfIsland(int n) {
        int count = 0;
        int perimeter = 0;
        int[][] copy = copy();
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                if (copy[i][j] == 1) {
                    if (count == n) {
                        perimeter += perimeter(copy, i, j);
                    } else {
                        sinkLand(copy, i, j);
                    }
                    count++;
                }
            }
        }
        return perimeter;
    }

    private void sinkLand(int[][] island, int i, int j) {
        if (island[i][j] == 0) return;
        island[i][j] = 0;
        if (inBounds(island, i - 1, j)) sinkLand(island, i - 1, j);
        if (inBounds(island, i + 1, j)) sinkLand(island, i + 1, j);
        if (inBounds(island, i, j - 1)) sinkLand(island, i, j - 1);
        if (inBounds(island, i, j + 1)) sinkLand(island, i, j + 1);
    }

    private int perimeter(int[][] island, int i, int j) {
        if (island[i][j] == -1) return 0;
        island[i][j] = -1;
        System.out.println("in (" + i + ", " + j + ")");
        int perimeter = 0;
        if (inBounds(island, i - 1, j)) perimeter += island[i - 1][j] == 0 ? 1 : perimeter(island, i - 1, j);
        else perimeter += 1;
        if (inBounds(island, i + 1, j)) perimeter += island[i + 1][j] == 0 ? 1 : perimeter(island, i + 1, j);
        else perimeter += 1;
        if (inBounds(island, i, j - 1)) perimeter += island[i][j - 1] == 0 ? 1 : perimeter(island, i, j - 1);
        else perimeter += 1;
        if (inBounds(island, i, j + 1)) perimeter += island[i][j + 1] == 0 ? 1 : perimeter(island, i, j + 1);
        else perimeter += 1;
        return perimeter;
    }

    private boolean inBounds(int[][] island, int i, int j) {
        return i >= 0 && i < island.length && j >= 0 && j < island[i].length;
    }

    public static void main(String[] args) {
        int[][][] input = {
            {},
            {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
            },
            {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
            },
            {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 0, 1, 0}
            },
            {
                {0, 1, 0, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 0, 1, 0}
            }
        };
        for (int[][] ints : input) {
            IslandMap im = new IslandMap(ints);
            im.print();
            System.out.println("Number of islands = " + im.findNumberOfIslands());
            System.out.println("Perimeter of island 1 = " + im.perimeterOfIsland(0));
            System.out.println("Area of island 1 = " + im.perimeterOfIsland(0));
            System.out.println("=====================");
        }
    }
}
