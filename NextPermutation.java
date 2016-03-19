import java.util.*;
public class NextPermutation{
    public static void main(String[] args) {
        Solution s = new Solution();
        ArrayList<Integer> data = new ArrayList<Integer>();
        //data.addAll(Arrays.asList(1, 2, 3));         // -> 1 3 2
        //data.addAll(Arrays.asList(1, 2, 3, 6, 5, 4));// -> 1 2 4 3 5 6 
        data.addAll(Arrays.asList(444, 994, 508, 72, 125, 299, 181, 238, 354, 223, 691, 249, 838, 890, 758, 675, 424, 199, 201, 788, 609, 582, 979, 259, 901, 371, 766, 759, 983, 728, 220, 16, 158, 822, 515, 488, 846, 321, 908, 469, 84, 460, 961, 285, 417, 142, 952, 626, 916, 247, 116, 975, 202, 734, 128, 312, 499, 274, 213, 208, 472, 265, 315, 335, 205, 784, 708, 681, 160, 448, 365, 165, 190, 693, 606, 226, 351, 241, 526, 311, 164, 98, 422, 363, 103, 747, 507, 669, 153, 856, 701, 319, 695, 52));
        System.out.println("data = " + data);
        s.nextPermutation(data);
        System.out.println("next = " + data);
    }
}

class Solution {

    /**
     * Find the highest index i such that s[i] < s[i+1]. If no such index exists, the permutation is the last permutation.
     * Find the highest index j > i such that s[j] > s[i]. Such a j must exist, since i+1 is such an index.
     * Swap s[i] with s[j].
     * Reverse the order of all of the elements after index i till the last element.
     */
    public void nextPermutation(ArrayList<Integer> a) {
        if (a == null || a.size() < 2) return;

        int n = a.size();
        int i = n - 2;
        for (; i >= 0 && a.get(i) >= a.get(i + 1); i--);
        if (i < 0) {
            Collections.sort(a);
            return;
        }

        int j = n - 1;
        for (; j > i && a.get(i) >= a.get(j); j--);
        swap(a, i, j);
        reverse(a, i + 1, n - 1);
    }

    private void reverse(ArrayList<Integer> a, int i, int j) {
        while (i < j) {
            Integer temp = a.get(i);
            a.set(i, a.get(j));
            a.set(j, temp);
            i++; j--;
        }
    }
    private void swap(ArrayList<Integer> a, int i, int j) {
        Integer temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
}

