import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode 119. Pascal's Triangle II
 * https://leetcode.com/problems/pascals-triangle-ii/
 */
public class PascalsTriangleII {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getRow(i));
        }
    }

    private static List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) return Collections.emptyList();

        List<Integer> prev = Collections.emptyList();
        List<Integer> current = prev;
        for (int i = 0; i <= rowIndex; i++) {
            current = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                current.add((j < prev.size() && j > 0) ? (prev.get(j - 1) + prev.get(j)) : 1);
            }
            prev = current;
        }

        return current;
    }
}
