import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Facebook Interview Preparation
 * <p>
 * Answer a Query
 * <p>
 * Imagine a length-N array of booleans, initially all false. Over time, some values are set to true, and at various points in time you would like to find the location of the nearest true to the right of given indices.
 * <p>
 * You will receive Q queries, each of which has a type and a value. SET queries have type = 1 and GET queries have type = 2.
 * <p>
 * When you receive a SET query, the value of the query denotes an index in the array that is set to true. Note that these indices start at 1. When you receive a GET query, you must return the smallest index that contains a true value that is greater than or equal to the given index, or -1 if no such index exists.
 * <p>
 * Signature
 * int[] answerQueries(ArrayList<Query> queries, int N)
 * <p>
 * Input
 * A list of Q queries, formatted as [type, index] where type is either 1 or 2, and index is < N
 * 1 <= N <= 1,000,000,000
 * 1 <= Q <= 500,000
 * <p>
 * Output
 * Return an array containing the results of all GET queries. The result of queries[i] is the smallest index that contains a true value that is greater than or equal to i, or -1 if no index satisfies those conditions.
 * <p>
 * Example
 * N = 5
 * Q = 5
 * queries = [[2, 3], [1, 2], [2, 1], [2, 3], [2, 2]]
 * output = [-1, 2, -1, 2]
 * <p>
 * The initial state of the array is [false, false, false, false, false].
 * The first query is GET 3, but no values in the array are true, so the answer is -1.
 * The second query is SET 2, so the value at index 2 is set to true.
 * The new state of the array is [false, true, false, false, false].
 * The third query is GET 1, and the index of the true value nearest to 1 (to the right) is 2.
 * The fourth query is GET 3, but no values to the right of index 3 are true.
 * The fifth query is GET 2, and the value at index 2 is true.
 */
public class AnswerAQuery {
    private static final int SET = 1;
    private static final int GET = 2;

    public static void main(String[] args) {
        AnswerAQuery sol = new AnswerAQuery();
        System.out.println(sol.answerQueries(Arrays.asList(
                new Query(2, 3),
                new Query(1, 2),
                new Query(2, 1),
                new Query(2, 3),
                new Query(2, 2)
        ), 5));
    }

    static class Query {
        int type;
        int index;

        public Query(int type, int index) {
            this.type = type;
            this.index = index;
        }
    }

    public List<Integer> answerQueries(List<Query> queries, @SuppressWarnings("unused") int n) {
        if (queries == null || queries.size() == 0) return Collections.emptyList();

        List<Integer> res = new ArrayList<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (Query query : queries) {
            if (query.type == SET) {
                treeSet.add(query.index);
            } else if (query.type == GET) {
                Integer i = treeSet.ceiling(query.index);
                res.add(Objects.requireNonNullElse(i, -1));
            }
        }
        return res;
    }
}
