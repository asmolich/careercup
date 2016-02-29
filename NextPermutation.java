import java.util.*;
public class NextPermutation{
    public static void main(String[] args) {
        Solution s = new Solution();
        ArrayList<Integer> data = new ArrayList<Integer>();
        for (int i : new int[]{251, 844, 767, 778, 658, 337, 10, 252, 632, 262, 707, 506, 701, 475, 410, 696, 631, 903, 516, 149, 344, 101, 42, 891, 991}) {
            data.add(i);
        }
        System.out.println("data = " + data);
        s.nextPermutation(data);
        System.out.println("next = " + data);
    }
}

class Solution {
    public void nextPermutation(ArrayList<Integer> a) {
        if (a == null || a.size() < 2) return;

        int i = a.size() - 1;
        for (; i >= 1; i--) {
            Integer curr = a.get(i);
            Integer next = a.get(i - 1);
            if (next < curr) {
                swap(a, i, i - 1);
                break;
            }
        }
        if (i == 0 && a.get(1) < a.get(0))
            swap(a, 0, a.size() - 1);
    }

    private void swap(ArrayList<Integer> a, int i, int j) {
        Integer temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
}

