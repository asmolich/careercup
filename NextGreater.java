import java.util.ArrayList;
import java.util.Arrays;

public class NextGreater {
    public static void main(String[] args) {
        ArrayList<Integer> data = new ArrayList<>(Arrays.asList(4, 5, 2, 10));

        System.out.println(nextGreater(data));
    }

    private static ArrayList<Integer> nextGreater(ArrayList<Integer> a) {
        ArrayList<Integer> res = new ArrayList<>();
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

