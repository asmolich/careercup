import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode
 * 354. Russian Doll Envelopes
 * https://leetcode.com/problems/russian-doll-envelopes/
 * #Hard #DP #BinarySearch
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {
        RussianDollEnvelopes sol = new RussianDollEnvelopes();
        System.out.println(sol.maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}})); // 3
        System.out.println(sol.maxEnvelopes(new int[][]{{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}})); // 4
        System.out.println(sol.maxEnvelopes(new int[][]{{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}})); // 4
        System.out.println(sol.maxEnvelopes(new int[][]{{4, 5}, {4, 3}, {6, 3}, {6, 4}, {6, 5}, {2, 3}, {1, 1}})); // 3
        System.out.println(sol.maxEnvelopes(new int[][]{{1, 2}, {2, 3}, {3, 4}, {3, 5}, {4, 5}, {5, 5}, {5, 6}, {6, 7}, {7, 8}})); // 7
    }

    // sort by x asc and y desc: [[2,3],[5,4],[6,7],[6,4]]
    // find LIS by y: [3,4,7]
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;

        // sort
        Arrays.sort(envelopes, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> -a[1]));
        System.out.println(Arrays.deepToString(envelopes));

        // LIS
        // patience sorting
        List<Integer> piles = new ArrayList<>();
        for (int[] envelope : envelopes) {
            int newPile = envelope[1];

            int i = Collections.binarySearch(piles, newPile);
            if (i < 0) i = ~i;

            if (i < piles.size()) {
                piles.set(i, newPile);
            } else {
                piles.add(newPile);
            }
            System.out.println(piles);
        }
        return piles.size();
    }
}