class Solution {
    // 1. Fixed: grid, rows, aur cols ko method signatures mein pass kiya gaya hai
    public boolean canMoveTo(int[] start, int[] end, int[] boxPos, char[][] grid, int rows, int cols) {
        // Edge check: Agar target player position hi out of bounds ya wall hai
        if (end[0] < 0 || end[0] >= rows || end[1] < 0 || end[1] >= cols || grid[end[0]][end[1]] == '#') {
            return false;
        }

        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols]; // Fixed: Set<String> ki jagah boolean array for speed
        
        que.offer(start);
        visited[start[0]][start[1]] = true;

        while(!que.isEmpty()) {
            int[] curr = que.poll();
            if(Arrays.equals(curr, end)) {
                return true;
            }
            for(int[] dir : new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}}) {
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];

                if(nx >= 0 && nx < rows && ny >= 0 && ny < cols && grid[nx][ny] != '#' && (nx != boxPos[0] || ny != boxPos[1]) && !visited[nx][ny] ) {
                    visited[nx][ny] = true;
                    que.offer(new int[]{nx,ny});
                }
            }
        }
        return false;
    }

    public int minPushBox(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[] player = new int[2];
        int[] box = new int[2];
        int[] target = new int[2];

        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                if(grid[r][c] == 'S') {
                   player[0] = r;
                   player[1] = c;
                } else if(grid[r][c] == 'B') {
                    box[0] = r;
                    box[1] = c;
                } else if(grid[r][c] == 'T') {
                    target[0] = r;
                    target[1] = c; 
                }
            }
        }

        Queue<int[]> que = new LinkedList<>();
        // Fixed: Performance optimize karne ke liye 4D boolean array ka use kiya hai
        boolean[][][][] visited = new boolean[rows][cols][rows][cols];
        
        que.offer(new int[]{0, box[0], box[1], player[0], player[1]});
        visited[box[0]][box[1]][player[0]][player[1]] = true;

        while(!que.isEmpty()) { // Fixed typo: queue -> que
            int[] curr = que.poll(); // Fixed typo: queue -> que
            int pushes = curr[0];
            int boxX = curr[1];
            int boxY = curr[2];
            int playerX = curr[3];
            int playerY = curr[4];

            if(boxX == target[0] && boxY == target[1]) {
                return pushes;
            }

            for(int[] dir : new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}}) {
                int newBX = boxX + dir[0];
                int newBY = boxY + dir[1];
                int newPX = boxX - dir[0];
                int newPY = boxY - dir[1]; // Fixed Bug: dir[0] ki jagah dir[1] kiya for column subtraction

                // Boundary check for new box position
                if(newBX >= 0 && newBX < rows && newBY >= 0 && newBY < cols && grid[newBX][newBY] != '#') {
                    // Check if this new state combination is already visited
                    if(!visited[newBX][newBY][boxX][boxY]) {
                        // Check if player can reach the pulling/pushing position
                        if(canMoveTo(new int[]{playerX, playerY}, new int[]{newPX, newPY}, new int[]{boxX, boxY}, grid, rows, cols)) {
                            visited[newBX][newBY][boxX][boxY] = true;
                            que.offer(new int[]{pushes + 1, newBX, newBY, boxX, boxY}); // Fixed typo: queue -> que
                        }
                    }
                }
            }
        }

        return -1;
    }
}