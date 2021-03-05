import java.util.Arrays;

public class _2048 {

    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
    };

    public void operation(int[][] board, int shiftDir) {
        int n = board.length;

        if (shiftDir == LEFT) {
            for (int i = 0; i < n; i++) {
                int ptr = 0;
                for (int j = ptr + 1; j < n; j++) {
                    if (board[i][j] > 0) {
                        if (board[i][ptr] > 0) {
                            if (board[i][j] == board[i][ptr]) {
                                board[i][ptr] <<= 1;
                                board[i][j] = 0;
                                ptr++;
                            }
                        } else {
                            // board[i][ptr] == 0
                            board[i][ptr] = board[i][j];
                            board[i][j] = 0;
                        }
                    } else {
                        if (board[i][ptr] > 0) {
                            ptr++;
                        }
                    }
                }
                if (ptr < n) {
                    for (int j = ptr; j < n; j++) {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {2, 2, 4, 4},
                {2, 0, 0, 4},
                {0, 2, 4, 2},
                {0, 2, 2, 4}
        };
        new _2048().operation(board, LEFT);
        System.out.println(Arrays.deepToString(board));
    }
}
