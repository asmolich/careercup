import java.util.Arrays;

/**
 * Facebook Interview Preparation
 * <p>
 * Revenue Milestones
 * <p>
 * We keep track of the revenue Facebook makes every day, and we want to know on what days Facebook hits certain revenue milestones. Given an array of the revenue on each day, and an array of milestones Facebook wants to reach, return an array containing the days on which Facebook reached every milestone.
 * <p>
 * Signature
 * int[] getMilestoneDays(int[] revenues, int[] milestones)
 * <p>
 * Input
 * revenues is a length-N array representing how much revenue FB made on each day (from day 1 to day N). milestones is a length-K array of total revenue milestones.
 * <p>
 * Output
 * Return a length-K array where K_i is the day on which FB first had milestones[i] total revenue. If the milestone is never met, return -1.
 * <p>
 * Example
 * revenues = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
 * milestones = [100, 200, 500]
 * output = [4, 6, 10]
 * <p>
 * Explanation
 * On days 4, 5, and 6, FB has total revenue of $100, $150, and $210 respectively. Day 6 is the first time that FB has >= $200 of total revenue.
 */
public class RevenueMilestones {
    public static void main(String[] args) {
        RevenueMilestones sol = new RevenueMilestones();
        System.out.println(Arrays.toString(sol.getMilestoneDays(new int[]{100, 200, 300, 400, 500}, new int[]{300, 800, 1000, 1400}))); // [2, 4, 4, 5]
        System.out.println(Arrays.toString(sol.getMilestoneDays(new int[]{700, 800, 600, 400, 600, 700}, new int[]{3100, 2200, 800, 2100, 1000}))); // [5, 4, 2, 3, 2]
    }

    public int[] getMilestoneDays(int[] revenues, int[] milestones) {
        if (revenues == null || revenues.length == 0 || milestones == null || milestones.length == 0) return new int[0];

        int n = revenues.length;
        int k = milestones.length;
        int[] sum = new int[n];
        sum[0] = revenues[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + revenues[i];
        }
        // System.out.println(Arrays.toString(sum));
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = 1 + binarySearch(sum, milestones[i]);
        }
        return res;
    }

    private int binarySearch(int[] a, int k) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // System.out.println(Map.of("lo" ,lo, "hi", hi, "k", k));
            int mid = lo + (hi - lo) / 2;
            if (a[mid] >= k) {
                if (mid > 0) {
                    if (a[mid - 1] < k) {
                        return mid;
                    }
                } else {
                    return 0;
                }
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }
}
