import java.util.List;

/**
 * LeetCode
 * Flatten 2D Vector
 * https://leetcode.com/problems/flatten-2d-vector/
 * http://buttercola.blogspot.com/2015/08/leetcode-flatten-2d-vector.html
 * https://aaronice.gitbook.io/lintcode/data_structure/flatten-2d-vector
 * #Medium
 */
@SuppressWarnings("unused")
public class Flatten2DVector {
    static class Vector2D {

        private final List<List<Integer>> vec2d;
        private int rowId;
        private int colId;
        private final int numRows;

        public Vector2D(List<List<Integer>> vec2d) {
            this.vec2d = vec2d;
            rowId = 0;
            colId = 0;
            numRows = vec2d.size();
        }

        public int next() {
            int ans = 0;

            if (colId < vec2d.get(rowId).size()) {
                ans = vec2d.get(rowId).get(colId);
            }

            colId++;

            if (colId == vec2d.get(rowId).size()) {
                colId = 0;
                rowId++;
            }

            return ans;
        }

        public boolean hasNext() {
            while (rowId < numRows && (vec2d.get(rowId) == null || vec2d.get(rowId).isEmpty())) {
                rowId++;
            }

            return vec2d != null &&
                    !vec2d.isEmpty() &&
                    rowId < numRows;
        }
    }
}
