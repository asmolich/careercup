import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * HackerEarth. Weak Nodes.
 * https://www.hackerearth.com/practice/algorithms/graphs/depth-first-search/practice-problems/algorithm/rhezo-and-super-tanks-a5a3a2f1/
 *
 * <p>
 * You are given N nodes with the following information:
 * <ul>
 *     <li>The nodes are connected by M bidirectional edges</li>
 *     <li>Each node has a value V<sub>i</sub> associated with it</li>
 *     <li>A node is called a weak node if the connections between some other nodes are broken
 *     when that node is deleted and alternate connections are not present</li>
 * </ul>
 * <p>
 * Write a program to tell the number of subsets from all the possible subsets of weak nodes,
 * which on getting their values multiplied gives a number which is divisible by all primes less than 25.
 * Print the answer modulo <code>10<sup>9</sup> + 7</code>.
 * </p>
 * <p>
 *     Constraints:
 *     <pre>
 *         1 <= N,M <= 5*10^4
 *         1 <= U,V <= N
 *         1 <= V <= 10^9
 *     </pre>
 * </p>
 * #Incomplete
 */
public class WeakNodes {
    private static final int MOD_BASE = 1_000_000_007;
    private static final int[] PRIMES_UNDER_25 = {2, 3, 5, 7, 11, 13, 17, 19, 23};
    private static final int PRIMES_MASK = 0b111111111;
    @SuppressWarnings("unused")
    private static final int PRODUCT_OF_ALL_PRIMES_UNDER_25 = 223_092_870;

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) throws IOException {
        Instant start = Instant.now();
        boolean console = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                console ? System.in : new FileInputStream("./WeakNodes_test_1000.txt")
        ))) {
            String[] inp = br.readLine().split(" ");
            int n = Integer.parseInt(inp[0]);
            int m = Integer.parseInt(inp[1]);
            int[] val = new int[n];
            String[] inp1 = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                val[i] = Integer.parseInt(inp1[i]);
            }
            Edge[] edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                String[] inp2 = br.readLine().split(" ");
                edges[i] = Edge.of(
                        Integer.parseInt(inp2[0]),
                        Integer.parseInt(inp2[1]));
            }
            System.out.println("val = " + Arrays.toString(val));
            System.out.println(edges.length + " edges = " + Arrays.toString(edges));
            int out_ = numberOfSubsetsOfWeakNodes(val, edges);
            System.out.println(out_); // 440344932
        }

        System.out.println(Duration.between(Instant.now(), start));
    }

    private static class Edge {
        int u, v;

        static Edge of(int u, int v) {
            Edge edge = new Edge();
            edge.u = u;
            edge.v = v;
            return edge;
        }

        @Override
        public String toString() {
            return "(" + u + ", " + v + ')';
        }
    }

    private static int numberOfSubsetsOfWeakNodes(int[] nodes, Edge[] edges) {
        if (nodes == null || nodes.length == 0 || edges == null || edges.length == 0) return 1;

        int[] primeFactorizedNodes = weakNodes(nodes, edges)
                .stream()
                .mapToInt(i -> nodes[i])
                .map(WeakNodes::mapToPrimeFactorBits)
                .toArray();

        System.out.println(Arrays.toString(primeFactorizedNodes));
        System.out.println(PRIMES_MASK);

        if (primeFactorizedNodes.length == 0) return -1;
        if (primeFactorizedNodes.length == 1) return primeFactorizedNodes[0] == PRIMES_MASK ? 1 : -1;

        boolean[][] dp = new boolean[primeFactorizedNodes.length + 1][PRIMES_MASK + 1];
        dp[0][0] = true;
        System.out.println("\t\t\t    0  " + Arrays.toString(PRIMES_UNDER_25));
        System.out.println("0\t\t\t" + Arrays.toString(dp[0]));
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = true;
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = (primeFactorizedNodes[i - 1] == (j - 1)) || dp[i - 1][j];
                if (!dp[i][j] && j >= primeFactorizedNodes[i - 1]) {
                    dp[i][j] = dp[i][j - primeFactorizedNodes[i - 1]];
                }
            }
            System.out.print(Integer.toBinaryString(primeFactorizedNodes[i - 1]) + "\t");
            System.out.println(Arrays.toString(dp[i]));
        }

        if (!dp[primeFactorizedNodes.length][PRIMES_MASK]) return -1;

        System.out.println("====================");
        printSubsetsRec(dp, primeFactorizedNodes, primeFactorizedNodes.length, PRIMES_MASK, new ArrayList<>(), primeFactorizedNodes);

        return count;
    }

    private static int count = 0;

    private static void printSubsetsRec(boolean[][] dp, int[] arr, int i, int sum, ArrayList<Integer> p, int[] primeFactorizedNodes) {
        // If we reached end and sum is non-zero. We print
        // p[] only if arr[0] is equal to sum OR dp[0][sum]
        // is true.
        if (i <= 1 && sum != 0 && dp[0][sum]) {
            p.add(arr[i]);
            System.out.println(p);
            count += (1 << primeFactorizedNodes.length) - 1 - p.size();
            if (count >= MOD_BASE) {
                count -= MOD_BASE;
            }
            System.out.println(count);
            p.clear();
            return;
        }

        // If sum becomes 0
        if (i <= 1 && sum == 0) {
            System.out.println(p);
            count += (1 << primeFactorizedNodes.length) - 1 - p.size();
            if (count >= MOD_BASE) {
                count -= MOD_BASE;
            }
            System.out.println(count);
            p.clear();
            return;
        }

        // If given sum can be achieved after ignoring current element.
        if (dp[i - 1][sum]) {
            // Create a new vector to store path
            printSubsetsRec(dp, arr, i - 1, sum, new ArrayList<>(p), primeFactorizedNodes);
        }

        // If given sum can be achieved after considering current element.
        if (sum >= arr[i - 1] && dp[i - 1][sum - arr[i - 1]]) {
            p.add(arr[i - 1]);
            printSubsetsRec(dp, arr, i - 1, sum - arr[i - 1], p, primeFactorizedNodes);
        }
    }

    private static int mapToPrimeFactorBits(int num) {
        int res = 0;
        for (int i = 0; i < PRIMES_UNDER_25.length; i++) {
            if (num % PRIMES_UNDER_25[i] == 0) {
                res |= (1 << i);
            }
        }
        return res;
    }

    // time O(V*(V+E)), for complete graphs O(V^3)
    private static List<Integer> weakNodes(int[] nodes, Edge[] edges) {
        if (nodes == null || nodes.length == 0 || edges == null || edges.length == 0) return Collections.emptyList();

        int length = nodes.length;
        @SuppressWarnings("unchecked")
        Deque<Integer>[] adj = new Deque[length];
        for (int i = 0; i < length; i++) adj[i] = new ArrayDeque<>();
        for (Edge edge : edges) {
            adj[edge.u - 1].add(edge.v - 1);
            adj[edge.v - 1].add(edge.u - 1);
        }

        List<Integer> weakNodes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (isWeak(adj, i)) {
                weakNodes.add(i);
            }
        }
        return weakNodes;
    }

    private static boolean isWeak(Deque<Integer>[] adj, int v) {
        Deque<Integer> deq = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        deq.add(v == 0 ? 1 : 0);
        while (!deq.isEmpty()) {
            Integer vertex = deq.removeLast();
            if (visited.contains(vertex)) continue;
            visited.add(vertex);

            if (v == vertex) continue;

            Deque<Integer> adjList = adj[vertex];
            if (!adjList.isEmpty()) {
                deq.addAll(adjList);
            }
        }
        return visited.size() != adj.length;
    }
}
// #Graphs #DFS #DP #SubsetSum
