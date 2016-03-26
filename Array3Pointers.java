import java.util.*;
public class Array3Pointers {
    public static void main(String[] args) {
		Array3Pointers a3p = new Array3Pointers();
		List<Integer> a = Arrays.asList(1, 4, 10);
		List<Integer> b = Arrays.asList(2, 15);
		List<Integer> c = Arrays.asList(10, 12);
		System.out.println(a3p.minimize(a, b, c));
	}

    class ListIndex implements Comparable<ListIndex> {
        List<Integer> l;
        int idx;
        ListIndex(List<Integer> list,int index) {l=list;idx=index;}
        @Override
        public int compareTo(ListIndex li) {
            return Integer.compare(l.get(idx), li.l.get(li.idx));
        }
        @Override
        public String toString() {
            return "" + l + '['+idx+']';
        }
        public boolean hasNext() {
            return idx + 1 < l.size();
        }
    }

	public int minimize(final List<Integer> a, final List<Integer> b, final List<Integer> c) {
        if (a == null || a.isEmpty() || b == null || b.isEmpty() || c == null || c.isEmpty()) return 0;
       
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);

        //ListIndex[] arr = new ListIndex[]{new ListIndex(a, 0), new ListIndex(b, 0), new ListIndex(c, 0)};
        ListIndex ai = new ListIndex(a, 0);
        ListIndex bj = new ListIndex(b, 0);
        ListIndex ck = new ListIndex(c, 0);

        int minDiff = Integer.MAX_VALUE;
        while (!ai.l.isEmpty() || !bj.l.isEmpty() || !ck.l.isEmpty()) {
            ListIndex min = min(ai, bj, ck);
            ListIndex max = max(ai, bj, ck);

            System.out.println("ai = " + ai);
            System.out.println("ai.cT(bj) = " + ai.compareTo(bj));
            
            System.out.println("min = " + min);
            System.out.println("max = " + max);

            int diff = max.l.get(max.idx) - min.l.get(min.idx);
            if (diff == 0) return 0;

            if (diff < minDiff) minDiff = diff;

            if (min.hasNext()) min.idx++;
            else break;
        }

        /*
        int i = 0, j = 0, k = 0, count = 0;
        while(i < a.size() || j < b.size() || k < c.size()) {
            int ai = a.get(i);
            int bj = b.get(j);
            int ck = c.get(k);

            int min = min(ai, bj, ck);
            List<Integer> minList = null;
            int minIdx = 0;
            if (min == ai) {
                minList = a;
                minIdx = i;
            }
            else if (min == bj) {
                minList = b;
                minIdx = j;
            }
            else {
                minList = c;
                minIdx = k;
            }
            
            int max = max(ai, bj, ck);
            List<Integer> maxList = null;
            if (max == ai) maxList = a;
            else if (max == bj) maxList = b;
            else maxList = c;
            
            //balance
            int diff = abs(max - min);
            int d;
            if ((minIdx == i && minIdx < a.size() - 1) ||
                (minIdx == j && minIdx < b.size() - 1) ||
                (minIdx < c.size() - 1))
                d = Math.min(diff, abs(max - minList.get(minIdx + 1)));
            else d = diff;

            if (diff != d) {
                if (minIdx == i) i++;
                else if (minIdx == j) j++;
                else k++;
                count = 0;
            }
            else {
                count++;
            }
            if (count >= 3) break;
        }
        */
        //return max(abs(a.get(i) - b.get(j)), abs(b.get(j) - c.get(k)), abs(c.get(k) - a.get(i)));
        return abs(minDiff);
    }
    private ListIndex min(ListIndex a, ListIndex b) {
        return a.compareTo(b) < 1 ? a : b;
    }
    private ListIndex min(ListIndex a, ListIndex b, ListIndex c) {
        return min(min(a, b), c); 
    }
    private ListIndex max(ListIndex a, ListIndex b) {
        return a.compareTo(b) > -1 ? a : b;
    }
    private ListIndex max(ListIndex a, ListIndex b, ListIndex c) {
        return max(max(a, b), c); 
    }

    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
    private int abs(int x) {
        if (x < 0) return -x;
        return x;
    }
}

