import java.util.PriorityQueue;

/**
 * LeetCode
 * 358. Rearrange String k Distance Apart
 * https://tenderleo.gitbooks.io/leetcode-solutions-/content/GoogleHard/358.html
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/
 */
public class RearrangeStringKDistanceApart {
    public static void main(String[] args) {
        RearrangeStringKDistanceApart sol = new RearrangeStringKDistanceApart();
        System.out.println(sol.rearrangeString("aabbcc", 3)); // "abcabc"
        System.out.println(sol.rearrangeString("aaabc", 3)); // ""
        System.out.println(sol.rearrangeString("aaadbbcc", 2)); // "abacabcd"
    }

    public String rearrangeString(String s, int k) {
        if (s == null || s.isEmpty()) return "";
        int n = s.length();
        int[][] fr = new int[26][2];
        for (int i = 0; i < 26; i++) {
            fr[i][0] = i;
        }

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            fr[ch - 'a'][1]++;
        }
        PriorityQueue<int[]> maxQ = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        for (int i = 0; i < 26; i++) {
            maxQ.add(fr[i]);
        }
        if (!maxQ.isEmpty() && maxQ.peek()[1] > n / k + 1) return "";

        char[] res = new char[n];
        int cycle = 0;
        int idx = 0;
        while (!maxQ.isEmpty()) {
            int[] max = maxQ.poll();
            while (max[1]-- > 0) {
                if (idx >= n) idx = ++cycle;
                res[idx] = (char) (max[0] + 'a');
                idx += k;
            }
        }
        return new String(res);
    }
}
