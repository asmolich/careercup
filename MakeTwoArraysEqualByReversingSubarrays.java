import java.util.Arrays;

/**
 * LeetCode
 * 1460. Make Two Arrays Equal by Reversing Sub-arrays
 * https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/
 * #Easy
 */
public class MakeTwoArraysEqualByReversingSubarrays {
    public static void main(String[] args) {
        MakeTwoArraysEqualByReversingSubarrays sol = new MakeTwoArraysEqualByReversingSubarrays();
        System.out.println(sol.canBeEqual(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})); // true
        System.out.println(sol.canBeEqual(new int[]{7}, new int[]{7})); // true
        System.out.println(sol.canBeEqual(new int[]{1,12}, new int[]{12, 1})); // true
        System.out.println(sol.canBeEqual(new int[]{3,7,9}, new int[]{3,7,11})); // false
        System.out.println(sol.canBeEqual(new int[]{1,1,1,1,1}, new int[]{1,1,1,1,1})); // true
    }

    public boolean canBeEqual(int[] a, int[] b) {
        if (a == null) return b == null;
        if (b == null) return false;
        if (a.length != b.length) return false;

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }
}
