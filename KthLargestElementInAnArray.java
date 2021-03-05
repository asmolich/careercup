/**
 * LeetCode
 * 215. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * #Medium #QuickSelect #Partitioning #Heap #PriorityQueue
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
        int lo = 0;
        int hi = n - 1;
        int index = n - k;
        while (lo < hi) {
            int pivot = partition(nums, lo, hi);
            if (pivot < index) lo = pivot + 1;
            else if (pivot > index) hi = pivot - 1;
            else return nums[pivot];
        }
        return nums[lo];
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = lo;
        while (lo <= hi) {
            while (lo <= hi && nums[lo] <= nums[pivot]) lo++;
            while (lo <= hi && nums[hi] > nums[pivot]) hi--;
            if (lo > hi) break;
            swap(nums, lo, hi);
        }
        swap(nums, hi, pivot);
        return hi;
    }

    private void swap(int[] nums, int lo, int hi) {
        int temp = nums[lo];
        nums[lo] = nums[hi];
        nums[hi] = temp;
    }
}
