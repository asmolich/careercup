import java.util.Arrays;

/**
 * InterviewBit
 * Maximum Area of Triangle!
 * https://www.interviewbit.com/problems/maximum-area-of-triangle/
 * https://www.geeksforgeeks.org/maximum-area-triangle-different-vertex-colors/
 * #Hard
 */
public class MaximumAreaOfTriangle {
    public static void main(String[] args) {
        MaximumAreaOfTriangle sol = new MaximumAreaOfTriangle();
        System.out.println(sol.solve(new String[]{"rrrrr", "rrrrg", "rrrrr", "bbbbb"})); // 10
        System.out.println(sol.solve(new String[]{"rrr", "rrr", "rrr", "rrr"})); // 0
        System.out.println(sol.solve(new String[]{"rrrbr", "ggrgb", "bbbrg", "rrgrr", "gbggb", "rbbgr", "rbgrg", "bbbgr", "ggbbb", "bggbr", "ggrbb", "grrrg", "rbrrg", "brrgr", "rrgbg", "bbrgr", "gbbbr", "rrbgb", "brbbr", "bgrrr", "bbggr", "rggbg", "bbggg", "gggbb", "bgbbg", "rrbgr", "rggrb", "grggr", "rggrr"})); // 73
    }

    private static class RGB {
        int[] colors;

        public RGB(int r, int g, int b) {
            colors = new int[]{r, g, b};
        }

        int r() {
            return colors[0];
        }

        int g() {
            return colors[1];
        }

        int b() {
            return colors[2];
        }

        void r(int c) {
            colors[0] = c;
        }

        void g(int c) {
            colors[1] = c;
        }

        void b(int c) {
            colors[2] = c;
        }

        @Override
        public String toString() {
            return Arrays.toString(colors);
        }
    }

    public int solve(String[] a) {
        if (a == null || a.length == 0) return 0;

        int m = a.length;
        int n = a[0].length();
        RGB[][] tb = new RGB[n][2];
        RGB left = new RGB(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        RGB right = new RGB(-1, -1, -1);
        for (String s : a) {
            for (int col = 0; col < n; col++) {
                char ch = s.charAt(col);
                if (ch == 'r') {
                    left.r(Math.min(left.r(), col));
                    right.r(Math.max(right.r(), col));
                } else if (ch == 'g') {
                    left.g(Math.min(left.g(), col));
                    right.g(Math.max(right.g(), col));
                } else if (ch == 'b') {
                    left.b(Math.min(left.b(), col));
                    right.b(Math.max(right.b(), col));
                }
            }
        }
        System.out.println("left = " + left);
        System.out.println("right = " + right);
        for (int col = 0; col < n; col++) {
            RGB top = new RGB(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
            RGB bottom = new RGB(-1, -1, -1);
            for (int row = 0; row < m; row++) {
                char ch = a[row].charAt(col);
                if (ch == 'r') {
                    top.r(Math.min(top.r(), row));
                    bottom.r(Math.max(bottom.r(), row));
                } else if (ch == 'g') {
                    top.g(Math.min(top.g(), row));
                    bottom.g(Math.max(bottom.g(), row));
                } else if (ch == 'b') {
                    top.b(Math.min(top.b(), row));
                    bottom.b(Math.max(bottom.b(), row));
                }
            }
            tb[col][0] = top;
            tb[col][1] = bottom;
        }
        for (int i = 0; i < tb.length; i++) {
            System.out.println("tb[" + i + "] = " + Arrays.toString(tb[i]));
        }
        int res = 0;
        for (int col = 0; col < n; col++) {
            RGB top = tb[col][0];
            RGB bottom = tb[col][1];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;
                    int k = 3 - i - j;
                    // (i, j, k) - rgb colors
                    if (top.colors[i] != Integer.MAX_VALUE &&
                            bottom.colors[j] != -1 &&
                            left.colors[k] != Integer.MAX_VALUE) {
                        res = Math.max(res, (int) Math.ceil((bottom.colors[j] - top.colors[i] + 1) * (i - left.colors[k] + 1) / 2.0));
                    }
                    if (top.colors[i] != Integer.MAX_VALUE &&
                            bottom.colors[j] != -1 &&
                            right.colors[k] != -1) {
                        res = Math.max(res, (int) Math.ceil((bottom.colors[j] - top.colors[i] + 1) * (right.colors[k] - i + 1) / 2.0));
                    }
                }
            }
        }
        return res;
    }
}
