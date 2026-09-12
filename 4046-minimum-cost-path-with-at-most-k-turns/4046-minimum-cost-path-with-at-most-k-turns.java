class Solution {
    public int minCost(int[][] grid, int k) {
         int m = grid.length;
        int n = grid[0].length;

        // Directions: 0: Up, 1: Down, 2: Left, 3: Right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // dist[r][c][prev_dir][turns]
        // prev_dir ranges from 0 to 3. For the starting cell, we treat it specially.
        int[][][][] dist = new int[m][n][4][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 4; d++) {
                    Arrays.fill(dist[i][j][d], Integer.MAX_VALUE);
                }
            }
        }

        // Priority Queue stores arrays of format: {cost, r, c, prev_dir, turns}
        // Sorted primarily by cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Push initial state. Use -1 for prev_dir since there is no previous move.
        pq.offer(new int[]{grid[0][0], 0, 0, -1, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int r = curr[1];
            int c = curr[2];
            int prevDir = curr[3];
            int turns = curr[4];

            // If we reached the bottom-right cell, return the cost
            if (r == m - 1 && c == n - 1) {
                return cost;
            }

            // Skip if we found a strictly better path to this exact state configuration
            if (prevDir != -1 && cost > dist[r][c][prevDir][turns]) {
                continue;
            }

            // Explore all 4 adjacent neighbors
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // Boundary check
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newTurns = turns;
                    
                    // A turn happens if we had a valid previous direction and it changed
                    if (prevDir != -1 && prevDir != d) {
                        newTurns++;
                    }

                    // Only proceed if we haven't breached the allowed number of turns
                    if (newTurns <= k) {
                        int newCost = cost + grid[nr][nc];

                        // Relaxation step
                        if (newCost < dist[nr][nc][d][newTurns]) {
                            dist[nr][nc][d][newTurns] = newCost;
                            pq.offer(new int[]{newCost, nr, nc, d, newTurns});
                        }
                    }
                }
            }
        }

        return -1;
    }
}