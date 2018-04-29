
public class BalloonPopping {

    // Subproblem: DP[i][j] = max points you can get from popping between i and j
    // Guess: popping this one first will give us more points? it won't give us more points
    // Recurrence Relation: Max(DP from left to right or B*left*right + left over + right over)
    // Memo/Recurse: DP[left][right] = Max(DP from left to right or B*left*right + left over + right over)
    // Solve final: return DP[0][DP.length-1]
    // runtime and space o(n^2) where n is length of B
    // this is a bottom up approach

    public static int maxPoints(int[] B) {
        // reconstruct array with ones at either end
        int[] BwithOnes = new int[B.length+2];
        for(int i=1; i<B.length+1; i++){
            BwithOnes[i] = B[i-1];
        }
        BwithOnes[0] = 1;
        BwithOnes[B.length+1] = 1;

        // set the not really existent endpoints to 0 in DP
        int[][] DP = new int[BwithOnes.length][BwithOnes.length];
        DP[0][0] = 0;
        DP[BwithOnes.length-1][BwithOnes.length-1] = 0;

        for (int k = 2; k < DP.length; k++) {
            for (int left = 0; left < DP.length - k; left++) {
                int right = left + k;
                for (int i = left + 1; i < right; i++)
                    DP[left][right] = Math.max(DP[left][right], BwithOnes[left] * BwithOnes[i] * BwithOnes[right] + DP[left][i] + DP[i][right]);
            }
        }
        // solution is top right corner of DP
        return DP[0][DP.length - 1];
    }
}
