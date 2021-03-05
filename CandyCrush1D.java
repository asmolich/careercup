import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode
 * CandyCrush - 1D
 * https://leetcode.com/discuss/interview-question/380650/Bloomberg-or-Phone-Screen-or-Candy-Crush-1D
 * #Medium #Stack #CandyCrush
 */
/*
def candy_crush(input):
    if not input:
        return input

    stack = []
    stack.append([input[0], 1])

    for i in range(1, len(input)):
        if input[i] != input[i-1]:
            if stack[-1][1] >= 3:
                stack.pop()
            if stack and stack[-1][0] == input[i]:
                stack[-1][1] += 1
            else:
                stack.append([input[i], 1])
        else:
            stack[-1][1] += 1

    # handle end
    if stack[-1][1] >= 3:
        stack.pop()

    out = []
    for ltrs in stack:
        out += ltrs[0] * ltrs[1]

    return ''.join(out)

print(candy_crush("aaaabbbc")) #c
print(candy_crush("aabbbacd")) #cd
print(candy_crush("aabbccddeeedcba")) #blank expected
print(candy_crush("aabbbaacd")) #cd
print(candy_crush("dddabbbbaccccaax")) #x
 */
public class CandyCrush1D {
    public static void main(String[] args) {
        CandyCrush1D sol = new CandyCrush1D();
        System.out.println(sol.candyCrush("aaaabbbc")); // c
        System.out.println(sol.candyCrush("aabbbacd")); // cd
        System.out.println(sol.candyCrush("aabbccddeeedcba")); // #blank expected
        System.out.println(sol.candyCrush("aabbbaacd")); // cd
        System.out.println(sol.candyCrush("dddabbbbaccccaax")); // x
    }

    static class CharCount {
        char ch;
        int count;

        public CharCount(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public String candyCrush(String s) {
        if (s == null || s.isEmpty()) return "";

        Deque<CharCount> deq = new ArrayDeque<>();
        deq.addLast(new CharCount(s.charAt(0), 1));

        int n = s.length();
        for (int i = 1; i < n; i++) {
            char ch = s.charAt(i);

            if (ch != s.charAt(i - 1)) {
                if (!deq.isEmpty() && deq.peekLast().count >= 3) deq.removeLast();
                if (!deq.isEmpty() && deq.peekLast().ch == ch) deq.peekLast().count++;
                else deq.addLast(new CharCount(ch, 1));
            } else if (!deq.isEmpty()) {
                deq.peekLast().count++;
            }
        }
        if (!deq.isEmpty() && deq.peekLast().count >= 3) deq.removeLast();

        StringBuilder sb = new StringBuilder();
        for (CharCount cc : deq) {
            sb.append(("" + cc.ch).repeat(cc.count));
        }
        return sb.toString();
    }
}
