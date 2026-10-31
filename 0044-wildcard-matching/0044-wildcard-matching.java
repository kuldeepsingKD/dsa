class Solution {
    public boolean isMatch(String s, String p) {
           int m = s.length(), n = p.length();
        // 1. Corrected array type to boolean
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 2. Corrected loop boundaries (starting at length instead of length - 1, 
        // because dp size is m+1 and n+1)
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                
                // Base Case: Both string and pattern are fully processed
                if (i == m && j == n) {
                    dp[i][j] = true;
                } 
                // Base Case: Pattern is exhausted, but string still has characters
                else if (j == n) {
                    dp[i][j] = false;
                } 
                // Base Case: String is exhausted, but pattern still has characters
                else if (i == m) {
                    // '*' can match an empty sequence
                    if (p.charAt(j) == '*') {
                        dp[i][j] = dp[i][j + 1];
                    } else {
                        dp[i][j] = false;
                    }
                } 
                // Main DP Transitions
                else {
                    if (p.charAt(j) == '?') {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else if (p.charAt(j) == '*') {
                        // '*' acts as empty sequence (dp[i][j+1]) OR matches 1+ characters (dp[i+1][j])
                        dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
                    } else if (p.charAt(j) == s.charAt(i)) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[0][0];
    }
}