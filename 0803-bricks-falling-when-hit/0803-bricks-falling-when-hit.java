class Solution {
int[][] grid;
int n, m;

private int dfs(int i, int j) { 
    grid[i][j] = 2; 
     int count = 0;
  if (i + 1 < n && grid[i + 1][j] == 1) count += 1 + dfs(i + 1, j);
   if (i - 1 >= 0 && grid[i - 1][j] == 1) count += 1 + dfs(i - 1, j); 
   if (j + 1 < m && grid[i][j + 1] == 1) count += 1 + dfs(i, j + 1); 
   if (j - 1 >= 0 && grid[i][j - 1] == 1) count += 1 + dfs(i, j - 1); 
   return count; 
}
    public int[] hitBricks(int[][] grid, int[][] hits) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;

        for(int[] hit : hits) {
            grid[hit[0]][hit[1]]--;
        }

        for(int i =0; i <m; i++){
            if(grid[0][i] == 1){
                dfs(0,i);
            }
        }

        // Step 3: Process hits in reverse order

        int l = hits.length;
        int[] ans = new int[l];
        for(int i = l -1; i >= 0; i--) {
            int x = hits[i][0];
            int y = hits[i][1];

            grid[x][y]++;

            if (grid[x][y] == 1 && (x == 0 || (x > 0 && grid[x - 1][y] == 2) || (x < n - 1 && grid[x + 1][y] == 2) || (y > 0 && grid[x][y - 1] == 2) || (y < m - 1 && grid[x][y + 1] == 2))) { 
                ans[i] = dfs(x, y);
        }
        }
        return ans;
    }
}