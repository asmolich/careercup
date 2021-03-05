import java.util.Arrays;

/**
 * LeetCode
 * 1769. Minimum Number of Operations to Move All Balls to Each Box
 * https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 * #Medium #Greedy
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    public static void main(String[] args) {
        MinimumNumberOfOperationsToMoveAllBallsToEachBox sol = new MinimumNumberOfOperationsToMoveAllBallsToEachBox();
        System.out.println(Arrays.toString(sol.minOperations("110"))); // [1,1,3]
        System.out.println(Arrays.toString(sol.minOperations("001011"))); // [11,8,5,4,3,4]
    }

    public int[] minOperations(String b) {
        if (b == null || b.isEmpty()) return new int[0];

        int n = b.length();
        int first = 0;
        int onesTotal = 0;
        for (int i = 0; i < n; i++) {
            char ch = b.charAt(i);
            if (ch == '1') {
                first += i;
                onesTotal++;
            }
        }
        int[] res = new int[n];
        int moves = 0;
        int last = first + onesTotal;
        for (int i = 0; i < n; i++) {
            char ch = b.charAt(i);
            res[i] = last - onesTotal + moves;
            if (ch == '1') {
                moves += 2;
            }
            last = res[i];
        }
        return res;
    }
}
