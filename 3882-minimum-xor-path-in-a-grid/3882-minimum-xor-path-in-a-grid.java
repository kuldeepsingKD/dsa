 

class Solution {
    public int minCost(int[][] grid) {
          int m = grid.length;
        int n = grid[0].length;
        
        // Find max possible XOR value (upper bound)
        // Values fit in 10 bits (0-1023) based on constraints
        int MAXVAL = 1024;
        
        // dp[i][j] = set of all reachable XOR values at cell (i,j)
        // Use boolean array indexed by XOR value
        boolean[][][] dp = new boolean[m][n][MAXVAL];
        dp[0][0][grid[0][0] % MAXVAL] = true;
        
        // Fill first row
        for (int j = 1; j < n; j++) {
            for (int x = 0; x < MAXVAL; x++) {
                if (dp[0][j-1][x]) {
                    dp[0][j][x ^ grid[0][j]] = true;
                }
            }
        }
        
        // Fill first column
        for (int i = 1; i < m; i++) {
            for (int x = 0; x < MAXVAL; x++) {
                if (dp[i-1][0][x]) {
                    dp[i][0][x ^ grid[i][0]] = true;
                }
            }
        }
        
        // Fill rest
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                for (int x = 0; x < MAXVAL; x++) {
                    if (dp[i-1][j][x] || dp[i][j-1][x]) {
                        dp[i][j][x ^ grid[i][j]] = true;
                    }
                }
            }
        }
        
        // Find minimum XOR at destination
        for (int x = 0; x < MAXVAL; x++) {
            if (dp[m-1][n-1][x]) return x;
        }
        
        return -1;
    }
}