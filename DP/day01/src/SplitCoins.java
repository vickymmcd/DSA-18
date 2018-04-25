public class SplitCoins {

    // Subproblem: DP[i][j] = min difference for only including up to index i in one of sums j
    // Guess: we include the value in sum1 (to make smaller difference) or not
    // Recurrence Relation: mindiff(adding coins[i] to sum1, not adding coins[i] to sum1)
    // Memo/Recurse: DP[i][j] = Min(splitcoins(sum1+coins[i]), splitcoins(sum1))
    // Solve final: return min DP with index 0
    // runtime and space are o(n*m) where n is number of coins and m is largest sum
    // this is a top down approach

    public static int splitCoinsRecursive(int[] coins, int sum1, int totalSum, int index, int[][] DP){
        int sum2 = totalSum-sum1;
        if(index==0){
            return Math.abs(sum1-sum2);
        }
        if(DP[index-1][sum1] != -1){
            return DP[index-1][sum1];
        }

        DP[index-1][sum1] = Math.min(splitCoinsRecursive(coins, sum1+coins[index-1], totalSum, index-1, DP),
                splitCoinsRecursive(coins, sum1, totalSum, index-1, DP));
        return DP[index-1][sum1];
    }

    public static int splitCoins(int[] coins) {
        int totalSum = 0;
        for(int i=0; i<coins.length; i++){
            totalSum=totalSum+coins[i];
        }
        int[][] DP = new int[coins.length][totalSum];

        for(int i=0; i< DP.length; i++){
            for(int j=0; j<DP[0].length; j++){
                DP[i][j] = -1;
            }
        }
        return splitCoinsRecursive(coins, 0, totalSum, coins.length, DP);
    }
}
