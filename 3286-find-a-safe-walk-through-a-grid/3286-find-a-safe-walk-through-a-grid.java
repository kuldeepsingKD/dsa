class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
       int m = grid.size();
       int n = grid.get(0).size();

       int[][] bestHealth = new int[m][n];
       for(int[] row : bestHealth){
        Arrays.fill(row, -1);
       } 

       Queue<int[]> que = new LinkedList<>();

       int startHealth = health - grid.get(0).get(0);
       if(startHealth <= 0) return false;

       que.offer(new int[]{0,0, startHealth});
       bestHealth[0][0] = startHealth;

       int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

       while(!que.isEmpty()) {
        int[] curr = que.poll();
        int x =curr[0];
        int y = curr[1];
        int currHealth = curr[2];

        if(x == m-1 && y == n-1){
            return true;
        }

        for(int[] d : dirs){
            int nx = x + d[0];
            int ny = y + d[1];

            if(nx < 0 || ny <0 || nx >= m || ny >= n){
                continue;
            }

            int newHealth = currHealth - grid.get(nx).get(ny);

            if(newHealth <= 0){
                continue;
            }

            if(newHealth <= bestHealth[nx][ny]){
                continue;
            }

            bestHealth[nx][ny] = newHealth;
            que.offer(new int[]{nx, ny, newHealth});
        }
       }

       return false;
        



    }
}