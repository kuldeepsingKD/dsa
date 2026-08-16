class Solution {
    int[][][] dp ;

    private int solve(int[] stones, int totalsum, int turn, int i, int j) {
        if(i >= j) return dp[i][j][turn] =  0;

        if(dp[i][j][turn] != -1) {
            return dp[i][j][turn];
        } 

        if(turn == 1) {
            int x = totalsum - stones[i];
            int res1 = solve(stones, x, 0, i+1, j) + x;

            int y = totalsum - stones[j];
            int res2 = solve(stones, y, 0, i, j -1) + y;

            return dp[i][j][turn] = Math.max(res1,res2);
        }else{

               int x = totalsum - stones[i];
            int res1 = solve(stones, x, 1, i+1, j) - x;

            int y = totalsum - stones[j];
            int res2 = solve(stones, y, 1, i, j -1) - y;

            return dp[i][j][turn] = Math.min(res1,res2);
        }
    }
    public int stoneGameVII(int[] stones) {
        int n = stones.length;

        dp = new int[n][n][2];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int totalsum = 0;
        int turn = 1;

        for(int stone : stones) {
            totalsum += stone;
        }

        return solve(stones, totalsum, 1, 0, n-1);
    }
}