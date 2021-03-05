/**
 * Facebook Interview Preparation
 * <p>
 * 1 Billion Users
 * <p>
 * We have N different apps with different user growth rates. At a given time t, measured in days, the number of users using an app is g^t (for simplicity we'll allow fractional users), where g is the growth rate for that app. These apps will all be launched at the same time and no user ever uses more than one of the apps. We want to know how many total users there are when you add together the number of users from each app.
 * <p>
 * After how many full days will we have 1 billion total users across the N apps?
 * Signature
 * int getBillionUsersDay(float[] growthRates)
 * Input
 * 1.0 < growthRate < 2.0 for all growth rates
 * 1 <= N <= 1,000
 * Output
 * Return the number of full days it will take before we have a total of 1 billion users across all N apps.
 * Example 1
 * growthRates = [1.5]
 * output = 52
 * Example 2
 * growthRates = [1.1, 1.2, 1.3]
 * output = 79
 * Example 3
 * growthRates = [1.01, 1.02]
 * output = 1047
 */
public class OneBillionUsers {
    private static final double ONE_BILLION = 1e9d;
    private static final int GUESS = 5001;

    public static void main(String[] args) {
        OneBillionUsers sol = new OneBillionUsers();
        System.out.println(sol.getBillionUsersDay(new float[]{1.5f})); // 52
        System.out.println(sol.getBillionUsersDay(new float[]{1.1f, 1.2f, 1.3f})); // 79
        System.out.println(sol.getBillionUsersDay(new float[]{1.01f, 1.02f})); // 1047
    }

    public int getBillionUsersDay(float[] growthRates) {
        if (growthRates == null || growthRates.length == 0) return 0;

        int lo = 1;
        int hi = GUESS;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            double growth = calculateGrowth(growthRates, mid);
            if (growth >= ONE_BILLION) {
                if (calculateGrowth(growthRates, mid - 1) < ONE_BILLION) return mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    private double calculateGrowth(float[] growthRates, int pow) {
        double res = 0;
        for (float rate : growthRates) {
            res += Math.pow(rate, pow);
        }
        return res;
    }
}
