import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode
 * 645. Set Mismatch
 * https://leetcode.com/problems/set-mismatch/
 * #Easy
 */
public class SetMismatch {
    public static void main(String[] args) {
        SetMismatch sol = new SetMismatch();
        System.out.println(Arrays.toString(sol.findErrorNums(new int[]{1, 2, 2, 4}))); // [1,2,2,4]
        System.out.println(Arrays.toString(sol.findErrorNums(new int[]{1, 1}))); // [1,2]
    }

    public int[] findErrorNums(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int[] res = new int[2];
        for (int num : nums) {
            if (set.contains(num)) {
                res[0] = num;
            }
            set.add(num);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                res[1] = i;
                break;
            }
        }
        return res;
    }
}
