import java.util.ArrayList;
import java.util.List;

/**
 * Sieve of Eratosthenes
 * https://www.geeksforgeeks.org/sieve-of-eratosthenes/
 * Time: O(N*log(log(N)))
 * Space: O(n)
 */
public class PrimeGenerator {
    public static void main(String[] args) {
        System.out.println(primes(100));
    }

    public static List<Integer> primes(int n) {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i < n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a prime
            if (prime[p]) {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        List<Integer> primes = new ArrayList<>();
        // Collect all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i]) primes.add(i);
        }
        return primes;
    }
}
