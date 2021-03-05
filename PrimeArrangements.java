import java.util.Arrays;

/**
 * LeetCode
 * 1175. Prime Arrangements
 * https://leetcode.com/problems/prime-arrangements/
 * #Easy
 */
public class PrimeArrangements {
    private static final long MOD = 1_000_000_007L;

    public static void main(String[] args) {
        PrimeArrangements sol = new PrimeArrangements();
        for (int i = 1; i <= 100; i++) {
            System.out.println(sol.numPrimeArrangements(i));
        }
    }

    public int numPrimeArrangements(int n) {
        if (n < 1 || n > 100) {
            System.out.println("expected 'n' to have value from 1 to 100 only");
            return -1;
        }
        int primes = numOfPrimes(n);
        int nonPrimes = n - primes;
        // System.out.println("primes = " + primes + ", nonPrimes = " + nonPrimes);

        long perPrimes = permutations(primes);
        long perNonPrimes = permutations(nonPrimes);
        // System.out.println("perPrimes = " + perPrimes + ", perNonPrimes = " + perNonPrimes);

        return (int) ((perPrimes * perNonPrimes) % MOD);
    }

    private long permutations(int r) {
        // r!
        long num = 1;
        for (long i = 1; i <= r; i++) {
            num *= i;
            if (num > MOD) num %= MOD;
        }
        return num;
    }

    private int numOfPrimes(int n) {
        if (n < 2) return 0;
        if (n < 3) return 1;
        if (n < 5) return 2;

        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (primes[i]) {
                for (int j = 2 * i; j <= n; j += i) {
                    primes[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (primes[i]) count++;
        }
        return count;
    }
}
