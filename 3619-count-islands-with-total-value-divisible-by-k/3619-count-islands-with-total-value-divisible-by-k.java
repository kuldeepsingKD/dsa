class Solution {
    int n, m;
    int[][] directions = {{1,0}, {-1, 0}, {0,1}, {0,-1}};

    public long dfs(int i, int j, int[][] grid) {
        long sum = grid[i][j];
        grid[i][j] = 0;

        for(int[] dir : directions) {
            int i_ = i + dir[0];
            int j_ = j + dir[1];

            if(i_ >= 0 && i_ < m && j_ >= 0 && j_ < n && grid[i_][j_] > 0) {
                sum += dfs(i_, j_, grid);
            }
        }

        return sum;
    }
    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;

         
        int count = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] > 0) {
                   long sum = dfs(i, j, grid);
                    if(sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;


    }
}