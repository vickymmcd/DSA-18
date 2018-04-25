public class DungeonGame {
    // Subproblem: DP[i][j] = minInitialHealth if i,j is the starting point to end
    // Guess: we go down (/up since im doing backwards)? we go right (/left since im doing backwards)?
    // Recurrence Relation: Max(Min(going down, going right), 1)
    // Memo/Recurse: DP[i][j] = Max(Min(going down, going right), 1)
    // Solve final: return DP[0][0]
    // runtime and space o(n^2) where n is length/width of map
    // this is another bottom up approach

    public static int minInitialHealth(int[][] map) {
        int h = map.length;
        int w = map[0].length;

        int DP[][] = new int[h][w];

        // bottom corner (destination)
        if(map[h-1][w-1] > 0)
            DP[h-1][w-1] = 1;
        else
            DP[h-1][w-1] = Math.abs(map[h-1][w-1]) + 1; //absolute value of the neg # +1

        // fill in the last row and column making sure you never go below 1
        // the dp of the previous one (closer to bottom right) - the current or 1 if 1 is
        // greater than whatever that value is (never let fall below 1)
        for (int i = h-2; i >= 0; i--)
            DP[i][w-1] = Math.max(DP[i+1][w-1] - map[i][w-1], 1);
        for (int j = w-2; j >= 0; j--)
            DP[h-1][j] = Math.max(DP[h-1][j+1] - map[h-1][j], 1);

        for (int i=h-2; i>=0; i--)
        {
            for (int j=w-2; j>=0; j--)
            {
                // move right or move down
                int min = Math.min(DP[i+1][j], DP[i][j+1]);
                DP[i][j] = Math.max(min - map[i][j], 1);
            }
        }

        return DP[0][0];
    }
}
