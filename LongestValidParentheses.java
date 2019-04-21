import java.util.ArrayDeque;
import java.util.Deque;

class IndexPair {
    int i;
    int lastRead;
    int length;

    @Override
    public String toString() {
        return "IndexPair{" +
            "i=" + i +
            ", lastRead=" + lastRead +
            ", length=" + length +
            '}';
    }
}

class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;

        //System.out.println(s);

        int length = 0;
        int maxLength = 0;
        int lastRead = -1;
        Deque<IndexPair> deq = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            //System.out.println(deq);
            if (s.charAt(i) == ')') {
                if (deq.isEmpty()) {
                    length = 0;
                    continue;
                }
                IndexPair idx = deq.removeLast();
                //System.out.println("idx = " + idx + ", lastRead = " + lastRead);
                if (idx.lastRead == idx.i - 1)
                    length = idx.length + i - idx.i + 1;
                else
                    length = i - idx.i + 1;
                lastRead = i;
                if (maxLength < length) maxLength = length;
                //System.out.println("length = " + length + ", maxLength = " + maxLength);
            } else {
                IndexPair idx = new IndexPair();
                idx.i = i;
                idx.lastRead = lastRead;
                idx.length = length;
                deq.addLast(idx);
            }
        }
        return maxLength;
    }
}

class LongestValidParentheses {
    public static void main(String[] args) {
        Solution s = new Solution();
        //System.out.println(s.longestValidParentheses("(()())()((()(())))")); //18
        System.out.println(s.longestValidParentheses("()(())")); //6
    }
}

