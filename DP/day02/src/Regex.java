public class Regex {

    // Subproblem: DP[i][j] = true if first i characters in string matches first j characters in pattern
    // Guess: next character in string is a match? next character isn't a match?
    // Recurrence Relation: true if current is true and previous is true
    // Memo/Recurse: DP[i][j] = DP[i-1][j]/DP[i][j-1] & current is match or * or .
    // Solve final: return DP[s.length][p.length]
    // runtime and space o(n^2) where n is string/pattern length
    // this is a bottom up approach

    public static boolean isMatch(String s, String p) {

        // if the pattern is empty the string must also be empty for a match
        if (p.length() == 0)
            return (s.length() == 0);


        // create the DP table
        boolean[][] DP = new boolean[s.length()+1][p.length()+1];

        // fill in false for DP table initially
        for(int i = 0; i < s.length() + 1; i++){
            for(int j=0; j<p.length()+1; j++){
                DP[i][j] = false;
            }
        }

        // if looking at none of pattern and none of string then they match
        DP[0][0] = true;

        // if there is a * and the previous one is true then that one is true too for looking at empty string column
        for (int j = 0; j < p.length(); j++)
            if (p.charAt(j) == '*')
                DP[0][j+1] = DP[0][j - 1];

        for (int i = 1; i < s.length()+1; i++) {
            for (int j = 1; j < p.length()+1; j++) {

                if (p.charAt(j - 1) == '*') {
                    if(p.charAt(j-2) != '.' && p.charAt(j-2) != s.charAt(i-1)){
                        DP[i][j] = DP[i][j-2];
                    }
                    else {
                        DP[i][j] = DP[i][j - 1] || DP[i - 1][j] || DP[i][j-2];
                    }
                }

                if (p.charAt(j - 1) == '.' ||
                        s.charAt(i - 1) == p.charAt(j - 1))
                    DP[i][j] = DP[i - 1][j - 1];

            }
        }


        return DP[s.length()][p.length()];
    }

}
