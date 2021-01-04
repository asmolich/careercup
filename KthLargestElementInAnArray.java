/**
 * LeetCode 215. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * #Medium #QuickSelect #Partitioning
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        KthLargestElementInAnArray sol = new KthLargestElementInAnArray();
        System.out.println(sol.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2)); // 5
        System.out.println(sol.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4)); // 4
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) return -1;

        int n = nums.length;
        int lo = 1;
        int hi = n -1;
        int pivot = nums[0];
        for (int i = lo; i < hi; i++) {

        }
        return 0;
    }
}
