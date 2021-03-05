/**
 * Facebook Interview Preparation
 * <p>
 * Change in a Foreign Currency
 * <p>
 * You likely know that different currencies have coins and bills of different denominations. In some currencies, it's actually impossible to receive change for a given amount of money. For example, Canada has given up the 1-cent penny. If you're owed 94 cents in Canada, a shopkeeper will graciously supply you with 95 cents instead since there exists a 5-cent coin.
 * <p>
 * Given a list of the available denominations, determine if it's possible to receive exact change for an amount of money targetMoney. Both the denominations and target amount will be given in generic units of that currency.
 * <p>
 * Signature
 * boolean canGetExactChange(int targetMoney, int[] denominations)
 * <p>
 * Input
 * 1 ≤ |denominations| ≤ 100
 * 1 ≤ denominations[i] ≤ 10,000
 * 1 ≤ targetMoney ≤ 1,000,000
 * <p>
 * Output
 * Return true if it's possible to receive exactly targetMoney given the available denominations, and false if not.
 * <p>
 * Example 1
 * denominations = [5, 10, 25, 100, 200]
 * targetMoney = 94
 * output = false
 * Every denomination is a multiple of 5, so you can't receive exactly 94 units of money in this currency.
 * <p>
 * Example 2
 * denominations = [4, 17, 29]
 * targetMoney = 75
 * output = true
 * You can make 75 units with the denominations [17, 29, 29].
 */
public class ChangeInAForeignCurrency {
    public static void main(String[] args) {
        ChangeInAForeignCurrency sol = new ChangeInAForeignCurrency();
        System.out.println(sol.canGetExactChange(94, new int[]{5, 10, 25, 100, 200})); // false
        System.out.println(sol.canGetExactChange(75, new int[]{4, 17, 29})); // true
    }

    public boolean canGetExactChange(int targetMoney, int[] denominations) {
        if (targetMoney < 0) return false;
        if (targetMoney == 0) return true;
        boolean res = false;
        for (int den : denominations) {
            res |= canGetExactChange(targetMoney - den, denominations);
        }
        return res;
    }
}
