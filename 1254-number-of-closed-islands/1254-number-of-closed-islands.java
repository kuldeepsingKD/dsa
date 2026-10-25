class Solution {
      private int rows;
    private int cols;
     private void dfs(int[][] grid, int r, int c) {
        // Boundary check aur check ki kya cell land (0) hai
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] != 0) {
            return;
        }

        // Land ko visit mark karne ke liye 1 (water) bana dein
        grid[r][c] = 1;

        // Charo directions (Up, Down, Left, Right) mein explore karein
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
    public int closedIsland(int[][] grid) {
         rows = grid.length;
        cols = grid[0].length;

        // Step 1: Border wale saare land (0) ko pani (1) mein badlein
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Agar cell border par hai aur land (0) hai
                if ((r == 0 || r == rows - 1 || c == 0 || c == cols - 1) && grid[r][c] == 0) {
                    dfs(grid, r, c);
                }
            }
        }

        // Step 2: Andar ke bache hue closed islands ko count karein
        int closedIslands = 0;
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == 0) {
                    closedIslands++;
                    dfs(grid, r, c); // Is pure island ko mark/sink kar dein
                }
            }
        }

        return closedIslands;
    }
}