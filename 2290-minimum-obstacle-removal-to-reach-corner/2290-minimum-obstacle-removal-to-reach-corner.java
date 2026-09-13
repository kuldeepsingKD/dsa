class Solution {
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        int[][] dist = new int[m][n];
        for(int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }  

         PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

         pq.offer(new int[]{0,0,0});
         dist[0][0] = 0;

         while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int obstacles = curr[0];
            int r = curr[1];
            int c = curr[2];

            if(r == m-1 && c == n-1) {
                return obstacles;
            }

            if(obstacles > dist[r][c]) {
                continue;
            }

            for(int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nr < m && nc >= 0 && nc < n ) {
                    int newObstacles = obstacles + grid[nr][nc];

                    if(newObstacles < dist[nr][nc]) {
                        dist[nr][nc] = newObstacles;
                        pq.offer(new int[]{newObstacles, nr, nc});
                    }
                }


            }
         }

         return dist[m-1][n-1];
    }
}