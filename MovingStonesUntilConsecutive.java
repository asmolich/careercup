import java.util.Arrays;

/**
 * 1033. Moving Stones Until Consecutive
 * https://leetcode.com/contest/weekly-contest-134/problems/moving-stones-until-consecutive/
 */
public class MovingStonesUntilConsecutive {
    private static int[] numMovesStones(int a, int b, int c) {
        if (a >= b && b >= c) return new int[]{0, 0};
        int x = Math.min(a, Math.min(b, c));
        int z = Math.max(a, Math.max(b, c));
        int y = a + b + c - x - z;
        return new int[]{
            Math.min(y - x - 1, 1) + Math.min(z - y - 1, 1),
            z - x - 2
        };
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(numMovesStones(3, 4, 5)));
        System.out.println(Arrays.toString(numMovesStones(3, 3, 3)));
        System.out.println(Arrays.toString(numMovesStones(3, 5, 7)));
        System.out.println(Arrays.toString(numMovesStones(3, 5, 1)));
        System.out.println(Arrays.toString(numMovesStones(3, 5, 6)));
        System.out.println(Arrays.toString(numMovesStones(3, 4, 6)));
        System.out.println(Arrays.toString(numMovesStones(1, 2, 5)));
        System.out.println(Arrays.toString(numMovesStones(2, 4, 1)));
    }
}
