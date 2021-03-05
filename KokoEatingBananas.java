/**
 * LeetCode
 * 875. Koko Eating Bananas
 * https://leetcode.com/problems/koko-eating-bananas/
 * #Medium #BinarySearch
 * https://leetcode.com/problems/koko-eating-bananas/discuss/152324/C++JavaPython-Binary-Search
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        KokoEatingBananas sol = new KokoEatingBananas();
        System.out.println(sol.minEatingSpeed(new int[]{3, 6, 7, 11}, 8)); // 4
        System.out.println(sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5)); // 30
        System.out.println(sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6)); // 23
    }

    public int minEatingSpeed(int[] piles, int h) {
        if (piles == null || piles.length == 0 || h < 1) return 0;

        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }
        int lo = 1, hi = maxPile;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            int hours = 0;
            for (int pile : piles) {
                hours += ((pile % mid == 0) ? pile / mid : pile / mid + 1);
            }
            if (hours > h) {
                lo = mid+1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
