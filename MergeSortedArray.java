import java.util.Arrays;

/**
 * LeetCode
 * https://leetcode.com/problems/merge-sorted-array/
 * 88. Merge Sorted Array
 * #Easy #TwoPointer
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        MergeSortedArray sol = new MergeSortedArray();
        int[] a = {4, 0, 0, 0, 0, 0};
        sol.merge(a, 1, new int[]{1, 2, 3, 5, 6}, 5);
        System.out.println(Arrays.toString(a)); // [1,2,3,4,5,6]
        a = new int[]{1, 2, 3, 0, 0, 0};
        sol.merge(a, 3, new int[]{2, 5, 6}, 3);
        System.out.println(Arrays.toString(a)); // [1,2,2,3,5,6]
        a = new int[]{1};
        sol.merge(a, 1, new int[0], 0);
        System.out.println(Arrays.toString(a)); // [1]
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0 || nums2 == null || nums2.length == 0) return;
        int p = nums1.length - 1, p1 = m - 1, p2 = n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2])
                nums1[p--] = nums1[p1--];
            else
                nums1[p--] = nums2[p2--];
        }
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }
}
