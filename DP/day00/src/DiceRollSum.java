public class DiceRollSum {

    // subproblem: DP[i] = how many dice roll sequences there are that reach sum N
    // guess: what you rolled next? 1, 2, 3, 4, 5, 6?
    // recurrence relation: DP[sum] = DP[sum-1]...+ DP[sum-6]
    // recurse/memoize: store DP[i] in the memo
    // solve original problem:return DP[i]

    private static int diceRollRecursive(int sum, int[] DP){
        if(sum==0) return 1;
        if(sum<0) return 0;

        if(DP[sum] != -1) return DP[sum];

        DP[sum] = diceRollRecursive(sum-1, DP) + diceRollRecursive(sum-2, DP) +
                diceRollRecursive( sum-3, DP) + diceRollRecursive( sum-4, DP) +
                diceRollRecursive( sum-5, DP) + diceRollRecursive( sum-6, DP);

        return DP[sum];
    }

    // Runtime: O(N*6)
    // Space: O(N)
    public static int diceRollSum(int N) {
        int[] DP = new int[N+1];
        for(int i=0; i< DP.length; i++){
                DP[i] = -1;
            }

        return diceRollRecursive(N, DP);
    }

}
