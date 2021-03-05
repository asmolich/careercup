/**
 * Odd-Even Subarrays.
 *
 * You are given an array A of N positive integer values. A sub-array of this array is called Odd-Even sub-array
 * if the number of odd integers in this sub-array is equal to the number of even integers in this sub-array.
 *
 * Find the number of Odd-Even sub-arrays for the given array.
 *
 * https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/odd-even-subarrays-72ad69db/
 * https://www.youtube.com/watch?v=Fgp6CKSH_b4
 *
 * #Incomplete
 */
public class OddEvenSubarrays {

    public static void main(String[] args) {
        System.out.println(getNumberOfOddEvenSubarraysDivideConquer(new int[]{1, 2, 1, 2}));
        System.out.println(getNumberOfOddEvenSubarraysDivideConquer(new int[]{1, 2, 3, 4}));
        System.out.println(getNumberOfOddEvenSubarraysDivideConquer(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    private static int getNumberOfOddEvenSubarraysDivideConquer(int[] array) {
        if (array == null || array.length == 0) return 0;

        return getNumberOfOddEvenSubarraysDivideConquer0(array, new int[array.length], 0, array.length - 1);
    }

    private static int getNumberOfOddEvenSubarraysDivideConquer0(int[] array, int[] aux, int start, int end) {
        return 0;
    }
}
