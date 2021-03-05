import java.util.PriorityQueue;

/**
 * Facebook Interview Preparation
 * <p>
 * Seating Arrangements
 * <p>
 * There are n guests attending a dinner party, numbered from 1 to n. The ith guest has a height of arr[i-1] inches.
 * <p>
 * The guests will sit down at a circular table which has n seats, numbered from 1 to n in clockwise order around the table. As the host, you will choose how to arrange the guests, one per seat. Note that there are n! possible permutations of seat assignments.
 * <p>
 * Once the guests have sat down, the awkwardness between a pair of guests sitting in adjacent seats is defined as the absolute difference between their two heights. Note that, because the table is circular, seats 1 and n are considered to be adjacent to one another, and that there are therefore n pairs of adjacent guests.
 * <p>
 * The overall awkwardness of the seating arrangement is then defined as the maximum awkwardness of any pair of adjacent guests. Determine the minimum possible overall awkwardness of any seating arrangement.
 * <p>
 * Signature
 * int minOverallAwkwardness(int[] arr)
 * <p>
 * Input
 * n is in the range [3, 1000].
 * Each height arr[i] is in the range [1, 1000].
 * <p>
 * Output
 * Return the minimum achievable overall awkwardness of any seating arrangement.
 * <p>
 * Example
 * n = 4
 * arr = [5, 10, 6, 8]
 * output = 4
 * If the guests sit down in the permutation [3, 1, 4, 2] in clockwise order around the table (having heights [6, 5, 8, 10], in that order), then the four awkwardnesses between pairs of adjacent guests will be |6-5| = 1, |5-8| = 3, |8-10| = 2, and |10-6| = 4, yielding an overall awkwardness of 4. It's impossible to achieve a smaller overall awkwardness.
 */
public class SeatingArrangements {
    public static void main(String[] args) {
        SeatingArrangements sol = new SeatingArrangements();
        System.out.println(sol.minOverallAwkwardness(new int[]{5, 10, 6, 8})); // 4
        System.out.println(sol.minOverallAwkwardness(new int[]{1, 2, 5, 3, 7})); // 4
    }

    public int minOverallAwkwardness(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int a : arr) maxQ.add(a);

        if (maxQ.isEmpty()) return 0;

        int res = 0;
        int prevLeft = maxQ.poll();
        int prevRight = prevLeft;
        while (!maxQ.isEmpty()) {
            int left = maxQ.poll();
            res = Math.max(res, prevLeft - left);
            prevLeft = left;
            if (!maxQ.isEmpty()) {
                int right = maxQ.poll();
                res = Math.max(res, prevRight - right);
                prevRight = right;
            } else {
                res = Math.max(res, prevRight - left);
            }
        }
        return res;
    }
}
