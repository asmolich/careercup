import java.util.PriorityQueue;

/**
 * Facebook Interview Preparation
 * <p>
 * Slow Sums
 * <p>
 * Suppose we have a list of N numbers, and repeat the following operation until we're left with only a single number: Choose any two numbers and replace them with their sum. Moreover, we associate a penalty with each operation equal to the value of the new number, and call the penalty for the entire list as the sum of the penalties of each operation.
 * <p>
 * For example, given the list [1, 2, 3, 4, 5], we could choose 2 and 3 for the first operation, which would transform the list into [1, 5, 4, 5] and incur a penalty of 5. The goal in this problem is to find the worst possible penalty for a given input.
 * <p>
 * Signature:
 * int getTotalTime(int[] arr)
 * <p>
 * Input:
 * An array arr containing N integers, denoting the numbers in the list.
 * <p>
 * Output format:
 * An int representing the worst possible total penalty.
 * <p>
 * Constraints:
 * 1 ≤ N ≤ 10^6
 * 1 ≤ Ai ≤ 10^7, where *Ai denotes the ith initial element of an array.
 * The sum of values of N over all test cases will not exceed 5 * 10^6.
 * <p>
 * Example
 * arr = [4, 2, 1, 3]
 * output = 26
 * <p>
 * First, add 4 + 3 for a penalty of 7. Now the array is [7, 2, 1]
 * Add 7 + 2 for a penalty of 9. Now the array is [9, 1]
 * Add 9 + 1 for a penalty of 10. The penalties sum to 26.
 */
public class SlowSums {
    public static void main(String[] args) {
        SlowSums sol = new SlowSums();
        System.out.println(sol.getTotalTime(new int[]{4, 2, 1, 3})); // 26
        System.out.println(sol.getTotalTime(new int[]{2, 3, 9, 8, 4})); // 88
    }

    public int getTotalTime(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int a : arr) {
            maxQ.add(a);
        }
        int res = 0;
        while (maxQ.size() > 1) {
            Integer v1 = maxQ.poll();
            Integer v2 = maxQ.poll();
            int sum = v1 + (v2 == null ? 0 : v2);
            res += sum;
            maxQ.add(sum);
        }
        return res;
    }
}
