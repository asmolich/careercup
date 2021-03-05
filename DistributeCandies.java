import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode
 * 575. Distribute Candies
 * https://leetcode.com/problems/distribute-candies/
 * #Easy #HashTable
 */
public class DistributeCandies {
    public static void main(String[] args) {
        DistributeCandies sol = new DistributeCandies();
        System.out.println(sol.distributeCandies(new int[]{1, 1, 2, 2, 3, 3})); // 3
        System.out.println(sol.distributeCandies(new int[]{1, 1, 2, 3})); // 2
        System.out.println(sol.distributeCandies(new int[]{6, 6, 6, 6})); // 1
    }

    public int distributeCandies(int[] candyType) {
        if (candyType == null || candyType.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int candy : candyType) {
            map.put(candy, map.getOrDefault(candy, 0) + 1);
        }
        return Math.min(map.size(), candyType.length / 2);
    }
}
