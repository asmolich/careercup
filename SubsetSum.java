import java.util.ArrayList;

/**
 * Perfect Sum Problem (Print all subsets with given sum)
 * https://www.geeksforgeeks.org/perfect-sum-problem-print-subsets-given-sum/
 * A Java program to count all subsets with given sum.
 * #Hard
 */
public class SubsetSum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5};
        int n = arr.length;
        int sum = 10;
        printAllSubsets(arr, n, sum);
    }

    // dp[i][j] is going to store true if sum j is
    // possible with array elements from 0 to i.
    private static boolean[][] dp;

    private static void display(ArrayList<Integer> v) {
        System.out.println(v);
    }

    // A recursive function to print all subsets with the
    // help of dp[][]. Vector p[] stores current subset.
    private static void printSubsetsRec(int[] arr, int i, int sum, ArrayList<Integer> p) {
        // If we reached end and sum is non-zero. We print
        // p[] only if arr[0] is equal to sun OR dp[0][sum]
        // is true.
        if (i == 0 && sum != 0 && dp[0][sum]) {
            p.add(arr[i]);
            display(p);
            p.clear();
            return;
        }

        // If sum becomes 0
        if (i == 0 && sum == 0) {
            display(p);
            p.clear();
            return;
        }

        // If given sum can be achieved after ignoring current element.
        if (dp[i - 1][sum]) {
            // Create a new vector to store path
            printSubsetsRec(arr, i - 1, sum, new ArrayList<>(p));
        }

        // If given sum can be achieved after considering current element.
        if (sum >= arr[i] && dp[i - 1][sum - arr[i]]) {
            p.add(arr[i]);
            printSubsetsRec(arr, i - 1, sum - arr[i], p);
        }
    }

    // Prints all subsets of arr[0..n-1] with sum 0.
    private static void printAllSubsets(int[] arr, int n, int sum) {
        if (n == 0 || sum < 0)
            return;

        // Sum 0 can always be achieved with 0 elements
        dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; ++i) {
            dp[i][0] = true;
        }

        // Sum arr[0] can be achieved with single element
        if (arr[0] <= sum)
            dp[0][arr[0]] = true;

        // Fill rest of the entries in dp[][]
        for (int i = 1; i < n; ++i)
            for (int j = 0; j < sum + 1; ++j)
                dp[i][j] = (arr[i] <= j) ? (dp[i - 1][j] ||
                        dp[i - 1][j - arr[i]])
                        : dp[i - 1][j];
        if (!dp[n - 1][sum]) {
            System.out.println("There are no subsets with sum " + sum);
            return;
        }

        // Now recursively traverse dp[][] to find all
        // paths from dp[n-1][sum]
        printSubsetsRec(arr, n - 1, sum, new ArrayList<>());
    }
}
