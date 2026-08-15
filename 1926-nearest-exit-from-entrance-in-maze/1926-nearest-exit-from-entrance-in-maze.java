class Solution {
     private static final int[][] directions = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public int nearestExit(char[][] maze, int[] entrance) {
         int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{entrance[0], entrance[1]});
        maze[entrance[0]][entrance[1]] = '+'; 
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] temp = queue.poll();
                int x = temp[0], y = temp[1];

                 
                if (!(x == entrance[0] && y == entrance[1]) &&
                    (x == 0 || x == m - 1 || y == 0 || y == n - 1)) {
                    return steps;
                }

                
                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == '.') {
                        queue.offer(new int[]{nx, ny});
                        maze[nx][ny] = '+';  
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}