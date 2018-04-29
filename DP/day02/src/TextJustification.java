import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    // Subproblem: DP[i] = min cost for words from word i to the end
    // Guess: end line after word i? don't end line yet?
    // Recurrence Relation: Min(deleting, inserting, replacing, doing nothing)
    // Memo/Recurse: DP[i][j] = min(deleting, inserting, replacing)
    // Solve final: return DP[a.length][b.length]
    // runtime and space o(n^2) where n is string length
    // this is my bottom up approach

    private static double cost(String[] words, int lo, int hi, int m) {
        if (hi <= lo)
            throw new IllegalArgumentException("Hi must be higher than Lo");
        int length = hi-lo-1; // account for spaces;
        for (int i = lo; i < hi; i++) {
            length += words[i].length();
        }
        if (length > m)
            return Double.POSITIVE_INFINITY;
        return Math.pow(m-length, 3);
    }

    public static List<Integer> justifyText(String[] w, int m) {
        return new ArrayList<Integer>();

    }
}