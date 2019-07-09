import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * InterviewBit. Min XOR Value. https://www.interviewbit.com/problems/min-xor-value/
 */
public class MinXorValue {
    public static void main(String[] args) {
        int[] data = {
            0, 2, 5, 7
            //0,010,101,111
        };
        System.out.println(minXorBruteForce(data));
        System.out.println(minXor(data));
    }

    static class TrieNode {
        TrieNode zero;
        TrieNode one;
        Integer val;
        int count = 0;

        @Override
        public String toString() {
            return "{" + (zero != null ? 0 : "") + (one != null ? 1 : "") + (val == null ? "" : "v=" + val) + ", count=" + count + '}';
        }
    }

    public static void print(TrieNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        Deque<TrieNode> deq = new ArrayDeque<>();
        deq.add(root);
        while (!deq.isEmpty()) {
            TrieNode node = deq.removeLast();
            System.out.println(node);
            if (node.zero != null) deq.addLast(node.zero);
            if (node.one != null) deq.addLast(node.one);
        }
    }

    private static int minXor(int[] nums) {
        if (nums == null || nums.length < 2) return -1;

        final int[] ints = Arrays.copyOf(nums, nums.length);
        Arrays.sort(ints);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < ints.length; i++) {
            min = Math.min(min, ints[i] ^ ints[i - 1]);
        }
        return min;
    }

    private static int maxXor(int[] nums) {
        if (nums == null || nums.length < 2) return -1;

        int min = Integer.MAX_VALUE;

        TrieNode root = new TrieNode();
        TrieNode node;
        for (int ni : nums) {
            node = root;
            for (int shift = 31; shift >= 0; shift--) {
                if (((ni >> shift) & 1) == 1) {
                    if (node.one == null) {
                        node.one = new TrieNode();
                        node.one.count = 1;
                        if (shift == 0) node.one.val = ni;
                    } else {
                        node.one.count++;
                    }
                    node = node.one;
                } else {
                    if (node.zero == null) {
                        node.zero = new TrieNode();
                        node.zero.count = 1;
                        if (shift == 0) node.zero.val = ni;
                    } else {
                        node.zero.count++;
                    }
                    node = node.zero;
                }
            }
        }

        //print(root);

        TrieNode parent;
        all:
        for (int ni : nums) {
            node = root;
            parent = null;
            for (int shift = 31; shift >= 0; shift--) {
                if (((ni >> shift) & 1) == 1) {
                    //searchNode = node;
                    print(parent);
                    break all;
                    //node = node.one;
                } else {
                    parent = node;
                    node = node.zero;
                }
            }
        }
        return min;
    }

    // O(n^2)
    private static int minXorBruteForce(int[] nums) {
        if (nums == null || nums.length < 2) return -1;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;
                int xor = nums[i] ^ nums[j];
                min = Math.min(min, xor);
            }
        }
        return min;
    }
}
