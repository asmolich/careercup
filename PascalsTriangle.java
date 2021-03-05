import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode
 * 118. Pascal's Triangle
 * https://leetcode.com/problems/pascals-triangle/
 * #Easy
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generate(i));
        }
    }

    private static List<List<Integer>> generate(int numRows) {
        if (numRows < 1) return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>(numRows);
        List<Integer> prev = Collections.emptyList();
        for (int i = 0; i < numRows; i++) {
            List<Integer> current = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                current.add((j < prev.size() && j > 0) ? (prev.get(j - 1) + prev.get(j)) : 1);
            }
            result.add(current);
            prev = current;
        }

        return result;
    }
}
