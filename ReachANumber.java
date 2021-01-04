/**
 * LeetCode 754. Reach a Number
 * https://leetcode.com/problems/reach-a-number/
 * #Meduim
 */
public class ReachANumber {
    public int reachNumber(int target) {
        int n = (int) Math.ceil((Math.sqrt(1.0 + 8.0 * Math.abs(target)) - 1) / 2);
        int d = n * (n + 1) / 2 - target;
        return n + (d % 2) * (n % 2 + 1);
    }

    public static void main(String[] args) {
        ReachANumber sol = new ReachANumber();
        System.out.println(sol.reachNumber(3));
        System.out.println(sol.reachNumber(2));
    }
}
