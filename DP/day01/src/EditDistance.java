public class EditDistance {

    // Subproblem: DP[i][j] = permutations it takes to get from string a up to index i
    // to string b up to index j
    // Guess: we replace? we delete? we insert? we do nothing?
    // Recurrence Relation: Min(deleting, inserting, replacing, doing nothing)
    // Memo/Recurse: DP[i][j] = min(deleting, inserting, replacing)
    // Solve final: return DP[a.length][b.length]
    // runtime and space o(n^2) where n is string length
    // this is my bottom up approach

    public static int minEditDist(String a, String b) {
        // have to be very cautious of off by one errors because when index
        // is 0 we are assuming the string is empty string (not really true though
        // making another variable for actual index of letter to make simpler
        int index1;
        int index2;
        int[][] DP = new int[a.length()+1][b.length()+1];
        for(int i=0; i<a.length()+1; i++){
            for(int j=0; j<b.length()+1; j++){
                index1 = i-1;
                index2 = j-1;
                if(i==0){
                    DP[i][j] = j;
                }
                else if(j==0){
                    DP[i][j] = i;
                }
                else{
                    int delete = DP[i-1][j] + 1;
                    int insert = DP[i][j-1] + 1;
                    int replace;
                    if(a.charAt(index1) == b.charAt(index2)){
                        replace = DP[i - 1][j - 1];
                    }
                    else{
                        replace = DP[i - 1][j - 1] + 1;
                    }
                    DP[i][j] = Math.min(delete, Math.min(insert, replace));
                }
            }
        }
        return DP[a.length()][b.length()];
    }

}
