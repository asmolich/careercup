import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode
 * 421. Maximum XOR of Two Numbers in an Array
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * #Medium #Trie
 */
public class MaximumXOROfTwoNumbersInArray {
    public static void main(String[] args) {
        MaximumXOROfTwoNumbersInArray sol = new MaximumXOROfTwoNumbersInArray();
        System.out.println(sol.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8})); // 28
        System.out.println(sol.findMaximumXOR(new int[]{0})); // 0
        System.out.println(sol.findMaximumXOR(new int[]{2, 4})); // 6
        System.out.println(sol.findMaximumXOR(new int[]{8, 10, 2})); // 10
        System.out.println(sol.findMaximumXOR(new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70})); // 127
    }

    public int findMaximumXOR(int[] nums) {
        int maxValue = 0;
        for (int num : nums) maxValue = Math.max(maxValue, num);
        int length = 32 - Integer.numberOfLeadingZeros(maxValue);

        int res = 0;
        int mask = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = length - 1; i >= 0; i--) {
            set.clear();
            int bit = 1 << i;
            mask |= bit;
            int temp = res | bit;
            for (int num : nums) {
                if (set.contains((num & mask) ^ temp)) {
                    res = temp;
                    break;
                }
                set.add(num & mask);
            }
        }
        return res;
    }
}
