import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode
 * 750. Number Of Corner Rectangles
 * https://github.com/grandyang/leetcode/issues/750
 */
public class NumberOfCornerRectangles {
    public static void main(String[] args) {
        NumberOfCornerRectangles sol = new NumberOfCornerRectangles();
        System.out.println(sol.countCornerRectangles(new int[][]{

        }));
    }

    public int countCornerRectangles(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m - 1; i++) {
            List<Integer> ones = new ArrayList<>();
            for (int k = 0; k < n; k++) if (grid[i][k] == 1) ones.add(k);
            for (int j = i + 1; j < m; j++) {
                int cnt = 0;
                for (Integer idx : ones) {
                    if (grid[j][idx] == 1) cnt++;
                }
                count += cnt * (cnt - 1) / 2;
            }
        }
        return count;
    }
}
