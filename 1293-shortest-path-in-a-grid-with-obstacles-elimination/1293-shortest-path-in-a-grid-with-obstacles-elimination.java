class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

         if (m == 1 && n == 1) return 0;

         
        if (k >= m + n - 2) return m + n - 2;

        int[][] maxRemaining = new int[m][n];

        for(int[] row : maxRemaining) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> que = new LinkedList<>();

        int[][] dirs = {{1,0}, {0,1}, {-1, 0}, {0,-1}};


        que.offer(new int[]{0,0,k,0});
        maxRemaining[0][0] = k;

        while(!que.isEmpty()) {
            int[] curr = que.poll();

            int r = curr[0];
            int c = curr[1];
            int remK = curr[2];
            int steps = curr[3];

            if(r == m -1 && c == n -1) {
                return steps;
            }

            for(int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if(nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextK = remK - grid[nr][nc];

                    if(nextK >= 0 && nextK > maxRemaining[nr][nc]) {
                        maxRemaining[nr][nc] = nextK;
                        que.offer(new int[]{nr, nc, nextK, steps+1});
                    }
                }
            }


        }

        return -1;


    }
}