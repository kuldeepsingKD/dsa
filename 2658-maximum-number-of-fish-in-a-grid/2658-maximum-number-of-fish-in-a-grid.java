class Solution {
    int n, m;
    private int dfs(int[][] grid, int i, int j) {
        if(i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }

        if(grid[i][j] == 0) {
            return 0;
        }

        int fish = grid[i][j];

          grid[i][j] = 0;

        fish += dfs(grid, i+1, j);
        fish += dfs(grid, i-1, j);
        fish += dfs(grid, i, j+1);
        fish += dfs(grid, i, j-1);

        return fish;
    }
    public int findMaxFish(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int ans = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] > 0) {
                    ans = Math.max(ans, dfs(grid,i,j));
                }
            }
        }

        return ans; 
    }
}