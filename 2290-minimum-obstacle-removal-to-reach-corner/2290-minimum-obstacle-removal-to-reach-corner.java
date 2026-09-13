class Solution {
    public int minimumObstacles(int[][] grid) {
       int m = grid.length;
        int n = grid[0].length;

        // Directions for moving: Up, Down, Left, Right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // dist[r][c] store karega cell (r, c) tak pahunchne ke liye minimum kitne obstacles todne pade
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Double-Ended Queue (Deque) to store elements as {row, col}
        Deque<int[]> deque = new ArrayDeque<>();

        // Starting point (0, 0)
        deque.offerFirst(new int[]{0, 0});
        dist[0][0] = 0;

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];

            // Goal Check: Agar bottom-right corner par pahunch gaye
            if (r == m - 1 && c == n - 1) {
                return dist[r][c];
            }

            // Explore 4 adjacent neighbors
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Boundary check
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    // Agar agla cell obstacle (1) hai toh cost 1 badhegi, khali (0) hai toh 0 badhegi
                    int weight = grid[nr][nc];
                    int newObstacles = dist[r][c] + weight;

                    // Relaxation step: Agar naya path pichle wale se behtar hai
                    if (newObstacles < dist[nr][nc]) {
                        dist[nr][nc] = newObstacles;

                        // 0-1 BFS Core Logic:
                        // Cost 0 hai toh front me dalo, Cost 1 hai toh back me dalo
                        if (weight == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }

        return dist[m - 1][n - 1];
    }
}