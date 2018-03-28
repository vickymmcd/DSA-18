import java.util.ArrayList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    /*
    returns true if a queen is found
     */
    public static boolean checkRow(char[][] board, int r){
        int x = 0;
        while(x< board[0].length){
            if(board[x][r] == 'Q') return true;
            x++;
        }
        return false;
    }

    /*
    returns true if a queen is found
     */
    public static boolean checkColumn(char[][] board, int c){
        int y = 0;
        while(y< board.length){
            if(board[c][y] == 'Q') return true;
            y++;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        List<char[][]> answers = new ArrayList<>();
        char[][] queens = new char[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                queens[i][j] = '.';
            }
        }
        int[] columns = new int[n];
        answers = nQueensRecursive(queens, 0, columns, answers);

        return answers;
    }

    private static List<char[][]> addSolution(char[][]board, List<char[][]> curr){
        curr.add(board);
        return curr;
    }

    private static List<char[][]> nQueensRecursive(char[][] answers, int row, int[] columns, List<char[][]> curr){
        if(row==answers.length)
            return addSolution(answers, curr);
        List<char[][]> result= new ArrayList<char[][]>();
        for(int i=0; i<answers.length; i++){
            if(columns[i] == 0){
                columns[i] = 1;
                answers[i][row] = 'Q';
                result = nQueensRecursive(answers, row+1, columns, curr);

                answers[i][row] = '.';
                columns[i] = 0;
            }
        }
        return result;

    }

}
