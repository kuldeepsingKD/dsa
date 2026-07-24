class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;
        int[][][] dp = new int[m][n][k];

        int startrem = (grid[0][0]) % k;
        dp[0][0][startrem] = 1;

        for(int i=0; i <m ; i++) {
            for(int j = 0; j < n; j++) {
                for(int rem = 0; rem < k; rem++) {

                    int paths = dp[i][j][rem];

                    if(paths == 0) {
                        continue;
                    }

                    if(i + 1 < m) {
                        int newRem = (rem + grid[i+1][j]) % k;

                        dp[i+1][j][newRem] = (dp[i+1][j][newRem] + paths) % MOD;
                    }

                    if(j + 1 < n) {
                        int newRem = (rem + grid[i][j+1]) % k;
                        dp[i][j+1][newRem] = (dp[i][j+1][newRem] + paths) % MOD;
                    }
                }
            }
        }

        return dp[m-1][n-1][0];

    }
}