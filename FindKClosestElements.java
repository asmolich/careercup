import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode
 * 658. Find K Closest Elements
 * https://leetcode.com/problems/find-k-closest-elements/
 * #Medium
 */
public class FindKClosestElements {
    public static void main(String[] args) {
        FindKClosestElements sol = new FindKClosestElements();
        System.out.println(sol.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3)); // [1,2,3,4]
        System.out.println(sol.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1)); // [1,2,3,4]
        System.out.println(sol.findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 1, 9)); // [10]
        System.out.println(sol.findClosestElements(new int[]{-2, -1, 1, 2, 3, 4, 5}, 7, 3)); // [-2,-1,1,2,3,4,5]
        System.out.println(sol.findClosestElements(new int[]{0, 1, 1, 1, 2, 3, 6, 7, 8, 9}, 9, 4)); // [0,1,1,1,2,3,6,7,8]
        System.out.println(sol.findClosestElements(new int[]{0, 0, 1, 2, 3, 3, 4, 7, 7, 8}, 3, 5)); // [3,3,4]
        System.out.println(sol.findClosestElements(new int[]{0, 0, 0, 1, 3, 5, 6, 7, 8, 8}, 2, 2)); // [1,3]
        System.out.println(sol.findClosestElements(new int[]{0, 2, 2, 3, 4, 6, 7, 8, 9, 9}, 4, 5)); // [3,4,6,7]
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0 || k < 1) return Collections.emptyList();
        // sorted array

        int n = arr.length;
        if (k >= n) return Arrays.stream(arr).boxed().collect(Collectors.toList());
        if (x <= arr[0]) return Arrays.stream(arr).limit(k).boxed().collect(Collectors.toList());
        if (x >= arr[n - 1]) return Arrays.stream(arr).skip(n - k).boxed().collect(Collectors.toList());
        int i = Arrays.binarySearch(arr, x);
        if (i < 0) i = ~i;

        int lo = Math.max(0, i - k - 1);
        int hi = Math.min(n - 1, i + k - 1);

        while (hi - lo > k - 1) {
            if (x - arr[lo] <= arr[hi] - x) hi--;
            else lo++;
        }
        List<Integer> res = new ArrayList<>();
        for (int j = lo; j <= hi; j++) {
            res.add(arr[j]);
        }
        return res;
    }
}
