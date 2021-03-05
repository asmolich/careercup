/**
 * LeetCode
 * 1342. Number of Steps to Reduce a Number to Zero
 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 * #Easy #BitManipulation
 */
public class NumberOfStepsToReduceANumberToZero {
    public static void main(String[] args) {
        NumberOfStepsToReduceANumberToZero sol = new NumberOfStepsToReduceANumberToZero();
        System.out.println(sol.numberOfSteps(14)); // 6
        System.out.println(sol.numberOfSteps(8)); // 4
        System.out.println(sol.numberOfSteps(123)); // 12
        System.out.println(sol.numberOfSteps(0)); // 0
    }

    public int numberOfSteps(int num) {
        if (num == 0) return 0;
        // 101 -> -1, >>2, -1
        return Integer.numberOfTrailingZeros(Integer.highestOneBit(num)) + Integer.bitCount(num);
    }
}
