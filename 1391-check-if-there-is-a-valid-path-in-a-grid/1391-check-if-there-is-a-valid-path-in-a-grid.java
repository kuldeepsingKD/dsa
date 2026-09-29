class Solution {
    

     private boolean canEnter(int nextStreet, int dir) {
        if (dir == 0) return nextStreet == 1 || nextStreet == 3 || nextStreet == 5; // Must have Left link
        if (dir == 1) return nextStreet == 1 || nextStreet == 4 || nextStreet == 6; // Must have Right link
        if (dir == 2) return nextStreet == 2 || nextStreet == 3 || nextStreet == 4; // Must have Down link
        if (dir == 3) return nextStreet == 2 || nextStreet == 5 || nextStreet == 6; // Must have Up link
        return false;
    }


    
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length; // Fixed index validation
        
        // Direction vectors: Right (0), Left (1), Up (2), Down (3)
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        // Street out-going directional maps
        boolean[][] streetCanGo = {
            {},                               // Street 0 (dummy)
            {true, true, false, false},       // Street 1: Right, Left
            {false, false, true, true},       // Street 2: Up, Down
            {false, true, false, true},       // Street 3: Left, Down
            {true, false, false, true},       // Street 4: Right, Down
            {false, true, true, false},       // Street 5: Left, Up
            {true, false, true, false}        // Street 6: Right, Up
        };

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        // Start from top-left cell
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            // Agar hum bottom-right cell par pahunch gaye, toh path valid hai
            if (r == m - 1 && c == n - 1) {
                return true;
            }

            int currentStreet = grid[r][c];

            // 4 directions mein check karo
            for (int i = 0; i < 4; i++) {
                // Agar current street is direction mein nahi jati, toh skip karo
                if (!streetCanGo[currentStreet][i]) continue;

                int nr = r + dirs[i][0];
                int nc = c + dirs[i][1];

                // Boundary verification and visited check
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int nextStreet = grid[nr][nc];

                    // Check if next street can accept entry from direction 'i'
                    if (canEnter(nextStreet, i)) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return false;
    }
}