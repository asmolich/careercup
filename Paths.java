import java.util.*;
public class Paths {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.uniquePaths(1000,3));
	}
}

class Solution {
    public int uniquePaths(int a, int b) {
        if (a < 1 || b < 1) return 0;
		if (a == 1 || b == 1) return 1;

        return c(a - 1, b - 1);
    }

    // (a+b)!/a!/b!
    private int c(int a, int b) {
        int max = Math.max(a, b);
        int i = max == a ? a : b;
        int j = 1;
        int res = 1;
        for (i = i + 1; i <= a + b; i++) {
            res *= i / j++;
        }
        return res;
    }
}
