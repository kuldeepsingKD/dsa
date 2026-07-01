class Solution {
    int ROW;
    int COL;
    int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public boolean DFS(int[][] grid, int i, int j) {
        if(i < 0 || i >= ROW || j < 0 || j >= COL || grid[i][j] == 1){
            return false;
        }

        if(i == ROW - 1) {
            return true;
        }

        grid[i][j] = 1;

        for(int[] dir : directions){
            int new_i = dir[0] + i;
            int new_j = dir[1] + j;

            if(DFS(grid, new_i, new_j)) {
                return true;
            }
        }
        return false;
    }
    public boolean canCross(int[][] cells, int mid) {
        int[][] grid = new int[ROW][COL];

        for(int i = 0; i <= mid; i++){
            int x = cells[i][0] - 1;
            int y = cells[i][1] - 1;

            grid[x][y] = 1;
        }

        for(int j = 0; j < COL; j++) {
            if(grid[0][j] == 0 && DFS(grid, 0, j)) {
                return true;
            }
        }
        return false;
    }
    public int latestDayToCross(int row, int col, int[][] cells) {
        ROW = row;
        COL = col;

        int l = 0;
        int h = cells.length-1;

        int lastday = 0;

        while(l <= h){
            int mid = l + (h-l)/2;
            if(canCross(cells, mid)){
                lastday = mid+1;
                l = mid + 1;
            }else{
                h = mid - 1;
            }
        }

        return lastday;
    }
}