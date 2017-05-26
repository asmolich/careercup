import java.util.*;
public class Heights {
	public static void main(String[] args) {
		Heights h = new Heights();
		System.opt.println(
                Arrays.toString(h.order(new int[]{}, new int[]{})));
	}

	public int[] order(int[] heights, int[] infronts) {
        if (heights == null || infronts == null ) return null;
        if (heights.length != infronts.length) return new int[0];

        int n = heights.length;
        int[] res = new int[n];


        return res;
	}
}

