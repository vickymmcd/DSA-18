public class LongestIncreasingSubsequence {

    private static int maxLIS;
    // subproblem: DP[i] = max LIS up to index i
    // guess: local max is next+1 or not
    // recurrence relation: DP[i] = DP[i-1] + 1 if DP[i] is greater
    // recurse/memoize: store DP[i] into memo
    // solve original problem: return DP[i]

    private static int LISRecursive(int[] A, int[] DP, int n){
        if(n<1) return 0;
        if(n==1) return 1;

        if(DP[n-1] != -1) return DP[n-1];

        int localMaxLIS = 1;
        int res = 1;

        for (int i = 1; i < n; i++)
        {
            res = LISRecursive(A, DP, i);
            if (A[i-1] < A[n-1] && res + 1 > localMaxLIS)
                localMaxLIS = res + 1;
        }

        if (maxLIS < localMaxLIS)
            maxLIS = localMaxLIS;

        DP[n-1] = localMaxLIS;

        return localMaxLIS;

    }

    // Runtime: O(N^2)
    // Space: O(N)
    public static int LIS(int[] A) {
        maxLIS = 0;

        int[] DP = new int[A.length];
        for(int i=0; i< DP.length; i++){
            DP[i] = -1;
        }

        LISRecursive(A, DP, A.length);

        return maxLIS;
    }
}