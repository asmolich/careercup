import java.util.PriorityQueue;

/**
 * Facebook Interview Preparation
 * <p>
 * Magical Candy Bags
 * <p>
 * You have N bags of candy. The ith bag contains arr[i] pieces of candy, and each of the bags is magical!
 * <p>
 * It takes you 1 minute to eat all of the pieces of candy in a bag (irrespective of how many pieces of candy are inside), and as soon as you finish, the bag mysteriously refills. If there were x pieces of candy in the bag at the beginning of the minute, then after you've finished you'll find that floor(x/2) pieces are now inside.
 * <p>
 * You have k minutes to eat as much candy as possible. How many pieces of candy can you eat?
 * <p>
 * Signature
 * int maxCandies(int[] arr, int K)
 * <p>
 * Input
 * 1 ≤ N ≤ 10,000
 * 1 ≤ k ≤ 10,000
 * 1 ≤ arr[i] ≤ 1,000,000,000
 * <p>
 * Output
 * A single integer, the maximum number of candies you can eat in k minutes.
 * <p>
 * Example 1
 * N = 5
 * k = 3
 * arr = [2, 1, 7, 4, 2]
 * output = 14
 * <p>
 * In the first minute you can eat 7 pieces of candy. That bag will refill with floor(7/2) = 3 pieces.
 * In the second minute you can eat 4 pieces of candy from another bag. That bag will refill with floor(4/2) = 2 pieces.
 * In the third minute you can eat the 3 pieces of candy that have appeared in the first bag that you ate.
 * In total you can eat 7 + 4 + 3 = 14 pieces of candy.
 */
public class MagicalCandyBags {
    public static void main(String[] args) {
        MagicalCandyBags sol = new MagicalCandyBags();
        System.out.println(sol.maxCandies(new int[]{2, 1, 7, 4, 2}, 3)); // 14
        System.out.println(sol.maxCandies(new int[]{9, 78, 76, 72, 48, 8, 24, 74, 29}, 3)); // 228
    }

    public int maxCandies(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 1) return 0;

        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int a : arr) {
            maxQ.add(a);
        }
        int res = 0;
        while (k > 0) {
            Integer bag = maxQ.poll();
            int bagValue = bag == null ? 0 : bag;
            res += bagValue;
            maxQ.add(bagValue / 2);
            k--;
        }
        return res;
    }
}
