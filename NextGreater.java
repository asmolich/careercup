import java.util.*;

public class NextGreater {
    public static void main(String[] args) {
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.addAll(Arrays.asList(4,5,2,10));

        Solution s = new Solution();
        System.out.println(s.nextGreater(data));
    }
}
class Solution {
    public ArrayList<Integer> nextGreater(ArrayList<Integer> a) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (a == null || a.isEmpty()) return res;

        for (int i = 0; i < a.size(); i++) {
            Integer ai = a.get(i);
            int minAj = -1;
            for (int j = i + 1; j < a.size(); j++) {
                Integer aj = a.get(j);
                if (aj > ai) {
                    minAj = aj;
                    break;
                }
            }
            res.add(minAj);
        }
        return res;
    }
}

