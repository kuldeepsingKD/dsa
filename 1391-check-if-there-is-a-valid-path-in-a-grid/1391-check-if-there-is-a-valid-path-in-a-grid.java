class Solution {
       private final int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
      private final boolean[][] streetCanGo = {
        {},                               // Street 0 (unused dummy)
        {true, true, false, false},       // Street 1: Right, Left
        {false, false, true, true},       // Street 2: Up, Down
        {false, true, false, true},       // Street 3: Left, Down
        {true, false, false, true},       // Street 4: Right, Down
        {false, true, true, false},       // Street 5: Left, Up
        {true, false, true, false}        // Street 6: Right, Up
    };

     private boolean canEnter(int nextStreet, int dir) {
        if (dir == 0) return nextStreet == 1 || nextStreet == 3 || nextStreet == 5; // Must have Left link
        if (dir == 1) return nextStreet == 1 || nextStreet == 4 || nextStreet == 6; // Must have Right link
        if (dir == 2) return nextStreet == 2 || nextStreet == 3 || nextStreet == 4; // Must have Down link
        if (dir == 3) return nextStreet == 2 || nextStreet == 5 || nextStreet == 6; // Must have Up link
        return false;
    }


    private boolean dfs(int r, int c, int m, int n, int[][] grid, boolean[][] visited) {
        // Base Target reached
        if (r == m - 1 && c == n - 1) {
            return true;
        }

        visited[r][c] = true;
        int currentStreet = grid[r][c];

        // Try moving in all 4 possible directions
        for (int i = 0; i < 4; i++) {
            // Check if current street even points in this direction
            if (!streetCanGo[currentStreet][i]) continue;

            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];

            // Boundary validation & check if visited
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nr == r ? nc : c]) { 
                // Fix to look at visited[nr][nc] safely
                if (!visited[nr][nc]) {
                    int nextStreet = grid[nr][nc];
                    
                    // Verify if the next street links back matching the entry criteria
                    if (canEnter(nextStreet, i)) {
                        if (dfs(nr, nc, m, n, grid, visited)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean hasValidPath(int[][] grid) {
         int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        return dfs(0, 0, m, n, grid, visited);
    }
}