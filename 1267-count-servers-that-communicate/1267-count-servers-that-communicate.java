class Solution {
    private void dfs(int[][] grid, int r, int c, boolean[][] visited, int[] serverCount) {
        visited[r][c] = true;
        serverCount[0]++;


        for(int j = 0; j < grid[0].length; j++) {
            if(grid[r][j] == 1 && !visited[r][j]) {
                dfs(grid, r, j, visited, serverCount);
            }
        }

        for(int i = 0; i < grid.length; i++) {
            if(grid[i][c] == 1 && !visited[i][c]) {
                dfs(grid, i, c, visited, serverCount);
            }
        }
    }
    public int countServers(int[][] grid) {
         int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int totalCommunicatingServers = 0;

        // Poore grid par iterate karenge
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Agar server hai aur visited nahi hai, toh naya component mila
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int[] serverCount = new int[1]; // Pass by reference ke liye array use kiya hai
                    dfs(grid, i, j, visited, serverCount);
                    
                    // Agar component ka size > 1 hai, toh yeh aapas mein communicate kar sakte hain
                    if (serverCount[0] > 1) {
                        totalCommunicatingServers += serverCount[0];
                    }
                }
            }
        }

        return totalCommunicatingServers;
    }
}