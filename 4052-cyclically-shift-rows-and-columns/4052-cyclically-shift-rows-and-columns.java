class Solution {
    public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {
         
        
      int[][] ans = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int nc = (j - rowShift[i] + n) % n;
                int nr = (i - colShift[nc] + n) % n;

                ans[nr][nc] = grid[i][j];
            }
        }

        return ans;
    }
}