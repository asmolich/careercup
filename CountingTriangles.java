import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Facebook Interview Preparation
 * <p>
 * Counting Triangles
 * <p>
 * Given a list of N triangles with integer side lengths, determine how many different triangles there are. Two triangles are considered to be the same if they can both be placed on the plane such that their vertices occupy exactly the same three points.
 * <p>
 * Signature
 * int countDistinctTriangles(ArrayList<Sides> arr)
 * or
 * int countDistinctTrianges(int[][] arr)
 * <p>
 * Input
 * In some languages, arr is an Nx3 array where arr[i] is a length-3 array that contains the side lengths of the ith triangle. In other languages, arr is a list of structs/objects that each represent a single triangle with side lengths a, b, and c.
 * It's guaranteed that all triplets of side lengths represent real triangles.
 * All side lengths are in the range [1, 1,000,000,000]
 * 1 <= N <= 1,000,000
 * <p>
 * Output
 * Return the number of distinct triangles in the list.
 * Example 1
 * arr = [[2, 2, 3], [3, 2, 2], [2, 5, 6]]
 * output = 2
 * The first two triangles are the same, so there are only 2 distinct triangles.
 * Example 2
 * arr = [[8, 4, 6], [100, 101, 102], [84, 93, 173]]
 * output = 3
 * All of these triangles are distinct.
 * Example 3
 * arr = [[5, 8, 9], [5, 9, 8], [9, 5, 8], [9, 8, 5], [8, 9, 5], [8, 5, 9]]
 * output = 1
 * All of these triangles are the same.
 */
public class CountingTriangles {
    public static void main(String[] args) {
        CountingTriangles sol = new CountingTriangles();
        System.out.println(sol.countDistinctTriangles(Arrays.asList(
                new Sides(2, 2, 3), new Sides(3, 2, 2), new Sides(2, 5, 6)
        ))); // 2
        System.out.println(sol.countDistinctTriangles(Arrays.asList(
                new Sides(8, 4, 6), new Sides(100, 101, 102), new Sides(84, 93, 173)
        ))); // 3
    }

    private static class Sides {
        int a;
        int b;
        int c;

        Sides(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    private static class SortedSides {
        private final int[] sides;

        public SortedSides(Sides s) {
            this.sides = new int[]{s.a, s.b, s.c};
            Arrays.sort(this.sides);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SortedSides sidesHash = (SortedSides) o;
            return Arrays.equals(sides, sidesHash.sides);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(sides);
        }
    }

    public int countDistinctTriangles(List<Sides> arr) {
        Set<SortedSides> set = new HashSet<>(arr.size());
        for (Sides sides : arr) {
            set.add(new SortedSides(sides));
        }
        return set.size();
    }
}
