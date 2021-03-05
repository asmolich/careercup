/**
 * LeetCode
 * https://leetcode.com/discuss/interview-question/341247/facebook-leftmost-column-index-of-1
 * <p>
 * In a binary matrix (all elements are 0 and 1), every row is sorted in ascending order (0 to the left of 1). Find the leftmost column index with a 1 in it.
 * <p>
 * Example 1:
 * Input:
 * <pre>
 * [[0, 0, 0, 1],
 *  [0, 0, 1, 1],
 *  [0, 1, 1, 1],
 *  [0, 0, 0, 0]]
 * </pre>
 * Output: 1
 * <p>
 * Example 2:
 * Input:
 * <pre>
 * [[0, 0, 0, 0],
 *  [0, 0, 0, 0],
 *  [0, 0, 0, 0],
 *  [0, 0, 0, 0]]
 * </pre>
 * Output: -1
 * <p>
 * Expected solution better than O(r * c).
 */
public class LeftmostColumnIndexOfOne {
    public static void main(String[] args) {
        LeftmostColumnIndexOfOne sol = new LeftmostColumnIndexOfOne();
        System.out.println(sol.findLeftmostIndexOfOne(new int[][]{
                {0, 0, 0, 1},
                {0, 0, 1, 1},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        })); // 1
        System.out.println(sol.findLeftmostIndexOfOne(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        })); // -1
    }

    public int findLeftmostIndexOfOne(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return -1;
        int m = matrix.length;
        int n = matrix[0].length;
        int res = -1;
        for (int i = 0, j = n - 1; i < m && j < n; ) {
            if (matrix[i][j] == 1) {
                j--;
                res = j;
            }
            else i++;
        }
        return res == -1 ? -1 : res + 1;
    }
}
