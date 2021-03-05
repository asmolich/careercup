import java.util.Arrays;

/**
 * LeetCode
 * 204. Count Primes
 * https://leetcode.com/problems/count-primes/
 * #Easy #Prime
 */
public class CountPrimes {
    public static void main(String[] args) {
        CountPrimes sol = new CountPrimes();
        System.out.println(sol.countPrimes(10)); // 4
        System.out.println(sol.countPrimes(0)); // 0
        System.out.println(sol.countPrimes(1)); // 0
        System.out.println(sol.countPrimes(100)); // 25
        System.out.println(sol.countPrimes(2)); // 0
        System.out.println(sol.countPrimes(6)); // 3
    }

    public int countPrimes(int n) {
        if (n < 2) return 0;
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i < n; i++) {
            if (primes[i]) {
                for (int j = i * 2; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (primes[i]) res++;
        }
        return res;
    }
}
