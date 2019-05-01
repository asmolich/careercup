import java.util.ArrayList;
import java.util.List;

class Parentheses {

    /**
     * leetcode 22. Generate Parentheses
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(result, new char[2 * n], n, n, 0);
        return result;
    }

    private void generateParenthesis(List<String> res, char[] current, int leftNum, int rightNum, int index) {
        if (leftNum == 0 && rightNum == 0) {
            if (isValid(current)) res.add(new String(current));
        } else {
            current[index] = '(';
            if (leftNum > 0) generateParenthesis(res, current, leftNum - 1, rightNum, index + 1);

            current[index] = ')';
            if (rightNum > 0) generateParenthesis(res, current, leftNum, rightNum - 1, index + 1);
        }
    }

    public boolean isValid(char[] str) {
        int open = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') open++;
            else if (open > 0) open--;
            else return false;
        }
        return open == 0;
    }

    public static void main(String[] args) {
        Parentheses parenthesis = new Parentheses();

        for (int i = 0; i < 6; i++) {
            System.out.println("Generate sequence of " + i + " parenthesis: " + parenthesis.generateParenthesis(i));
        }
    }
}
