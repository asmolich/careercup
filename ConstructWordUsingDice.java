import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode
 * Construct a word using dice
 * Google. On-site
 * https://leetcode.com/discuss/interview-question/267985/
 *
 * <p>Given a word of length <code>n</code> and <code>n</code> six-sided dice with a character in each side. Find out if this word can be constructed by the set of given dice.</p>
 * <p><strong>Example 1:</strong>
 * </p><pre><code>Input:
 * word = "hello"
 * dice = [[a, l, c, d, e, f], [a, b, c, d, e, f], [a, b, c, h, e, f], [a, b, c, d, o, f], [a, b, c, l, e, f]]
 * Output: true
 * Explanation: dice[2][3] + dice[1][4] + dice[0][1] + dice[4][3] + dice[3][4]
 * </code></pre>
 * <p><strong>Example 2:</strong>
 * </p><pre><code>Input:
 * word = "hello"
 * dice = [[a, b, c, d, e, f], [a, b, c, d, e, f], [a, b, c, d, e, f], [a, b, c, d, e, f], [a, b, c, d, e, f]]
 * Output: false
 * </code></pre>
 * <p><strong>Example 3:</strong>
 * </p><pre><code>Input:
 * word = "aaaa"
 * dice = [[a, a, a, a, a, a], [b, b, b, b, b, b], [a, b, c, d, e, f], [a, b, c, d, e, f]]
 * Output: false
 * </code></pre><p></p>
 */
public class ConstructWordUsingDice {
    /**
     * Example 1.
     * word = "hello"
     * dice = [[a, l, c, d, e, f], [a, b, c, d, e, f], [a, b, c, h, e, f], [a, b, c, d, o, f], [a, b, c, l, e, f]]
     * <p>
     * Flow Graph
     * | a   b   c   d  [e]  f   g  [h]  i   j   k  [l]  m   n  [o]  p   q   r   s   t   u   v   w   x   y   z
     * 0| 1   0   1   1   1   1   0   0   0   0   0   1   0   0   0   0   0   0   0   0   0   0   0   0   0   0
     * 1| 1   1   1   1   1   1   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0
     * 2| 1   1   1   0   1   1   0   1   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0
     * 3| 1   1   1   1   0   1   0   0   0   0   0   0   0   0   1   0   0   0   0   0   0   0   0   0   0   0
     * 4| 1   1   1   0   1   1   0   0   0   0   0   1   0   0   0   0   0   0   0   0   0   0   0   0   0   0
     * <p>
     * - 0 (0/1) --e,l-->   | h (0/1)  -----
     * /                      |               \
     * s --- 1 (0/1) --e---->   | e (0/1)  ----- t
     * \ \                      |               /
     * \ -- 2 (0/1) --e,h-->   | l (0/2)  ----/
     * \                      |             /
     * \- 3 (0/1) --o---->   | o (0/1)  --/
     * - 4 (0/1) --e,l-->   |          -/
     * <p>
     * Max Flow S-T = 5:
     * 0-l, 1-e, 2-h, 3-o, 4-l
     * <p>
     * Answer: [2,1,0,4,3]
     */
    public static void main(String[] args) {
        ConstructWordUsingDice sol = new ConstructWordUsingDice();
        System.out.println(Arrays.toString(sol.constructWordUsingDice(new char[][]{
                {'a', 'l', 'c', 'd', 'e', 'f'},
                {'a', 'b', 'c', 'd', 'e', 'f'},
                {'a', 'b', 'c', 'h', 'e', 'f'},
                {'a', 'b', 'c', 'd', 'o', 'f'},
                {'a', 'b', 'c', 'l', 'e', 'f'}
        }, "hello"))); // [2,1,0,4,3]
    }

    /*
     Perfect matching in a Bipartite graph
     h   -   {a b c h e f}
     e   -   {a b c d e f}
     l   -   {a l c d e f}
     l   -   {a b c l e f}
     o   -   {a b c d o f}
     */

    public int[] constructWordUsingDice(char[][] dice, String word) {
        if (dice == null || dice.length == 0 || word == null || word.isEmpty()) return new int[0];
        if (dice.length < word.length()) return new int[0];

        // char to die index
        Map<Character, Set<Integer>> charToDice = buildIndex(dice);
        // edges from word to dice
        int[] matching = new int[word.length()];
        Arrays.fill(matching, -1);
//        while (hasAugmentingPath())

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Set<Integer> diceSet = charToDice.get(ch);
            if (diceSet == null || diceSet.isEmpty()) return new int[0];
            matching[i] = diceSet.iterator().next();
        }

        return new int[0];
    }

    /*
     * is there an augmenting path?
     *   - if so, upon termination adj[] contains the level graph;
     *   - if not, upon termination marked[] specifies those vertices reachable via an alternating
     *     path from one side of the bipartition
     *
     * an alternating path is a path whose edges belong alternately to the matching and not
     * to the matching
     *
     * an augmenting path is an alternating path that starts and ends at unmatched vertices
     *
     * this implementation finds a shortest augmenting path (fewest number of edges), though there
     * is no particular advantage to do so here
     */
//    private boolean hasAugmentingPath(Graph G) {
//        marked = new boolean[V];
//
//        edgeTo = new int[V];
//        for (int v = 0; v < V; v++)
//            edgeTo[v] = -1;
//
//        // breadth-first search (starting from all unmatched vertices on one side of bipartition)
//        Queue<Integer> queue = new Queue<Integer>();
//        for (int v = 0; v < V; v++) {
//            if (bipartition.color(v) && !isMatched(v)) {
//                queue.enqueue(v);
//                marked[v] = true;
//            }
//        }
//
//        // run BFS, stopping as soon as an alternating path is found
//        while (!queue.isEmpty()) {
//            int v = queue.dequeue();
//            for (int w : G.adj(v)) {
//
//                // either (1) forward edge not in matching or (2) backward edge in matching
//                if (isResidualGraphEdge(v, w) && !marked[w]) {
//                    edgeTo[w] = v;
//                    marked[w] = true;
//                    if (!isMatched(w)) return true;
//                    queue.enqueue(w);
//                }
//            }
//        }
//
//        return false;
//    }
//
//    // is the edge v-w a forward edge not in the matching or a reverse edge in the matching?
//    private boolean isResidualGraphEdge(int v, int w) {
//        if ((mate[v] != w) &&  bipartition.color(v)) return true;
//        if ((mate[v] == w) && !bipartition.color(v)) return true;
//        return false;
//    }

    private Map<Character, Set<Integer>> buildIndex(char[][] dice) {
        Map<Character, Set<Integer>> index = new HashMap<>();
        for (char[] die : dice) {
            for (int i = 0; i < die.length; i++) {
                char ch = die[i];
                Set<Integer> set = index.get(ch);
                if (set == null) set = new HashSet<>();
                set.add(i);
                index.put(ch, set);
            }
        }
        return index;
    }
}
